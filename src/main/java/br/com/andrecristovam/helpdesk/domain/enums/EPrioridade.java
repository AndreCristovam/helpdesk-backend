package br.com.andrecristovam.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum EPrioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	@Getter
	private Integer codigo;	
	@Getter
	private String descricao;	
	
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
