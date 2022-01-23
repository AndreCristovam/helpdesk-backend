package br.com.andrecristovam.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Pessoa;
import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.domain.dto.TecnicoDTO;
import br.com.andrecristovam.helpdesk.repositories.IPessoaRepository;
import br.com.andrecristovam.helpdesk.repositories.ITecnicoRepository;
import br.com.andrecristovam.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.andrecristovam.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class tecnicoService {

	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id));
	}

	public List<Tecnico> findAll() {
		return this.tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return this.tecnicoRepository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return this.tecnicoRepository.save(oldObj);
	}

	private void validaCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}

	
}
