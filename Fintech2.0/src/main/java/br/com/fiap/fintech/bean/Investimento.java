package br.com.fiap.fintech.bean;

import java.time.LocalDate;

public class Investimento {

	private Integer codigoDoInvestimento;
	private String nomeDoInvestimento;
	private TipoDeInvestimento tipoDeInvestimento;
	private Banco banco;
	private Double valorDaRentabilidade;
	private LocalDate dataDeEntrada;
	private LocalDate dataDeVencimento;
	private Double valorDeInvestimento;
	private Integer chaveEstrangeira;
	private InvestimentoAberto investimentoAberto;
	private InvestimentoFechado investimentoFechado;

	public Investimento() {
		super();
	}

	public Investimento(Integer codigoDoInvestimento, String nomeDoInvestimento, TipoDeInvestimento tipoDeInvestimento,
			Banco banco, Double valorDaRentabilidade, LocalDate dataDeEntrada, LocalDate dataDeVencimento,
			Double valorDeInvestimento) {
		super();
		this.codigoDoInvestimento = codigoDoInvestimento;
		this.nomeDoInvestimento = nomeDoInvestimento;
		this.tipoDeInvestimento = tipoDeInvestimento;
		this.banco = banco;
		this.valorDaRentabilidade = valorDaRentabilidade;
		this.dataDeEntrada = dataDeEntrada;
		this.dataDeVencimento = dataDeVencimento;
		this.valorDeInvestimento = valorDeInvestimento;
	}

	public Integer getCodigoDoInvestimento() {
		return codigoDoInvestimento;
	}

	public void setCodigoDoInvestimento(Integer codigoDoInvestimento) {
		this.codigoDoInvestimento = codigoDoInvestimento;
	}

	public String getNomeDoInvestimento() {
		return nomeDoInvestimento;
	}

	public void setNomeDoInvestimento(String nomeDoInvestimento) {
		this.nomeDoInvestimento = nomeDoInvestimento;
	}

	public TipoDeInvestimento getTipoDeInvestimento() {
		return tipoDeInvestimento;
	}

	public void setTipoDeInvestimento(TipoDeInvestimento tipoDeInvestimento) {
		this.tipoDeInvestimento = tipoDeInvestimento;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Double getValorDaRentabilidade() {
		return valorDaRentabilidade;
	}

	public void setValorDaRentabilidade(Double valorDaRentabilidade) {
		this.valorDaRentabilidade = valorDaRentabilidade;
	}

	public LocalDate getDataDeEntrada() {
		return dataDeEntrada;
	}

	public void setDataDeEntrada(LocalDate dataDeEntrada) {
		this.dataDeEntrada = dataDeEntrada;
	}

	public LocalDate getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(LocalDate dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public Double getValorDeInvestimento() {
		return valorDeInvestimento;
	}

	public void setValorDeInvestimento(Double valorDeInvestimento) {
		this.valorDeInvestimento = valorDeInvestimento;
	}

	public Integer getChaveEstrangeira() {
		return chaveEstrangeira;
	}

	public void setChaveEstrangeira(Integer chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}

	public InvestimentoAberto getInvestimentoAberto() {
		return investimentoAberto;
	}

	public void setInvestimentoAberto(InvestimentoAberto investimentoAberto) {
		this.investimentoAberto = investimentoAberto;
	}

	public InvestimentoFechado getInvestimentoFechado() {
		return investimentoFechado;
	}

	public void setInvestimentoFechado(InvestimentoFechado investimentoFechado) {
		this.investimentoFechado = investimentoFechado;
	}	
	
	
}
