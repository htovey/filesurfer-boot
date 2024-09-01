/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.security;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import het.springapp.security.RestAuthenticationFailure;


/**
 *
 * @author heather
 */
@Configuration
@EnableWebSecurity
public class SecurityAdapter {

	@Value("${security.enable-csrf}")
	private boolean csrfEnabled;
	
	@Value("${allowed.origins}")
	private String allowedOrigins;
	
	@Autowired
	private DataSource dataSource; 
	
	@Autowired
	private RestAuthenticationFailure restAuthenticationEntryFailure;
	
	@Autowired
	private UserDetailsService coreUserDetailsService;
	
	@Bean("passwordEncoder")
	public BCryptPasswordEncoder getPasswordEncoder() {
	      return new BCryptPasswordEncoder();
	}
	
	@Bean 
	MyCorsFilter myCorsFilter() {
		return new MyCorsFilter(corsConfigurationSource());
	}
	
	@Bean 
	MyCsrfFilter myCsrfFilter() {
		return new MyCsrfFilter();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(coreUserDetailsService);
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}
	
	  @Bean
	  public PersistentTokenRepository tokenRepository() {
	    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
	    jdbcTokenRepositoryImpl.setDataSource(dataSource);
	    return jdbcTokenRepositoryImpl;
	  }
   
	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList(StringUtils.tokenizeToStringArray(allowedOrigins, ",")));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
	        configuration.setAllowedHeaders(Arrays.asList("Origin, Accept, authorization, Content-Type, Accept, X-Requested-With, remember-me, x-csrf-token"));
	        configuration.addAllowedHeader("authorization");
	        configuration.addAllowedHeader("content-type");
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
		.httpBasic()
		.and()
		.csrf().disable()
		.headers().disable()
		.logout().disable()
		.addFilterAt(myCorsFilter(), CorsFilter.class)	
		.addFilterBefore(myCsrfFilter(), SecurityContextPersistenceFilter.class)
		//.addFilterAt(authenticationFilter(), BasicAuthenticationFilter.class)
		.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryFailure)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
		.anonymous().disable();
		return http.build();
    }
		

	
}
