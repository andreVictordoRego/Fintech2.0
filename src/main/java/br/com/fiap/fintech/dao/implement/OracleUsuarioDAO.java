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
import java.io.FileOutputStream;
import java.io.OutputStream;

public class OracleUsuarioDAO implements UsuarioDAO, LoginDAO{


	private Connection conexao;
	private PreparedStatement stmt;
	
	@Override
	public void cadastrarNovoUsuario(Usuario usuario) throws DBException, SQLException {

		conexao = ConnectionManager.getInstance().getConnection();
		
	    String sqlUsuario = "INSERT INTO T_FNT_USUARIO (NR_CPF, NM_COMPLETO, DT_NASCIMENTO, DS_GENERO, TX_EMAIL, IM_FOTO) VALUES (?, ?, ?, ?, ?, ?)";
	    String sqlLogin = "INSERT INTO T_FNT_LOGIN (NR_CPF, TX_SENHA) VALUES (?, ?)";
	    
	    PreparedStatement stmtUsuario = null;
	    PreparedStatement stmtLogin = null;
	    boolean autoCommitOriginal = conexao.getAutoCommit();
	    
	    try {
	        conexao.setAutoCommit(false);

	        stmtUsuario = conexao.prepareStatement(sqlUsuario);
	        stmtUsuario.setLong(1, usuario.getNumeroDeCPF());
	        stmtUsuario.setString(2, usuario.getNomeCompleto());
	        stmtUsuario.setDate(3, Date.valueOf(usuario.getDataDeNascimento()));
	        stmtUsuario.setString(4, usuario.getGenero());
	        stmtUsuario.setString(5, usuario.getEmail());

	        File arquivoImagem = usuario.getImagemFoto();
	        if (arquivoImagem != null) {
	            try (InputStream inputStream = new FileInputStream(arquivoImagem)) {
	                stmtUsuario.setBinaryStream(6, inputStream);
	            	//stmtUsuario.setBinaryStream(6, inputStream, (int) arquivoImagem.length());
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
	        
	        stmtLogin = conexao.prepareStatement(sqlLogin);
	        stmtLogin.setLong(1, usuario.getNumeroDeCPF());
	        
	        if (isSenhaValida(usuario.getSenha())) {
	        	stmtLogin.setString(2, usuario.getSenha());
	        	
	        	stmtUsuario.executeUpdate();
	        	stmtLogin.executeUpdate();
	        } else {
	        	throw new DBException("Senha Invalida.");
	        }
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

	conexao = ConnectionManager.getInstance().getConnection();
		
	try {
			String sql = "UPDATE T_FNT_USUARIO SET NM_COMPLETO = ?, DT_NASCIMENTO = ?, DS_GENERO = ?, TX_EMAIL = ?, IM_FOTO = ? WHERE NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNomeCompleto());
			stmt.setDate(2, Date.valueOf(usuario.getDataDeNascimento()));
			stmt.setString(3, usuario.getGenero());
			stmt.setString(4, usuario.getEmail());
			
			File arquivoImagem = usuario.getImagemFoto();
	        if (arquivoImagem != null) {
	            try {
	                InputStream inputStream = new FileInputStream(arquivoImagem);
	                stmt.setBinaryStream(5, inputStream);
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	                throw new DBException("Erro ao carregar o arquivo de imagem.", e);
	            }
	        } else {
	            stmt.setNull(5, java.sql.Types.BLOB);
	        }
	        
	        stmt.setLong(6, usuario.getNumeroDeCPF());
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
	public void alterarSenhaDoUsuario(String novaSenha, Usuario usuario) throws DBException {
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		if (isSenhaValida(novaSenha)) {
			
			try {
				String sql = "UPDATE T_FNT_LOGIN SET TX_SENHA = ? WHERE NR_CPF = ?";

				stmt = conexao.prepareStatement(sql);
				usuario.setSenha(novaSenha);
				stmt.setString(1, usuario.getSenha());
				stmt.setLong(2, usuario.getNumeroDeCPF());
				
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
	
public void salvarImagemDoBanco(String caminhoDestino, Usuario usuario) throws DBException {
	        Connection conexao = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            conexao = ConnectionManager.getInstance().getConnection();

	            String sql = "SELECT IM_FOTO FROM T_FNT_USUARIO WHERE NR_CPF = ?";
	            stmt = conexao.prepareStatement(sql);

	            stmt.setLong(1, usuario.getNumeroDeCPF());

	            rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                byte[] imagemBytes = rs.getBytes("IM_FOTO");
	                if (imagemBytes != null) {
	                    File fileDestino = new File(caminhoDestino);
	                    try (OutputStream outputStream = new FileOutputStream(fileDestino)) {
	                        outputStream.write(imagemBytes);
	                        System.out.println("Imagem salva com sucesso em: " + caminhoDestino);
	                    }
	                } else {
	                    System.out.println("Nenhum imagem cadastrado no CPF: " + usuario.getNumeroDeCPF());
	                }
	            } else {
	                System.out.println("Usuário com CPF " + usuario.getNumeroDeCPF() + " não encontrado.");
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	            throw new DBException("Erro ao salvar imagem do banco.", e);
	        } finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
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
