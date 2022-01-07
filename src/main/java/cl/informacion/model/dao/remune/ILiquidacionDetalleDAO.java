package cl.informacion.model.dao.remune;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.informacion.model.remune.LiquidacionDetalle;

@Repository
public interface ILiquidacionDetalleDAO extends JpaRepository<LiquidacionDetalle, Long> {
	
	/*
	 * trae detalle de todos los contratos que tenga un funcionario
	 * 
	 * @param rut int , fecha int
	 * 
	 * @return list (liquidacion detalle)
	 * 
	 */
	@Query(value = "{ CALL CONSULTAR_LIQUIDACION_DETALLE(:rutIn, :fechaIn)}", nativeQuery = true)
	List<LiquidacionDetalle> listarDetalleLiquidacion(@Param("rutIn") int rut, @Param("fechaIn") int fecha);

}
