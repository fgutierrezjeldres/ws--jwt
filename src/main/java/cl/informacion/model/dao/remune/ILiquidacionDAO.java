package cl.informacion.model.dao.remune;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.informacion.model.remune.Liquidacion;

@Repository
public interface ILiquidacionDAO extends JpaRepository<Liquidacion, Long> {

	/*
	 * trae cabecera de todos los contratos que tenga un funcionario
	 * 
	 * @param rut int , fecha int
	 * 
	 * @return list (liquidacion)
	 * 
	 */
	@Query(value = "{ CALL CONSULTAR_LIQUIDACION_CABECERA(:rutIn, :fechaIn)}", nativeQuery = true)
	List<Liquidacion> listarLiquidacion(@Param("rutIn") int rut, @Param("fechaIn") int fecha);


}
