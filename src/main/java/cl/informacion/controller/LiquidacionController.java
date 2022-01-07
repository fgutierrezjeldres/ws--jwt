package cl.informacion.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.informacion.service.ILiquidacionService;
import cl.informacion.utilidades.Constantes;

@RestController
@RequestMapping({ "/pensiones" })
public class LiquidacionController implements Constantes {

	@Autowired
	private ILiquidacionService service;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@CrossOrigin
	@GetMapping(value = { "/getLiquidacion/{rutFuncionario}/{fecha}" }, produces = { "application/json" })
	@Secured("ROLE_PENSION")
	public ResponseEntity<List<Object>> obtenerLiquidacion(@PathVariable("rutFuncionario") String rut,
			@PathVariable("fecha") int fecha) {
		List<Object> resultado = new ArrayList<>();
		try {
			int rutFuncionario = separadorRut(rut);
			resultado = service.obtenerLiquidacion(rutFuncionario, fecha);
		} catch (Exception e) {
			String error = e.getMessage();
			this.logger.error("Error ->", error);
			return new ResponseEntity<List<Object>>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Object>>(resultado, HttpStatus.OK);
	}
	
}
