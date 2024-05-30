package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.exception.DBException;

public interface InvestimentoDAO {
	
	void cadastrarNovoInvestimento(Investimento investimento) throws DBException;
	List<Investimento> listarInvestimentos();
	void editarInvestimento(Investimento investimento) throws DBException;
	void excluirInvestimento(String nomeDoInvestimento);
	Boolean fecharInvestimento(Integer codigoDoInvestimento);
}
