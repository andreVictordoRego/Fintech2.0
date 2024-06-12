package br.com.fiap.fintech.bean;

public class Extrato {

	private Double valorDoSaldoTotal;
	private Double valorTotalDoInvestimento;
	private Double valorDeEntradas;
	private Double valorDeSaidas;
	
	
	public Extrato() {
	}
	
	public Extrato(Double valorDoSaldoTotal, Double valorTotalDoInvestimento, Double valorDeEntradas,
			Double valorDeSaidas) {
		super();
		this.valorDoSaldoTotal = valorDoSaldoTotal;
		this.valorTotalDoInvestimento = valorTotalDoInvestimento;
		this.valorDeEntradas = valorDeEntradas;
		this.valorDeSaidas = valorDeSaidas;
	}
	public Double getValorDoSaldoTotal() {
		return valorDoSaldoTotal;
	}
	
	public void setValorDoSaldoTotal(Double valorDoSaldoTotal) {
		this.valorDoSaldoTotal = valorDoSaldoTotal;
	}
	
	public Double getValorTotalDoInvestimento() {
		return valorTotalDoInvestimento;
	}
	
	public void setValorTotalDoInvestimento(Double valorTotalDoInvestimento) {
		this.valorTotalDoInvestimento = valorTotalDoInvestimento;
	}
	
	public Double getValorDeEntradas() {
		return valorDeEntradas;
	}
	
	public void setValorDeEntradas(Double valorDeEntradas) {
		this.valorDeEntradas = valorDeEntradas;
	}
	
	public Double getValorDeSaidas() {
		return valorDeSaidas;
	}
	
	public void setValorDeSaidas(Double valorDeSaidas) {
		this.valorDeSaidas = valorDeSaidas;
	}
	
}
