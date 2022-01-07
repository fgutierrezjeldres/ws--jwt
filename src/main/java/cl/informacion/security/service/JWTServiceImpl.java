package cl.informacion.security.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.informacion.security.SimpleGrantedAuthorityMixin;
import cl.informacion.utilidades.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {

	@Override
	public String crear(Authentication autentificacion) throws IOException {

		String usuario = ((User) autentificacion.getPrincipal()).getUsername();

		Collection<? extends GrantedAuthority> roles = autentificacion.getAuthorities();//falla aca 
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		
		String token = Jwts.builder().setClaims(claims).setSubject(usuario)
				.signWith(SignatureAlgorithm.HS512, Constantes.SECRET.getBytes()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Constantes.EXPIRATION_DATE)).compact();
		return token;
	}

	@Override
	public boolean validar(String token) {
		try {
			obtenerClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims obtenerClaims(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().setSigningKey(Constantes.SECRET.getBytes())
				.parseClaimsJws(respuesta(token)).getBody();
		return claims;
	}

	@Override
	public String obtenerUsuario(String token) {
		return obtenerClaims(token).getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> obtenerRoles(String token) throws IOException {
		Object roles = obtenerClaims(token).get("authorities");
 
		Collection<? extends GrantedAuthority> perfiles = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return perfiles;
	}

	@Override
	public String respuesta(String token) {
		
		if (token != null && token.startsWith(Constantes.TOKEN_PREFIX)) {
			return token.replace(Constantes.TOKEN_PREFIX, "");
		}
		return null;
	}

}
