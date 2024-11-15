package imb.lh_p3.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.clinica.entity.Consulta;
import imb.lh_p3.clinica.service.IConsultaService;
import imb.lh_p3.clinica.util.DTOResponse;
import imb.lh_p3.clinica.util.ResourceNotFoundException;
import jakarta.validation.Valid;

@RestController
public class ConsultaController {

	@Autowired
	IConsultaService service;

	@GetMapping("/consultas")
	ResponseEntity<DTOResponse<List<Consulta>>> mostrarTodasLasConsultas() {
		List<Consulta> lista = service.mostrarTodos();
		DTOResponse<List<Consulta>> dto = new DTOResponse<>(200, "", lista);

		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/consultas/{id}")
	ResponseEntity<DTOResponse<Consulta>> mostrarConsultaPorId(@PathVariable("id") Long id) {

		if (service.existe(id)) {
			DTOResponse<Consulta> dto = new DTOResponse<>(200, "", service.mostrarPorId(id));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		} else {
			throw new ResourceNotFoundException("No existe esta consulta: " + id);
		}
	}

	@PostMapping("/consultas")
	ResponseEntity<DTOResponse<Consulta>> crearConsulta(@Valid @RequestBody Consulta consulta) {
		if (service.existe(consulta.getId())) {
			throw new ResourceNotFoundException("Ya existe esta consulta: " + consulta.getId());
		} else {
			DTOResponse<Consulta> dto = new DTOResponse<>(201, "La consulta se creó con éxito", service.guardar(consulta));
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
	}

	@PutMapping("/consultas")
	ResponseEntity<DTOResponse<Consulta>> actualizarConsulta(@Valid @RequestBody Consulta consulta) {

		if (service.existe(consulta.getId())) {
			DTOResponse<Consulta> dto = new DTOResponse<>(200, "La consulta se actualizó con éxito", service.guardar(consulta));
			return ResponseEntity.ok().body(dto);
		} else {
			throw new ResourceNotFoundException("No existe esta consulta: " + consulta.getId());
		}
	}

	@DeleteMapping("/consultas/{id}")
	ResponseEntity<DTOResponse<?>> eliminarConsulta(@PathVariable("id") Long id) {
		if (service.existe(id)) {
			service.eliminar(id);
			DTOResponse<?> dtoSi = new DTOResponse<>(200, "La consulta se eliminó", null);
			return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
		} else {
			throw new ResourceNotFoundException("La consulta con ID " + id + " no existe.");
		}
	}
}
