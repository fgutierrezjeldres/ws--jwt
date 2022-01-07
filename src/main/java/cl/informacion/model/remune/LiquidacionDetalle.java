package cl.informacion.model.remune;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LiquidacionDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int fecha;

	private int rut;

	private String tipoFun;

	private String tipoDetalle;

	private int correlativo;

	private int codigoConcepto;

	private String nombreConcepto;

	private String letraConcepto;

	private String imponible;

	private int valor;

	private String tipoPago;
	
	private static final long serialVersionUID = 1L;
	
	public LiquidacionDetalle() {
	}

	public LiquidacionDetalle(long id, int fecha, int rut, String tipoFun, String tipoDetalle, int correlativo,
			int codigoConcepto, String nombreConcepto, String letraConcepto, String imponible, int valor,
			String tipoPago) {
		this.id = id;
		this.fecha = fecha;
		this.rut = rut;
		this.tipoFun = tipoFun;
		this.tipoDetalle = tipoDetalle;
		this.correlativo = correlativo;
		this.codigoConcepto = codigoConcepto;
		this.nombreConcepto = nombreConcepto;
		this.letraConcepto = letraConcepto;
		this.imponible = imponible;
		this.valor = valor;
		this.tipoPago = tipoPago;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFecha() {
		return this.fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public int getRut() {
		return this.rut;
	}

	public void setRut(int rut) {
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

	public int getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	public int getcodigoConcepto() {
		return this.codigoConcepto;
	}

	public void setcodigoConcepto(int codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public String getNombreConcepto() {
		return this.nombreConcepto;
	}

	public void setNombreConcepto(String nombreConcepto) {
		this.nombreConcepto = nombreConcepto;
	}

	public String getLetraConcepto() {
		return this.letraConcepto;
	}

	public void setLetraConcepto(String letraConcepto) {
		this.letraConcepto = letraConcepto;
	}

	public String getImponible() {
		return this.imponible;
	}

	public void setImponible(String imponible) {
		this.imponible = imponible;
	}

	public int getValor() {
		return this.valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
}
