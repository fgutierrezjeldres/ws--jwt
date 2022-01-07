package cl.informacion.model.dao.remune;

import org.springframework.data.repository.CrudRepository;

import cl.informacion.model.remune.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

	public Usuario findByUsuario(String usuario);
}
