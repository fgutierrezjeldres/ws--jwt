package cl.informacion.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.informacion.service.IViaticoService;

@RestController
@RequestMapping({"/viaticos"})
public class ViaticoController {
	
	@Autowired
	private IViaticoService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	 @CrossOrigin
	 @GetMapping(value = {"/consultarExistenciaViaticos/{rut}/{fechaInicio}/{fechaTermino}"}, produces = {"application/json"})
	 @Secured("ROLE_PERSONAL")
	 public ResponseEntity<Integer> consultarExistenciaViaticos(@PathVariable("rut") int rut, @PathVariable("fechaInicio") String fechaInicio, 
			 @PathVariable("fechaTermino") String fechaTermino) {
		 Integer resultado = null; 
		 try {
		
			 resultado = service.consultarExistenciaViatico(rut, fechaInicio, fechaTermino);
		 }catch(Exception e) {
			 String error = e.getMessage();
			 this.logger.error("Error ->", error);
			 return new ResponseEntity<Integer> (resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 return new ResponseEntity<Integer> (resultado, HttpStatus.OK);
	 }

}
