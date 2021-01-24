package Esprit.PiDev.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import Esprit.PiDev.Service.User_Details_Service_Impl;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class Security_Configuration extends WebSecurityConfigurerAdapter {
	@Autowired
	private Authentification_Entry_Point_Config entryPoint;
	@Autowired
	private User_Details_Service_Impl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder()); 

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable().authorizeRequests().anyRequest().authenticated() // All requests send to the client side from server side must be authenticated
			.and()
				.httpBasic().authenticationEntryPoint(entryPoint) // UseAuthenticationEntryPoint to authenticate with Email&&password
			.and()
				.formLogin().loginPage("/login").permitAll() // Browser API login form to authenticate
			.and()
				.logout().clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("JSESSIONID").and().sessionManagement().maximumSessions(1); // Destroy cookies to remove Session (JSESSIONID)
																						 

			http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}
	

	/*
	 * private AuthenticationSuccessHandler successHandler() { return new
	 * AuthenticationSuccessHandler() {
	 * 
	 * @Override public void onAuthenticationSuccess(HttpServletRequest
	 * httpServletRequest, HttpServletResponse httpServletResponse,
	 * Authentication authentication) throws IOException, ServletException {
	 * httpServletResponse.getWriter().append("OK");
	 * httpServletResponse.setStatus(200); } }; }
	 */

}
