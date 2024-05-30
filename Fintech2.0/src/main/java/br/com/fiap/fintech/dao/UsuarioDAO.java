package br.com.fiap.fintech.dao;

import java.sql.SQLException;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.exception.DBException;

public interface UsuarioDAO {

	void cadastrarNovoUsuario(Usuario usuario) throws DBException, SQLException;
	void editarCadastroDoUsuario(Usuario usuario) throws DBException;
	void alterarSenhaDoUsuario(String novaSenha);
	boolean isSenhaValida(String senhaParaValidacao);
}
