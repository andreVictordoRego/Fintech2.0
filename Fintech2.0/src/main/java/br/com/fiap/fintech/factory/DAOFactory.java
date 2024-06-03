package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.ExtratoDAO;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.implement.OracleExtratoDAO;
import br.com.fiap.fintech.dao.implement.OracleObjetivoDAO;
import br.com.fiap.fintech.dao.implement.OracleUsuarioDAO;

public class DAOFactory {
	

	public static ExtratoDAO getExtratoDAO() {
		return new OracleExtratoDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static ObjetivoDAO getObjetivoDAO() {
		return new OracleObjetivoDAO();
	}
}
