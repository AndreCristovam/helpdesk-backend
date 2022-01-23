package br.com.andrecristovam.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.repositories.ITecnicoRepository;
import br.com.andrecristovam.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class tecnicoService {

	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id));
	}
}
