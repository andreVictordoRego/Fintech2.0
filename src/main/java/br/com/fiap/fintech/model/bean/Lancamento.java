package br.com.fiap.fintech.model.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Lancamento {

	private Integer codigoDoLancamento;
	private Long numeroDoCPF;
	private LocalDate dtDoLancamento;
	private LocalDateTime hrDoLancamento;
	private Double vlDoLancamento;
	private String dsDoLancamento;
	
	public Lancamento() {
		
	}
	
	public Lancamento(Integer codigoDoLancamento, Long numeroDoCPF, LocalDate dtDoLancamento,
			LocalDateTime hrDoLancamento, Double vlDoLancamento, String dsDoLancamento) {
		this.codigoDoLancamento = codigoDoLancamento;
		this.numeroDoCPF = numeroDoCPF;
		this.dtDoLancamento = dtDoLancamento;
		this.hrDoLancamento = hrDoLancamento;
		this.vlDoLancamento = vlDoLancamento;
		this.dsDoLancamento = dsDoLancamento;
	}

	public Integer getCodigoDoLancamento() {
		return codigoDoLancamento;
	}

	public void setCodigoDoLancamento(Integer codigoDoLancamento) {
		this.codigoDoLancamento = codigoDoLancamento;
	}

	public Long getNumeroDoCPF() {
		return numeroDoCPF;
	}

	public void setNumeroDoCPF(Long numeroDoCPF) {
		this.numeroDoCPF = numeroDoCPF;
	}

	public LocalDate getDtDoLancamento() {
		return dtDoLancamento;
	}

	public void setDtDoLancamento(LocalDate dtDoLancamento) {
		this.dtDoLancamento = dtDoLancamento;
	}

	public LocalDateTime getHrDoLancamento() {
		return hrDoLancamento;
	}

	public void setHrDoLancamento(LocalDateTime hrDoLancamento) {
		this.hrDoLancamento = hrDoLancamento;
	}

	public Double getVlDoLancamento() {
		return vlDoLancamento;
	}

	public void setVlDoLancamento(Double vlDoLancamento) {
		this.vlDoLancamento = vlDoLancamento;
	}

	public String getDsDoLancamento() {
		return dsDoLancamento;
	}

	public void setDsDoLancamento(String dsDoLancamento) {
		this.dsDoLancamento = dsDoLancamento;
	}

	@Override
	public String toString() {
		return "Lancamento [codigoDoLancamento=" + codigoDoLancamento + ", numeroDoCPF=" + numeroDoCPF
				+ ", dtDoLancamento=" + dtDoLancamento + ", hrDoLancamento=" + hrDoLancamento + ", vlDoLancamento="
				+ vlDoLancamento + ", dsDoLancamento=" + dsDoLancamento + "]";
	}
	
}
