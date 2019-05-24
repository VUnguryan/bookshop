package ua.step.bookshop.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/authors","/publishers","/payment","/delivery","/contacts","/registration").permitAll()
			.antMatchers("/authors/add","/publishers/add").hasAuthority("admin")
			.anyRequest().fullyAuthenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.permitAll()
		.and()
				.logout()
				.logoutUrl("/logout")
				.deleteCookies("remember-me")
				.logoutSuccessUrl("/login")
				.permitAll();
	}

	/**
	 * !!!!DENGER!!!!
	 *  Не трогай 
	 * !!!УБЬЕТ!!!
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
				.usersByUsernameQuery("select login, password, id from users where login=?").authoritiesByUsernameQuery(
						"select users.login, roles.roles from ((users inner join users_has_roles on users.id=users_has_roles.users_id)"
								+ " inner join roles on roles.id=users_has_roles.roles_id) where users.login = ?");

	}
}
