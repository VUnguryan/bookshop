package ua.step.bookshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
	/*@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance())
				.usersByUsernameQuery("select login, password, id from users where login=?").authoritiesByUsernameQuery(
						"select users.login, roles.roles from ((users inner join users_has_roles on users.id=users_has_roles.users_id)"
								+ " inner join roles on roles.id=users_has_roles.roles_id) where users.login = ?");

	}*/

	@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
