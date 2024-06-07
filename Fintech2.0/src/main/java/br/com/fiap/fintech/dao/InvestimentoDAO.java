package br.com.fiap.fintech.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.InvestimentoAberto;
import br.com.fiap.fintech.exception.DBException;

public interface InvestimentoDAO {
	
	void cadastrarNovoInvestimento(Investimento investimento) throws DBException, SQLException;
	List<Investimento> listarInvestimentos();
	void editarInvestimento(Investimento investimento) throws DBException;
	void excluirInvestimento(String nomeDoInvestimento) throws DBException;
	List<InvestimentoAberto> listarInvestimentosAbertos(Integer codigoDoinvestimento);
	void registrarFechamentoDeInvestimento(InvestimentoAberto investimentoAberto);
	Boolean fecharInvestimento(Integer codigoDoInvestimento);
	void lancarNovoInvestimentoAberto(Investimento investimento) throws SQLException, DBException;
}
