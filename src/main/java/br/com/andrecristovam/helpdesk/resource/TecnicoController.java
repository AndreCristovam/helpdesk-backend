package br.com.andrecristovam.helpdesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.domain.dto.TecnicoDTO;
import br.com.andrecristovam.helpdesk.services.tecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController{
	
	@Autowired
	private tecnicoService tecnicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = this.tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

}
