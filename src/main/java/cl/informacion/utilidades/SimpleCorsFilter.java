package cl.informacion.utilidades;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component 
public class SimpleCorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse respuesta = (HttpServletResponse) response;
		respuesta.setHeader("Access-Control-Allow-Origin", "*");
		respuesta.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
		respuesta.setHeader("Access-Control-Max-Age", "3600");
		respuesta.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
		chain.doFilter(request, response);
		
	}
	public void destroy() {}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
