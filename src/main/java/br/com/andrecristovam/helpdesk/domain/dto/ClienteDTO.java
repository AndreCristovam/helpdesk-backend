package br.com.andrecristovam.helpdesk.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.andrecristovam.helpdesk.domain.Cliente;
import br.com.andrecristovam.helpdesk.domain.enums.EPerfil;
import lombok.Data;

@Data
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	

	protected Integer id;
	@NotNull(message = "O campo NOME é requerido")
	protected String nome;	
	@NotNull(message = "O campo CPF é requerido")
	protected String cpf;
	@NotNull(message = "O campo E-MAIL é requerido")
	protected String email;	
	@NotNull(message = "O campo SENHA é requerido")
	protected String senha;	
	protected Set<Integer> perfis = new HashSet<>();	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public ClienteDTO() {
		super();
		addPerfis(EPerfil.CLIENTE);
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
		addPerfis(EPerfil.CLIENTE);
	}	

	public Set<EPerfil> getPerfis() {
		return perfis.stream().map(x -> EPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfis(EPerfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}	
	

}
