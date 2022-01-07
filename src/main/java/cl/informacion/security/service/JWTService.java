package cl.informacion.security.service;


import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Claims;

public interface JWTService {
	
	public String crear(Authentication autentificacion) throws IOException;
	public boolean validar(String token);
	public Claims obtenerClaims(String token);
	public String obtenerUsuario(String token);
	public Collection<? extends GrantedAuthority> obtenerRoles(String token) throws IOException;
	public String respuesta(String token);

}
