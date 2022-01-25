package br.com.andrecristovam.helpdesk.domain.enums;

public enum EStatus {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	
	private Integer codigo;	
	
	private String descricao;	
	
	
	
	private EStatus(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}



	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}


	public static EStatus toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for(EStatus status : EStatus.values()) {
			
			if (cod.equals(status.getCodigo())) {
				return status;
			}			
		}
		
		throw new IllegalArgumentException("Status inválido");
	}	
}
