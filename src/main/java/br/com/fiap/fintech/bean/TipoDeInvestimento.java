package br.com.fiap.fintech.bean;

public enum TipoDeInvestimento {
	
	POUPANCA("Poupanca"),
	TESOURO_DIRETO("Tesouro Direto"),
	CDB("CDB"),
	CRI("CRI"),
	LCA("LCA"),
	LCI("LCI");
	
	private String tipoDeInvestimento;
	
	TipoDeInvestimento (String tipoDeInvestimento){
		this.tipoDeInvestimento = tipoDeInvestimento;
	}
	
	public String getTipoDeInvestimento(){
		return tipoDeInvestimento;
	}

}
