package co.com.disney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import co.com.disney.auth.JwtFilterRequest;
import co.com.disney.repository.OperationUserJPA;



@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	
	@Autowired
	private OperationUserJPA operationUserJPA;
	
	
	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(operationUserJPA);   
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/auth/*").permitAll() 
		.anyRequest().authenticated()
		.and().exceptionHandling()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 

		http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
		
	}


	@Override
	@Bean 
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
	
	

}
