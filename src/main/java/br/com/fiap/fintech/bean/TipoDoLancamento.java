package br.com.fiap.fintech.bean;

public enum TipoDoLancamento {

	GASTO("Gasto"),
	RECEBIMENTO("Recebimento");
	
	private String tipoDoLancamento;
	
	private TipoDoLancamento(String tipoDoLancamento) {
		this.tipoDoLancamento = tipoDoLancamento;
	}
	
	public String getTipoDoLancamento() {
		return this.tipoDoLancamento;
	}
}