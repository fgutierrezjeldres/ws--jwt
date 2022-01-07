package cl.informacion.model.remune;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "rol", uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "rol" }) })
public class Rol implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 60)
	private String rol;
	
	@Column(length = 200)
	private String descripcion;
	
	private static final long serialVersionUID = 1L;
	
	public Rol() {
		
	}

	public Rol(Long id, String rol, String descripcion) {
		this.id = id;
		this.rol = rol;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}