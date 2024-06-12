package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.bean.Extrato;

public interface ExtratoDAO {

	Extrato consultarExtrato(Integer numeroDeCPF);
	void atualizarExtrato(Extrato extratoAtualizado);
}
