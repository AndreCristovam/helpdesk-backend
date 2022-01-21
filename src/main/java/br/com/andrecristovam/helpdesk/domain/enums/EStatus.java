package br.com.andrecristovam.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum EStatus {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	@Getter
	private Integer codigo;	
	@Getter
	private String descricao;	
	
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
