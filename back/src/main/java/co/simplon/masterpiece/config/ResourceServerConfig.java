package co.simplon.masterpiece.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Configures the HTTP security for this application.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Disable CSRF, no need with JWT if not cookie-based.
		// Disable CORS if API is public, better to enable in general.
		// Anonymous is enabled by default.
		http.httpBasic().disable().csrf().disable().cors().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// open endpoints to login and to create an account
				.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/login", "/api/create_account")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/api/servers", "/api/serverAttributeNames")
				.permitAll().antMatchers(HttpMethod.DELETE, "/api/servers")
				.authenticated()
				// endpoints inside user and admin need corresponding roles
				.antMatchers("/api/me", "/api/user/**", "/api/admin/**").authenticated();
	}
}
