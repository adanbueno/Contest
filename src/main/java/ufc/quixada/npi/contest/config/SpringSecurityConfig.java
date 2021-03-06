package ufc.quixada.npi.contest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
            .antMatchers("/coautor*", "/cadastro*", "/resetar-senha/**", "/esqueci-minha-senha", "/completar-cadastro/**", "/logout").permitAll()
            .antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()
            .antMatchers("/autor/file/**").permitAll()
			.antMatchers("/participacao/**").permitAll()
            /*.antMatchers("/autor/**").hasRole("USER")
            .antMatchers("/coautor/**").hasRole("USER")
            .antMatchers("/evento/{\\d+}/sessao/**").hasRole("USER")
            .antMatchers("/evento/**").hasRole("ADMIN")
            .antMatchers("/sessao/**").hasRole("USER")
            .antMatchers("/eventoOrganizador/**").hasRole("USER")
            .antMatchers("/revisor/**").hasRole("USER")*/
            .anyRequest()
            .fullyAuthenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .failureUrl("/loginfailed")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/login")
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/*@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}*/
	
}