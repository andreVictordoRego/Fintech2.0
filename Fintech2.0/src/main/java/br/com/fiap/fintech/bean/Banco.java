package br.com.fiap.fintech.bean;

public enum Banco {

	BRADESCO("Bradesco"),
	ITAU("Itau"),
	SANTANDER("Santander"),
	BANCO_DO_BRASIL("Banco do Brasil"),
	CAIXA_ECONOMICA("Caixa Economica"),
	NUBANK("Nubank"),
	NEXT("Next"),
	INTER("Inter"),
	C6_BANK("C6 Bank");
	
	private String banco;
	
	Banco (String banco){
		this.banco = banco;
	}
	
	public String getBanco(){
		return banco;
	}
}
