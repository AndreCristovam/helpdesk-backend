package br.com.andrecristovam.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Pessoa;
import br.com.andrecristovam.helpdesk.domain.Cliente;
import br.com.andrecristovam.helpdesk.domain.dto.ClienteDTO;
import br.com.andrecristovam.helpdesk.repositories.IPessoaRepository;
import br.com.andrecristovam.helpdesk.repositories.IClienteRepository;
import br.com.andrecristovam.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.andrecristovam.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private IClienteRepository tecnicoRepository;
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = tecnicoRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: "+ id));
	}

	public List<Cliente> findAll() {
		return this.tecnicoRepository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(this.encoder.encode(objDTO.getSenha()));
		validaCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return this.tecnicoRepository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = this.findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(this.encoder.encode(objDTO.getSenha()));
		}
		
		validaCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return this.tecnicoRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		
		Cliente obj = this.findById(id);
		
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		
		this.tecnicoRepository.deleteById(id);
	}


	private void validaCpfEEmail(ClienteDTO objDTO) {
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
