package com.medsko.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	private final UserDetailsService userDetailsService;

	public SecurityConfig(DataSource dataSource, UserDetailsService userDetailsService) {
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/design", "/orders")
					.hasRole("USER")
				.antMatchers("/", "/**")
					.permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/design")
			.and()
			.logout()
				.logoutSuccessUrl("/")  // By default, the user will be redirected to the loginPage after logging out.
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  In memory implementation
//		auth
//	    	.inMemoryAuthentication()
//			.withUser("harry")
//				.password("overDeMuur")
//				.authorities("ROLE_USER")
//			.and()
//			.withUser("herman")
//				.password("geenJagerGeenNeger")
//				.authorities("ROLE_USER");

//  Jdbc implementation
//		auth
//	    	.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("select username, password, enabled from Users"
//				+ " where username = ?")
//			.authoritiesByUsernameQuery("select username, authority from UserAuthorities" +
//					" where username = ?")
//			.passwordEncoder(new BCryptPasswordEncoder());

//  LDAP implementation
//		auth
//	    	.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.passwordCompare()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.passwordAttribute("passcode")
//			.and()
//			.contextSource()
//				.root("dc=tacocloud,dc=com")
//				.ldif("classpath:users.ldif");

		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

}
