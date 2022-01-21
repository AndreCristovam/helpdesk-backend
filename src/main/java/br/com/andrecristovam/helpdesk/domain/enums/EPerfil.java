package br.com.andrecristovam.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum EPerfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	@Getter
	private Integer codigo;	
	@Getter
	private String descricao;	
	
	public static EPerfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for(EPerfil perfil : EPerfil.values()) {
			
			if (cod.equals(perfil.getCodigo())) {
				return perfil;
			}			
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}	
}
