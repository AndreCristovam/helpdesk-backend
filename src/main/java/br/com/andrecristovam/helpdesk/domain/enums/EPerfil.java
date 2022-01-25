package br.com.andrecristovam.helpdesk.domain.enums;

public enum EPerfil {

	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");
	
	
	private Integer codigo;	
	
	private String descricao;	
	
	
	
	private EPerfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}


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
