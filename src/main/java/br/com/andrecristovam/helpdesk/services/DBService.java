package br.com.andrecristovam.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrecristovam.helpdesk.domain.Chamado;
import br.com.andrecristovam.helpdesk.domain.Cliente;
import br.com.andrecristovam.helpdesk.domain.Tecnico;
import br.com.andrecristovam.helpdesk.domain.enums.EPerfil;
import br.com.andrecristovam.helpdesk.domain.enums.EPrioridade;
import br.com.andrecristovam.helpdesk.domain.enums.EStatus;
import br.com.andrecristovam.helpdesk.repositories.IChamadoRepository;
import br.com.andrecristovam.helpdesk.repositories.IClienteRepository;
import br.com.andrecristovam.helpdesk.repositories.ITecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ITecnicoRepository tecnicoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IChamadoRepository chamadoRepository;
	

	public void instanciaDB() {

		Tecnico tecn1 = new Tecnico(null, "Tomas Shelby", "123.456.789-00", "tomas@mail.com", "1234");
		tecn1.addPerfil(EPerfil.ADMIN);		
		tecnicoRepository.saveAll(Arrays.asList(tecn1));
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "123.456.789-01", "linus@mail.com", "1234");
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		Chamado cha1 = new Chamado(null, EPrioridade.MEDIA, EStatus.ANDAMENTO, "Chamado 01","Primeiro chamado", tecn1, cli1);
		chamadoRepository.saveAll(Arrays.asList(cha1));
	}
}
