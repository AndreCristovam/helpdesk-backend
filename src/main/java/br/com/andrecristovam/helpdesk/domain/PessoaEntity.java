package br.com.andrecristovam.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.andrecristovam.helpdesk.domain.enums.EPerfil;


public abstract class PessoaEntity {
	
	protected Integer id;
	
	protected String nome;
	
	protected String cpf;
	
	protected String email;
	
	protected String senha;
	
	protected Set<Integer> perfis = new HashSet<>();
	
	protected LocalDate date = LocalDate.now();

	public PessoaEntity() {
		super();
		addPerfil(EPerfil.CLIENTE);
	}

	public PessoaEntity(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(EPerfil.CLIENTE);
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<EPerfil> getPerfis() {
		return perfis.stream().map(x -> EPerfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(EPerfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaEntity other = (PessoaEntity) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}
	
	
	

}
