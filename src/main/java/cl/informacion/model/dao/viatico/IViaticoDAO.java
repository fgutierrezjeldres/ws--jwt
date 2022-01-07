package cl.informacion.model.dao.viatico;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.informacion.model.viatico.Viatico;

public interface IViaticoDAO extends JpaRepository<Viatico, Long> {
	/*
	 * trae detalle de todos los contratos que tenga un funcionario
	 * 
	 * @param rut int , fecha int
	 * 
	 * @return list (liquidacion detalle)
	 * 
	 */
	@Query(value = "{ CALL SP_CONSULTAR_EXISTENCIA_VIATICOS(:rutIn, :fechaInicioIn, :fechaTerminoIn)}", nativeQuery = true)
	Integer consultarExistenciaViatico (@Param("rutIn") int rut, @Param("fechaInicioIn") String fechaInicio, @Param("fechaTerminoIn") String fechaTermino);

}
