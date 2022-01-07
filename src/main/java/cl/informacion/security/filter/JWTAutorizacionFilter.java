package cl.informacion.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import cl.informacion.security.service.JWTService;
import cl.informacion.utilidades.Constantes;

public class JWTAutorizacionFilter extends BasicAuthenticationFilter{

	private JWTService jwtService;
	
	public JWTAutorizacionFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest consulta, HttpServletResponse respuesta, FilterChain chain)
			throws IOException, ServletException {

		String header = consulta.getHeader(Constantes.HEADER_STRING);

		if (!requiresAuthentication(header)) {
			chain.doFilter(consulta, respuesta);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = null;
		
		if(jwtService.validar(header)) {
			authentication = new UsernamePasswordAuthenticationToken(jwtService.obtenerUsuario(header), null, jwtService.obtenerRoles(header));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(consulta, respuesta);
		
	}

	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith(Constantes.TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}

}
