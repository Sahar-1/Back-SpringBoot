package Esprit.PiDev.Security;

import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import Esprit.PiDev.ExterneService.Custom_Oauth2_User_Service;
import Esprit.PiDev.ExterneService.OAuth2_Login_Success_Handler;
import Esprit.PiDev.Service.User_Details_Service_Impl;

@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class Security_Configuration extends WebSecurityConfigurerAdapter {
	@Autowired
	private Authentification_Entry_Point_Config entryPoint;
	
	@Autowired
	private User_Details_Service_Impl userDetailsService;
	@Autowired
	Custom_Oauth2_User_Service customService;
	@Autowired
	OAuth2_Login_Success_Handler oauth2LoginSuccessHandler;
	 
 

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());//Authentication Bean Configuration
		
	}
	 @Override
	 public void configure(final WebSecurity webSecurity) {
	  webSecurity.ignoring().antMatchers("/token/**");
	 }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/Sign-In","/logout") .permitAll() 
		 .and().authorizeRequests().antMatchers( HttpMethod.GET, "/oauth2/**"  , "/all"  ).permitAll() 
			 
		.anyRequest().authenticated() 
		
				.and().httpBasic().authenticationEntryPoint(entryPoint) 
				.and().formLogin().loginPage("/login").permitAll()  
				.and().oauth2Login().loginPage("/login").userInfoEndpoint().userService(customService).and()
				.successHandler(oauth2LoginSuccessHandler).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		        
				.clearAuthentication(true).deleteCookies("dummyCookie").and().sessionManagement().maximumSessions(1);
		
		 
		
		http.logout().deleteCookies("JSESSIONID").and().sessionManagement().maximumSessions(1); 
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		 
	}
	 
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
 
	 @Bean
	 AuthenticationEntryPoint forbiddenEntryPoint() {
	  return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	 }

	@Bean
	public UserDetailsService userDetailsService () {
		return new User_Details_Service_Impl();
	} 
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
	    return authenticationManager();
	}
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new Custom_Logout_Success_Handler();
	}

	@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> sessionListener() {
	    return new ServletListenerRegistrationBean<HttpSessionListener>(new Session_Created());
	}
	 @Bean
	  public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	  }

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
