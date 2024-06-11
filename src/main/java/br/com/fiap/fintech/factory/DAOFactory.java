package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.dao.implement.OracleInvestimentoDAO;

public class DAOFactory {
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}

}
