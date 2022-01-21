package br.com.andrecristovam.helpdesk.domain;

import java.time.LocalDate;
import java.util.Objects;

import br.com.andrecristovam.helpdesk.domain.enums.EPrioridade;
import br.com.andrecristovam.helpdesk.domain.enums.EStatus;

public class ChamadoEntity {
	
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private EPrioridade prioridade;
	private EStatus status;
	private String titulo;
	private String observacoes;
	
	private TecnicoEntity tecnico;
	private ClienteEntity cliente;
	
	
	public ChamadoEntity() {
		super();
	}


	public ChamadoEntity(Integer id, EPrioridade prioridade, EStatus status, String titulo, String observacoes,
			TecnicoEntity tecnico, ClienteEntity cliente) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}


	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}


	public LocalDate getDataFechamento() {
		return dataFechamento;
	}


	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}


	public EPrioridade getPrioridade() {
		return prioridade;
	}


	public void setPrioridade(EPrioridade prioridade) {
		this.prioridade = prioridade;
	}


	public EStatus getStatus() {
		return status;
	}


	public void setStatus(EStatus status) {
		this.status = status;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public TecnicoEntity getTecnico() {
		return tecnico;
	}


	public void setTecnico(TecnicoEntity tecnico) {
		this.tecnico = tecnico;
	}


	public ClienteEntity getCliente() {
		return cliente;
	}


	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChamadoEntity other = (ChamadoEntity) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
