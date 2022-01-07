package cl.informacion.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.informacion.model.dao.remune.IUsuarioDAO;
import cl.informacion.model.remune.Rol;
import cl.informacion.model.remune.Usuario;

@Service("JpaUsuarioService")
public class JpaUsuarioService implements UserDetailsService {

	@Autowired
	private IUsuarioDAO usuarioDao;
	
	private Logger logger =  LoggerFactory.getLogger(JpaUsuarioService.class);
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario u = usuarioDao.findByUsuario(usuario);
		
		if (u == null) {
			logger.error("error en el ingreso:  no existe el usuario '"+ u +"' en el sistema!");
			throw new UsernameNotFoundException("Usuario: "+ u +" no existe en el sistema!");
		}

        List<GrantedAuthority> perfiles = new ArrayList<GrantedAuthority>();
        for(Rol rol: u.getRoles()) {
        	logger.info("Rol: ".concat(rol.getRol()));
        	perfiles.add(new SimpleGrantedAuthority("ROLE_"+rol.getRol().toUpperCase()));
        }
        
        if(perfiles.isEmpty()) {
        	logger.error("error en el ingreso: el usuario '"+ u +"' no tiene roles asignados");
        	throw new UsernameNotFoundException("Error en el ingreso: usuario '" + u + "' no tiene roles asignados!");
        }
        
        return new User(u.getUsuario(), u.getContrasena(), u.getActivo(), true, true, true, perfiles); 
        
	}

}
