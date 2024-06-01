package br.com.fiap.fintech.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.fiap.fintech.model.bean.Lancamento;
import br.com.fiap.fintech.model.dao.LancamentoDAO;

public class MainTeste {

	public static void main(String[] args) {
		
		Lancamento lancamento = new Lancamento();
		LancamentoDAO lantoDao = new LancamentoDAO();
		
		lancamento.setNumeroDoCPF(41304631877L);
		lancamento.setDtDoLancamento(LocalDate.now());
		lancamento.setHrDoLancamento(LocalDateTime.now());
		lancamento.setVlDoLancamento(25000.10);
		lancamento.setDsDoLancamento("Lançamento teste");
		

		lantoDao.cadastrarNovoLancamento(lancamento);
	
	}
}
