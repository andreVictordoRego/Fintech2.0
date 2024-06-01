package br.com.fiap.fintech.model.bean;

public final class Gasto extends Lancamento {

	private CategoriaDeGasto categoriaDeGasto;

	public Gasto() {
		
	}
	
	public Gasto(CategoriaDeGasto categoriaDeGasto) {
		this.categoriaDeGasto = categoriaDeGasto;
	}

	public CategoriaDeGasto getCategoriaDeGasto() {
		return categoriaDeGasto;
	}

	public void setCategoriaDeGasto(CategoriaDeGasto categoriaDeGasto) {
		this.categoriaDeGasto = categoriaDeGasto;
	}
	
}
