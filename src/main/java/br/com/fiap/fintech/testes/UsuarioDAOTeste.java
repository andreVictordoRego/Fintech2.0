package br.com.fiap.fintech.testes;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.implement.OracleUsuarioDAO;
import br.com.fiap.fintech.exception.DBException;

public class UsuarioDAOTeste {

	public static void main(String[] args) throws DBException, SQLException {
		
		String caminhoImg = "C:\\Users\\andre\\OneDrive\\Área de Trabalho\\NovaImagemBanco2.jpg";
		File imagem = new File (caminhoImg);
		
		Usuario usuario = new Usuario(33344466688L, "Melissa", LocalDate.of(1991, 03, 28), "Masculino", "email@email.com", null, "12X3456");
		Usuario usuarioTesteEditado = new Usuario(33344466688L, "Melissa Soares", LocalDate.of(1991, 9, 10), "Feminino", "mel@email.com", imagem, "12X3456");
		
		UsuarioDAO dao = new OracleUsuarioDAO();
		
		System.out.println("Criando DAO de cadastro");
		dao.cadastrarNovoUsuario(usuario);
		System.out.println("Usuario Cadastrado");
		
		System.out.println("Criando DAO de edicao");
		dao.editarCadastroDoUsuario(usuarioTesteEditado);
		System.out.println("Edicao Concluida");
		
		System.out.println("Criando Alteracao Senha");
		dao.alterarSenhaDoUsuario("11111a", usuarioTesteEditado);
		System.out.println("Alteracao Concluida");
	}

}
