package br.com.fiap.fintech.bean;

public enum CategoriaDoLancamento {
	
	ALUGUEL("Aluguel"),
	CONDOMINIO("Condomínio"),
	MERCADO("Mercado"),
	COMBUSTIVEL("Combustível"),
	MEDICAMENTOS("Medicamentos"),
	PLANO_DE_CELULAR("Plano de Celular"),
	INTERNET("Internet"),
	SERVICO_DE_STREAMING("Serviço de Streaming"),
	SEGURO_RESIDENCIAL("Seguro Residencial"),
	SEGURO_AUTO("Seguro Auto"),
	SALARIO("Salário"),
	EMPRESTIMO("Empréstimo"),
	CHEQUE_ESPECIAL("Cheque Especial"),
	RENDIMENTO_DA_POUPANCA("Rendimento da Poupança"),
	VENDA("Venda");
	
	private String categoriaDoLancamento;

	private CategoriaDoLancamento(String categoriaDoLancamento) {
		this.categoriaDoLancamento = categoriaDoLancamento;
	}
	
	public String getCategoriaDoLancamento() {
		return categoriaDoLancamento;
	}

}
