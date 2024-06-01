package br.com.fiap.fintech.model.bean;

public class Recebimento extends Lancamento {

	private CategoriaDoRecebimento categoriaDoRecebimento;

	public Recebimento() {
		
	}
	
	public Recebimento(CategoriaDoRecebimento categoriaDoRecebimento) {
		this.categoriaDoRecebimento = categoriaDoRecebimento;
	}

	public CategoriaDoRecebimento getCategoriaDoRecebimento() {
		return categoriaDoRecebimento;
	}

	public void setCategoriaDoRecebimento(CategoriaDoRecebimento categoriaDoRecebimento) {
		this.categoriaDoRecebimento = categoriaDoRecebimento;
	}

}
