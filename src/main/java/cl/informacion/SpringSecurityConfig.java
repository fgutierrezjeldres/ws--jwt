package cl.informacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cl.informacion.security.filter.JWTAutentificacionFilter;
import cl.informacion.security.filter.JWTAutorizacionFilter;
import cl.informacion.security.service.JWTService;
import cl.informacion.security.service.JpaUsuarioService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JpaUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
    private static final String[] SWAGGER = {
            // -- swagger ui
            "/v2/api-docs", 
            "/swagger-resources/**", 
            "/configuration/ui",
            "/configuration/security", 
            "/swagger-ui.html",
            "/webjars/**", 
            "/"
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(SWAGGER).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.addFilter(new JWTAutentificacionFilter(authenticationManager(), jwtService))
		.addFilter(new JWTAutorizacionFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

		/*String password = "personal1";
		String bcryptPassword = this.passwordEncoder.encode(password);
		this.logger.info("bcrypt code = " + bcryptPassword);*/

	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
