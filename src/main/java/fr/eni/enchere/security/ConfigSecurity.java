package fr.eni.enchere.security;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fr.eni.enchere.security.MyUserDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {


	public static final String USER = "USER";
	public static final String ADMIN = "ADMIN";

//	@Bean
//	UserDetailsManager userDetailsManager(DataSource dataSource) {
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		//récupération des information de l'utilisateur dans la table utilisateur
//		jdbcUserDetailsManager.setUsersByUsernameQuery("select pseudo, password, actif from utilisateur where pseudo = ?");
//		//récupération du role des utilisateur dans la table roles
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select pseudo, role from roles where pseudo = ?");
//		
//		return jdbcUserDetailsManager;
//	
//	}
//	MyUserDetailsService myUserDetailsService= new MyUserDetailsService();
//	UserDetailsManager userDetailsManager() {
//		UserDetailsManager userDetailsManager=new User
//		return
//	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
//			auth.anyRequest().permitAll();
			
			auth.requestMatchers(HttpMethod.GET, "/").permitAll();
			//auth.requestMatchers(HttpMethod.GET, "/index").permitAll();
			//on donne accès à la requète de type get "/securites/liste" à l'employé
			auth.requestMatchers(HttpMethod.GET, "/profil/*").hasAuthority(USER)
				.requestMatchers(HttpMethod.GET, "/profil").hasAuthority(USER)
				.requestMatchers(HttpMethod.POST, "/profil/*").hasAuthority(USER)
				.requestMatchers(HttpMethod.GET, "/inscription").permitAll()
				.requestMatchers(HttpMethod.POST, "/inscription/*").permitAll()
				.requestMatchers(HttpMethod.POST, "/login/*").permitAll()
				.requestMatchers(HttpMethod.GET, "/login/*").permitAll()
			//on donne accès à la requète de type get "/securites/admin" à l'admin
				.requestMatchers(HttpMethod.GET, "/admin").hasAuthority(ADMIN)
				.requestMatchers(HttpMethod.GET, "/admin/*").hasAuthority(ADMIN)
			//on donne accès au site à la page d'acceuil
				.requestMatchers("/*").permitAll()
			//on donne accès au css
				.requestMatchers(HttpMethod.GET, "/enchere/*").permitAll()
				.requestMatchers(HttpMethod.POST, "/enchere/recherche").permitAll()
				.requestMatchers(HttpMethod.GET, "/enchere/article/*").hasAuthority(USER)
				.requestMatchers(HttpMethod.POST, "/enchere/article/*").hasAuthority(USER)
				.requestMatchers("/css/*").permitAll()
			//on donne accès aux images
				.requestMatchers("/images/*").permitAll()
				.requestMatchers("/javascript/*").permitAll()
				.requestMatchers("/error").permitAll()
			//interdire le reste des url
				.anyRequest().denyAll();
			
		});
		http.csrf(csrf -> csrf.disable());
		//gestion automatique du login
		//http.formLogin(Customizer.withDefaults());
		http.formLogin(form ->{
			form.loginPage("/login").permitAll();
			form.defaultSuccessUrl("/").permitAll();
			form.failureUrl("/login-error");
			form.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
				   @Override
				    public void onAuthenticationSuccess(HttpServletRequest request, 
				      HttpServletResponse response, Authentication authentication)
				      throws IOException, ServletException {
				 
				    	  // MET L'UTILISATEUR CONNECTE DANS UNE VARIABLE DE SESSION currentUser
				    	  if(authentication!=null) {
				    		  MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
				    		  request.getSession().setAttribute("currentUser", userDetails.getUser());
				    		  System.out.println("after log"+ userDetails.getAuthorities().toString());
				    	  }
				    	  super.onAuthenticationSuccess(request, response, authentication);
				    }

			});
		});
		
		http.logout(logout -> {
			//suppression la session du côté du serveur
			logout.invalidateHttpSession(true)
				.clearAuthentication(true)
				//supprimer
				.deleteCookies("JSESSIONID")
				//déterminer la page à utiliser pour le logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				//redirige vers la page d'acceuil
				.logoutSuccessUrl("/")
				//permission d'acces à tous
				.permitAll();
			
		});
		
		
		return http.build();
		
	}

}
