package cl.informacion.model;

import java.util.List;

public class Resultado <T> {
	
	private T liquidacion;
	private List<T> detalleLiquidacion;
	
	public Resultado () {
		
	}
	
	public T getLiquidacion() {
		return liquidacion;
	}
	public void setLiquidacion(T liquidacion) {
		this.liquidacion = liquidacion;
	}
	public List<T> getDetalleLiquidacion() {
		return detalleLiquidacion;
	}
	public void setDetalleLiquidacion(List<T> detalleLiquidacion) {
		this.detalleLiquidacion = detalleLiquidacion;
	}
	

}
