package pe.edu.upn.ProyectoWebFinal.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/index.html").permitAll()
				
				.antMatchers("/categoria/nuevo").hasRole("EMPLOYEE")
				.antMatchers("/categoria/del/**").hasRole("EMPLOYEE")
				.antMatchers("/categoria/edit/**").hasRole("EMPLOYEE")
				.antMatchers("/categoria/**/nuevomodelo").hasRole("EMPLOYEE")
				
				.antMatchers("/modelo/nuevo").hasRole("EMPLOYEE")
				.antMatchers("/modelo/del/**").hasRole("EMPLOYEE")
				.antMatchers("/modelo/edit/**").hasRole("EMPLOYEE")
				.antMatchers("/modelo/**/nuevoproducto").hasRole("EMPLOYEE")
				
				.antMatchers("/producto/nuevo").hasRole("EMPLOYEE")
				.antMatchers("/producto/del/**").hasRole("EMPLOYEE")
				.antMatchers("/producto/edit/**").hasRole("EMPLOYEE")
				
				
				.antMatchers("/usuario/del/**").hasRole("MANAGER")
				.antMatchers("/usuario/info/**").hasRole("MANAGER")
				.antMatchers("/usuario/**/nuevocarrito").hasRole("CUSTOMER")
				
				.antMatchers("/venta/nuevo/**").hasRole("CUSTOMER")
				
				.antMatchers("/carrito/edit/**").hasRole("CUSTOMER")
				.antMatchers("/carrito/del/**").hasRole("CUSTOMER")
				/*
				.antMatchers("/medico").authenticated()
				*/
				
				
				/*.antMatchers("/categoria/**").authenticated()   .hasRole("ADMIN")*/
				
				/* la opcion nuevo tienes que ser admin para entrar por url */
				/*
				
				.antMatchers("/medico/del/**").hasRole("ADMIN")
				*/
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login").permitAll()
				.usernameParameter("inputUsername")
                .passwordParameter("inputPassword")
			.and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        	.logoutSuccessUrl("/")
	        .and()
            .rememberMe()
            	.tokenValiditySeconds(2592000)
            	.key("Cl4v3.")
            	.rememberMeParameter("checkRememberMe")
            	.userDetailsService(usuarioDetailsService)
            .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);

        return daoAuthenticationProvider;
    }
	
}
