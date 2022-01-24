package br.com.andrecristovam.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Chamado;
import br.com.andrecristovam.helpdesk.domain.Cliente;
import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.domain.dto.ChamadoDTO;
import br.com.andrecristovam.helpdesk.domain.enums.EPrioridade;
import br.com.andrecristovam.helpdesk.domain.enums.EStatus;
import br.com.andrecristovam.helpdesk.repositories.IChamadoRepository;
import br.com.andrecristovam.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private IChamadoRepository chamadoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = this.chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrato Id: " + id));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		return chamadoRepository.save(newChamado(objDTO));
	}
	

	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = this.findById(id);
		oldObj = newChamado(objDTO);
		return chamadoRepository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {

		Tecnico tecnico = this.tecnicoService.findById(obj.getTecnico());
		Cliente cliente = this.clienteService.findById(obj.getCliente());

		Chamado chamado = new Chamado();

		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(EPrioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(EStatus.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());

		return chamado;

	}

}
