package br.com.andrecristovam.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	

	public void instanciaDB() {

		Tecnico tec1 = new Tecnico(null, "André Cristovam", "123.456.789-00", "andre@mail.com", encoder.encode("123456"));
		tec1.addPerfil(EPerfil.ADMIN);		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		
		Tecnico tec2 = new Tecnico(null, "Sandro Junior", "903.347.070-56", "sandro@mail.com", encoder.encode("123"));
		Tecnico tec3 = new Tecnico(null, "Vagner Yukio", "271.068.470-54", "vagner@mail.com", encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Douglas Bocchino", "162.720.120-39", "douglas@mail.com", encoder.encode("123"));
		Tecnico tec5 = new Tecnico(null, "Amanda Maria", "778.556.170-27", "amanda@mail.com", encoder.encode("123"));
		Tecnico tec6 = new Tecnico(null, "Karina Carrara", "801.556.190-27", "karina@mail.com", encoder.encode("123"));
		Tecnico tec7 = new Tecnico(null, "Raquel Soares", "981.597.170-88", "raquel@mail.com", encoder.encode("123"));
		Tecnico tec8 = new Tecnico(null, "Danilo Dias", "400.200.170-06", "danilo@mail.com", encoder.encode("123"));

		Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
		Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
 
		Chamado c1 = new Chamado(null, EPrioridade.MEDIA, EStatus.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
		Chamado c2 = new Chamado(null, EPrioridade.ALTA, EStatus.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
		Chamado c3 = new Chamado(null, EPrioridade.BAIXA, EStatus.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
		Chamado c4 = new Chamado(null, EPrioridade.ALTA, EStatus.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
		Chamado c5 = new Chamado(null, EPrioridade.MEDIA, EStatus.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
		Chamado c6 = new Chamado(null, EPrioridade.BAIXA, EStatus.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6, tec7, tec8));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
	}
}
