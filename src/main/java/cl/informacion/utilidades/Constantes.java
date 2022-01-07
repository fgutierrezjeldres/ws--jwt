package cl.informacion.utilidades;

import org.springframework.util.Base64Utils;

public interface Constantes {

	public static final String SECRET = Base64Utils.encodeToString("R3muNer@ci°nes".getBytes());

	public static final long EXPIRATION_DATE = 14000000L;

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";

	default int separadorRut(String rut) {
		String texto = rut.substring(0, rut.length() - 1);
		return Integer.parseInt(texto);
	}

}
