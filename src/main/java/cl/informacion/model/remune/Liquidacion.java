package cl.informacion.model.remune;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Liquidacion implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String traceNumber;

	private int fecha;

	private String rut;

	private String tipoFun;

	private String tipoDetalle;

	private String paterno;

	private String materno;

	private String nombres;

	private String reparticion;

	private String gradoEmpleo;

	private String gradoSueldo;

	private int gradoCongelado;

	private String trienios;

	private String tipoPago;
	
	private static final long serialVersionUID = 1L;

	public Liquidacion() {
	}

	public Liquidacion(long id, String traceNumber, int fecha, String rut, String tipoFun, String tipoDetalle,
			String paterno, String materno, String nombres, String reparticion, String gradoEmpleo, String gradoSueldo,
			int gradoCongelado, String trienios, String tipoPago) {
		
		this.id = id;
		this.traceNumber = traceNumber;
		this.fecha = fecha;
		this.rut = rut;
		this.tipoFun = tipoFun;
		this.tipoDetalle = tipoDetalle;
		this.paterno = paterno;
		this.materno = materno;
		this.nombres = nombres;
		this.reparticion = reparticion;
		this.gradoEmpleo = gradoEmpleo;
		this.gradoSueldo = gradoSueldo;
		this.gradoCongelado = gradoCongelado;
		this.trienios = trienios;
		this.tipoPago = tipoPago;
		
		
		
	}

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTraceNumber() {
		return this.traceNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	public int getFecha() {
		return this.fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTipoFun() {
		return this.tipoFun;
	}

	public void setTipoFun(String tipoFun) {
		this.tipoFun = tipoFun;
	}

	public String getTipoDetalle() {
		return this.tipoDetalle;
	}

	public void setTipoDetalle(String tipoDetalle) {
		this.tipoDetalle = tipoDetalle;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getReparticion() {
		return this.reparticion;
	}

	public void setReparticion(String reparticion) {
		this.reparticion = reparticion;
	}

	public String getGradoEmpleo() {
		return this.gradoEmpleo;
	}

	public void setGradoEmpleo(String gradoEmpleo) {
		this.gradoEmpleo = gradoEmpleo;
	}

	public String getGradoSueldo() {
		return this.gradoSueldo;
	}

	public void setGradoSueldo(String gradoSueldo) {
		this.gradoSueldo = gradoSueldo;
	}

	public int getGradoCongelado() {
		return this.gradoCongelado;
	}

	public void setGradoCongelado(int gradoCongelado) {
		this.gradoCongelado = gradoCongelado;
	}

	public String getTrienios() {
		return this.trienios;
	}

	public void setTrienios(String trienios) {
		this.trienios = trienios;
	}

	public String getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

}
