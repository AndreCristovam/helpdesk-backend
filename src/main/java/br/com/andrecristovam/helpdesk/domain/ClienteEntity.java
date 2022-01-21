package br.com.andrecristovam.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class ClienteEntity extends PessoaEntity {
	
	private List<ChamadoEntity> chamados = new ArrayList<>();

	public ClienteEntity() {
		super();
	}

	public ClienteEntity(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public List<ChamadoEntity> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadoEntity> chamados) {
		this.chamados = chamados;
	}
	
	

}
