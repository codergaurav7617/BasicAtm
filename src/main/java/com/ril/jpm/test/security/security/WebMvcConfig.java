package com.ril.jpm.test.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class WebMvcConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {

	@Autowired
	private DataSource dataSource;

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("Transaction_Type");
		registry.addViewController("/hello").setViewName("sucess");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	protected void configure(HttpSecurity auth) throws Exception {
		auth
		.authorizeRequests()
			.antMatchers("/", "/home","/h2-console/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();

	}


	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	 @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {

        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password , enabled "+
                        "from users where username = ?"
                )
                .authoritiesByUsernameQuery(
                        "select username,authority "+
                        "from authorities where username = ?"
                );
    }
}