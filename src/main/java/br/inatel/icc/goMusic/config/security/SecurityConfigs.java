package br.inatel.icc.goMusic.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.inatel.icc.goMusic.repository.UserRepository;


@EnableWebSecurity
@Configuration
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

	private AuthService authService;
	private UserRepository userRepository;
	private TokenService tokenService;

	@Autowired
	public SecurityConfigs(AuthService authService, UserRepository userRepository, TokenService tokenService) {
		this.authService = authService;
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Authentication configs
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Authorization configs
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.antMatchers(HttpMethod.POST, "/auth/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(new AuthTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

}
