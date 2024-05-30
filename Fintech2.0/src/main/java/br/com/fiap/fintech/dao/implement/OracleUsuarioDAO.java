package br.com.fiap.fintech.dao.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.LoginDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;
import java.io.InputStream;

public class OracleUsuarioDAO implements UsuarioDAO, LoginDAO{

	private Connection conexao = ConnectionManager.getInstance().getConnection();
	private PreparedStatement stmt;
	private ResultSet rs;
	
	@Override
	public void cadastrarNovoUsuario(Usuario usuario) throws DBException, SQLException {

	    String sqlUsuario = "INSERT INTO T_FNT_USUARIO (NR_CPF, NM_COMPLETO, DT_NASCIMENTO, DS_GENERO, TX_EMAIL, IM_FOTO) VALUES (?, ?, ?, ?, ?, ?)";
	    String sqlLogin = "INSERT INTO T_FNT_LOGIN (NR_CPF, TX_SENHA) VALUES (?, ?)";
	    
	    PreparedStatement stmtUsuario = null;
	    PreparedStatement stmtLogin = null;
	    boolean autoCommitOriginal = conexao.getAutoCommit();
	    
	    try {
	        conexao.setAutoCommit(false);

	        stmtUsuario = conexao.prepareStatement(sqlUsuario);
	        stmtUsuario.setInt(1, usuario.getNumeroDeCPF());
	        stmtUsuario.setString(2, usuario.getNomeCompleto());
	        stmtUsuario.setDate(3, Date.valueOf(usuario.getDataDeNascimento()));
	        stmtUsuario.setString(4, usuario.getGenero());
	        stmtUsuario.setString(5, usuario.getEmail());

	        File arquivoImagem = usuario.getImagemFoto();
	        if (arquivoImagem != null) {
	            try (InputStream inputStream = new FileInputStream(arquivoImagem)) {
	                stmtUsuario.setBinaryStream(6, inputStream, (int) arquivoImagem.length());
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	                throw new DBException("Erro ao carregar o arquivo de imagem.", e);
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new DBException("Erro ao ler o arquivo de imagem.", e);
	            }
	        } else {
	            stmtUsuario.setNull(6, java.sql.Types.BLOB);
	        }

	        stmtUsuario.executeUpdate();
	        
	        stmtLogin = conexao.prepareStatement(sqlLogin);
	        stmtLogin.setInt(1, usuario.getNumeroDeCPF());
	        
	        if (isSenhaValida(usuario.getSenha())) {
	        	stmtLogin.setString(2, usuario.getSenha());
	        } else {
	        	throw new DBException("Senha Invalida.");
	        }
	       
	        stmtLogin.executeUpdate();
	        
	        conexao.commit();

	    } catch (SQLException e) {
	        conexao.rollback();
	        e.printStackTrace();
	        throw new DBException("Erro ao criar novo Usuario.", e);
	    } finally {
	        if (stmtUsuario != null) {
	            try {
	                stmtUsuario.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmtLogin != null) {
	            try {
	                stmtLogin.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        try {
	            conexao.setAutoCommit(autoCommitOriginal);
	            conexao.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


	

	@Override
	public void editarCadastroDoUsuario(Usuario usuario) throws DBException {

	try {
			
			String sql = "UPDATE T_FNT_USUARIO SET NM_COMPLETO = ?, DT_NASCIMENTO = ?, DS_GENERO = ?, TX_EMAIL = ?, IM_FOTO = ? WHERE NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNomeCompleto());
			stmt.setDate(2, Date.valueOf(usuario.getDataDeNascimento()));
			stmt.setString(3, usuario.getGenero());
			stmt.setString(4, usuario.getEmail());
			
			File arquivoImagem = usuario.getImagemFoto();
	        if (arquivoImagem != null) {
	            try (InputStream inputStream = new FileInputStream(arquivoImagem)) {
	                stmt.setBinaryStream(5, inputStream, (int) arquivoImagem.length());
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	                throw new DBException("Erro ao carregar o arquivo de imagem.", e);
	            } catch (IOException e) {
	                e.printStackTrace();
	                throw new DBException("Erro ao ler o arquivo de imagem.", e);
	            }
	        } else {
	            stmt.setNull(5, java.sql.Types.BLOB);
	        }	
	        
	        stmt.setInt(6, usuario.getNumeroDeCPF());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao editar o Cadastro do Usuario.");
		} finally {
			if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
			if (conexao != null) {
				try {
					conexao.close();
				} catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		}
	}
		
	
	@Override
	public boolean isSenhaValida(String senhaParaValidacao) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
	    return senhaParaValidacao != null && senhaParaValidacao.matches(regex);
		
	}
	
	@Override
	public void alterarSenhaDoUsuario(String novaSenha) {

		if (isSenhaValida(novaSenha)) {
			
			try {
				
				String sql = "UPDATE T_FNT_LOGIN SET TX_SENHA = ? WHERE NR_CPF = ?";
				stmt = conexao.prepareStatement(sql);
				
				stmt.setString(1, usuario.setSenha(novaSenha));
				stmt.setInt(2, usuario.getNumeroDeCPF());
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				
			} finally {
					if (stmt != null) {
			            try {
			                stmt.close();
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
					if (conexao != null) {
						try {
							conexao.close();
						} catch (SQLException e) {
				            e.printStackTrace();
				        }
					}
				}
			} else {
				throw new DBException("Senha Invalida.");
			}
		}

	

//***************************************************************************************

	@Override
	public void validarLogin(String email, String senha) {
		// fazer uma verificacao no banco de email e senha 
		// e depois verificar se: email = email/ senha=senha???
		
	}




	@Override
	public void logarComGmail() {
		// estudar
		
	}

}
