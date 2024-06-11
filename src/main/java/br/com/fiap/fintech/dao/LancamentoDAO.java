package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Lancamento;
import br.com.fiap.fintech.exception.DatabaseException;


public interface LancamentoDAO {

	void cadastrarLancamento(Lancamento produto) throws DatabaseException;
	void atualizarLancamento(Lancamento produto) throws DatabaseException;
	void removerLancamento(Integer codigoDoProduto) throws DatabaseException;
	List<Lancamento> listarLancamentos();
}
