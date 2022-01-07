package cl.informacion.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.informacion.model.remune.Usuario;
import cl.informacion.security.service.JWTService;
import cl.informacion.utilidades.Constantes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JWTAutentificacionFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager autentificacionManager;
	private JWTService jwtService;
	/*
	 * constructor
	 */

	public JWTAutentificacionFilter(AuthenticationManager autentificacionManager, JWTService jwtService) {

		this.autentificacionManager = autentificacionManager;
		this.jwtService = jwtService;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String usuario = obtainUsername(request);
		String contrasena = obtainPassword(request);

		if (usuario != null && contrasena != null) {
			logger.info("usuario encontrado  desde consulta (form-data): ");
			//logger.info("contrasena desde consulta (form-data): " + contrasena);

		} else {
			Usuario u = null;
			try {
				u = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);// esto falla 
				usuario = u.getUsuario();
				contrasena = u.getContrasena();
				
				logger.info("usuario encontrado  desde InputStream (raw): ");

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		usuario = usuario.trim();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario, contrasena);
		return autentificacionManager.authenticate(token);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.crear(authResult);
		
		response.addHeader(Constantes.HEADER_STRING, Constantes.TOKEN_PREFIX + token);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("usuario", (User) authResult.getPrincipal());
		body.put("mensaje", String.format("inicio de sesion realizado!", ((User)authResult.getPrincipal()).getUsername()));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Error de autenticación: usuario o contrasena incorrecto!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}

	
}
