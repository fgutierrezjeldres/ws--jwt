package cl.informacion.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.informacion.model.Resultado;
import cl.informacion.model.dao.remune.ILiquidacionDAO;
import cl.informacion.model.dao.remune.ILiquidacionDetalleDAO;
import cl.informacion.model.remune.Liquidacion;
import cl.informacion.model.remune.LiquidacionDetalle;
import cl.informacion.service.ILiquidacionService;

@Service
public class LiquidacionServiceImpl implements ILiquidacionService {
	
	@Autowired
	ILiquidacionDAO daoLiquidacion;
	
	@Autowired
	ILiquidacionDetalleDAO daoDetalle;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/*busco cabecera de la base de datos */
	@Override
	public List<Liquidacion> listarLiquidacion(int rut, int fecha)  {
		
		List<Liquidacion> resultado = null; 
		try {
			 resultado = daoLiquidacion.listarLiquidacion(rut, fecha);
			 
		}catch (Exception e) {
			List<Liquidacion> resultado2 = resultado;
			this.logger.error("Error ->", e.getMessage());
		}
		
		return resultado;
		
	}

	/*busco detalle de la base de datos */
	@Override
	public List<LiquidacionDetalle> listarDetalleLiquidacion(int rut, int fecha) throws Exception {
		return daoDetalle.listarDetalleLiquidacion(rut, fecha);
	}
	
	
	public List<Object> obtenerLiquidacion(int rut, int fecha) throws Exception {
		
		List<Object> listado = new ArrayList<>();
		List<Liquidacion> liquidaciones = listarLiquidacion(rut, fecha); 
		List<LiquidacionDetalle> detalleLiquidaciones = listarDetalleLiquidacion(rut, fecha);
		
		for (Liquidacion liquidacion : liquidaciones ) {
			Resultado<Object> resultado = new Resultado<Object>();
			Stream<LiquidacionDetalle> detalle = detalleLiquidaciones.stream()
					.filter(l -> (l.getTipoPago().equals(liquidacion.getTipoPago()) && l.getTipoFun().equals(liquidacion.getTipoFun())
							&& l.getTipoDetalle().equals(liquidacion.getTipoDetalle())));
			resultado.setLiquidacion(liquidacion);
			resultado.setDetalleLiquidacion(detalle.collect(Collectors.toList()));

			listado.add(resultado);
		}
		return listado;
	} 
}
