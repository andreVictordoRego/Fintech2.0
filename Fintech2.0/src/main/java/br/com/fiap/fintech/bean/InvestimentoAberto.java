package br.com.fiap.fintech.bean;

import java.time.LocalDate;

public class InvestimentoAberto {
	
	private LocalDate mes;
	private Double valorTotalDoRendimento;
	
	public InvestimentoAberto() {
		super();
	}

	public InvestimentoAberto(LocalDate mes, Double valorTotalDoRendimento) {
		super();
		this.mes = mes;
		this.valorTotalDoRendimento = valorTotalDoRendimento;
	}

	public LocalDate getMes() {
		return mes;
	}

	public void setMes(LocalDate mes) {
		this.mes = mes;
	}

	public Double getValorTotalDoRendimento() {
		return valorTotalDoRendimento;
	}

	public void setValorTotalDoRendimento(Double valorTotalDoRendimento) {
		this.valorTotalDoRendimento = valorTotalDoRendimento;
	}
}
