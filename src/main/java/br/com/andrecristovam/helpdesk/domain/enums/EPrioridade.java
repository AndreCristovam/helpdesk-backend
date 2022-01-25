package br.com.andrecristovam.helpdesk.domain.enums;

public enum EPrioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	
	private Integer codigo;	
	
	private String descricao;	
	
	
	
	private EPrioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}


	public static EPrioridade toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for(EPrioridade prioridade : EPrioridade.values()) {
			
			if (cod.equals(prioridade.getCodigo())) {
				return prioridade;
			}			
		}
		
		throw new IllegalArgumentException("Prioridade inválido");
	}	
}
