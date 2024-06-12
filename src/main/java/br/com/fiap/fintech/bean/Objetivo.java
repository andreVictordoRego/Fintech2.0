package br.com.fiap.fintech.bean;

import java.time.LocalDate;

public class Objetivo {

	private Integer codigoDoObjetivo;
	private Long numeroDeCPF;
	private String nomeDoObjetivo;
	private Double valorDoObjetivo;
	private Double valorAtual;
	private LocalDate dataDeCriacao;
	private LocalDate dataDeConclusao;
	private String descricaoDoObjetivo;
	private Integer chaveEstrangeira;
	
	
	public Objetivo() {

	}

	public Objetivo(Integer codigoDoObjetivo, Long numeroDeCPF, String nomeDoObjetivo, Double valorDoObjetivo, Double valorAtual,
			LocalDate dataDeCriacao, LocalDate dataDeConclusao, String descricaoDoObjetivo) {
		super();
		this.codigoDoObjetivo = codigoDoObjetivo;
		this.numeroDeCPF = numeroDeCPF;
		this.nomeDoObjetivo = nomeDoObjetivo;
		this.valorDoObjetivo = valorDoObjetivo;
		this.valorAtual = valorAtual;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeConclusao = dataDeConclusao;
		this.descricaoDoObjetivo = descricaoDoObjetivo;
	}

	
	public Integer getCodigoDoObjetivo() {
		return codigoDoObjetivo;
	}

	public void setCodigoDoObjetivo(Integer codigoDoObjetivo) {
		this.codigoDoObjetivo = codigoDoObjetivo;
	}

	public Long getNumeroDeCPF() {
		return numeroDeCPF;
	}

	public void setNumeroDeCPF(Long numeroDeCPF) {
		this.numeroDeCPF = numeroDeCPF;
	}

	public String getNomeDoObjetivo() {
		return nomeDoObjetivo;
	}

	public void setNomeDoObjetivo(String nomeDoObjetivo) {
		this.nomeDoObjetivo = nomeDoObjetivo;
	}

	public Double getValorDoObjetivo() {
		return valorDoObjetivo;
	}

	public void setValorDoObjetivo(Double valorDoObjetivo) {
		this.valorDoObjetivo = valorDoObjetivo;
	}

	public Double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(LocalDate dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public LocalDate getDataDeConclusao() {
		return dataDeConclusao;
	}

	public void setDataDeConclusao(LocalDate dataDeConclusao) {
		this.dataDeConclusao = dataDeConclusao;
	}

	public String getDescricaoDoObjetivo() {
		return descricaoDoObjetivo;
	}

	public void setDescricaoDoObjetivo(String descricaoDoObjetivo) {
		this.descricaoDoObjetivo = descricaoDoObjetivo;
	}

	public Integer getChaveEstrangeira() {
		return chaveEstrangeira;
	}

	public void setChaveEstrangeira(Integer chaveEstrangeira) {
		this.chaveEstrangeira = chaveEstrangeira;
	}
	
	
	
}
