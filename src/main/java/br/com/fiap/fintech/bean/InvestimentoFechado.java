package br.com.fiap.fintech.bean;

import java.time.LocalDate;

public class InvestimentoFechado {
	
	private LocalDate dataDeSaida;
	private Double valorDaLiquidez;
	private Integer chavePrimariaEstrantrangeira;
	
	public InvestimentoFechado() {
		
	}

	public InvestimentoFechado(LocalDate dataDeSaida, Double valorDaLiquidez) {
		super();
		this.dataDeSaida = dataDeSaida;
		this.valorDaLiquidez = valorDaLiquidez;
	}

	public LocalDate getDataDeSaida() {
		return dataDeSaida;
	}

	public void setDataDeSaida(LocalDate dataDeSaida) {
		this.dataDeSaida = dataDeSaida;
	}

	public Double getValorDaLiquidez() {
		return valorDaLiquidez;
	}

	public void setValorDaLiquidez(Double valorDaLiquidez) {
		this.valorDaLiquidez = valorDaLiquidez;
	}

	public Integer getChavePrimariaEstrantrangeira() {
		return chavePrimariaEstrantrangeira;
	}

	public void setChavePrimariaEstrantrangeira(Integer chavePrimariaEstrantrangeira) {
		this.chavePrimariaEstrantrangeira = chavePrimariaEstrantrangeira;
	}
	
	 @Override
	    public String toString() {
	        return "Investimento Fechado{" +
	                "Data do Fechamento = " + dataDeSaida +
	                ", Valor líquido da aplicação = " + valorDaLiquidez +
	                '}';
	    }
	
}
