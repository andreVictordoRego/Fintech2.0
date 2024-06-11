package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.LancamentoDAO;
import br.com.fiap.fintech.dao.oracle.OracleLancamentoDAO;

public class DAOFactory {

	public static LancamentoDAO getLancamentoDAO() {
		return new OracleLancamentoDAO();
	}
}
