package cl.informacion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.informacion.model.dao.viatico.IViaticoDAO;
import cl.informacion.service.IViaticoService;

@Service
public class ViaticoServiceImpl implements IViaticoService {
	
	@Autowired
	IViaticoDAO daoViatico;

	@Override
	public Integer consultarExistenciaViatico(int rut, String fechaInicio, String fechaTermino) throws Exception {
		return daoViatico.consultarExistenciaViatico(rut, fechaInicio, fechaTermino);
	}
	

}
