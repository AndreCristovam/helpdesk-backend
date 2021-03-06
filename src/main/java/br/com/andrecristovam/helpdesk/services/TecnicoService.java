package br.com.andrecristovam.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Pessoa;
import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.domain.dto.TecnicoDTO;
import br.com.andrecristovam.helpdesk.repositories.IPessoaRepository;
import br.com.andrecristovam.helpdesk.repositories.ITecnicoRepository;
import br.com.andrecristovam.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.andrecristovam.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id));
	}

	public List<Tecnico> findAll() {
		return this.tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(this.encoder.encode(objDTO.getSenha()));
		validaCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return this.tecnicoRepository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(this.encoder.encode(objDTO.getSenha()));
		}
		
		validaCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return this.tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		
		Tecnico obj = this.findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
		}
		
		this.tecnicoRepository.deleteById(id);
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
