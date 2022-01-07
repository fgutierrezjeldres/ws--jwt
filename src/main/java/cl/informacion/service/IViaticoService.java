package cl.informacion.service;


public interface IViaticoService {
	
	Integer consultarExistenciaViatico (int rut, String fechaInicio, String fechaTermino) throws Exception;

}
