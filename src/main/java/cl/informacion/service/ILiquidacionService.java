package cl.informacion.service;

import java.util.List;

import cl.informacion.model.remune.Liquidacion;
import cl.informacion.model.remune.LiquidacionDetalle;

public interface ILiquidacionService {
	
	List<Liquidacion> listarLiquidacion(int rut, int fecha) throws Exception;
	List<LiquidacionDetalle> listarDetalleLiquidacion(int rut, int fecha) throws Exception;
	List<Object> obtenerLiquidacion(int rut, int fecha) throws Exception;

}
