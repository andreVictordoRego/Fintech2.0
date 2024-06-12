package br.com.fiap.fintech.dao.implement;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleObjetivoDAO implements ObjetivoDAO{

	private Connection conexao = ConnectionManager.getInstance().getConnection();
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	@Override
	public void criarNovoObjetivo(Objetivo objetivo) throws DBException {
	
		Connection conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			
			String sql = "INSERT INTO T_FNT_OBJTVO (CD_OBJETIVO, NR_CPF, NM_OBJETIVO, VL_OBJETIVO, VL_ATUAL, DT_CRIACAO, DT_CONCLUSAO, DS_OBJETIVO) VALUES (SQ_TB_OBJTVO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, objetivo.getNumeroDeCPF());
			stmt.setString(2, objetivo.getNomeDoObjetivo());
			stmt.setDouble(3, objetivo.getValorDoObjetivo());
			stmt.setDouble(4, objetivo.getValorAtual());
			stmt.setDate(5, Date.valueOf(objetivo.getDataDeCriacao()));
			stmt.setDate(6, Date.valueOf(objetivo.getDataDeConclusao()));
			stmt.setString(7, objetivo.getDescricaoDoObjetivo());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao criar novo Objetivo.");
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
	public List<Objetivo> listarObjetivos(Long numeroDeCPF) {
		conexao = ConnectionManager.getInstance().getConnection();
		List<Objetivo> lista = new ArrayList<Objetivo>();
		PreparedStatement stmt = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_FNT_OBJTVO WHERE NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, numeroDeCPF);
						
			rs = stmt.executeQuery();
			while(rs.next()) {
				Objetivo objetivoLista = new Objetivo();
				
				objetivoLista.setCodigoDoObjetivo(rs.getInt("CD_OBJETIVO")); 
				objetivoLista.setNomeDoObjetivo(rs.getString("NM_OBJETIVO"));
				objetivoLista.setValorDoObjetivo(rs.getDouble("VL_OBJETIVO"));
				objetivoLista.setValorAtual(rs.getDouble("VL_ATUAL"));
				objetivoLista.setDataDeCriacao(rs.getDate("DT_CRIACAO").toLocalDate());
				objetivoLista.setDataDeConclusao(rs.getDate("DT_CONCLUSAO").toLocalDate());
				objetivoLista.setDescricaoDoObjetivo(rs.getString("DS_OBJETIVO"));

				lista.add(objetivoLista);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
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
		return lista;
	}

	@Override
	public void editarObjetivo(Objetivo objetivo) throws DBException {
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			
			String sql = "UPDATE T_FNT_OBJTVO SET NM_OBJETIVO = ?, VL_OBJETIVO = ?, VL_ATUAL = ?, DT_CRIACAO = ?, DT_CONCLUSAO = ?, DS_OBJETIVO = ? WHERE CD_OBJETIVO = ? AND NR_CPF = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivo.getNomeDoObjetivo());
			stmt.setDouble(2, objetivo.getValorDoObjetivo());
			stmt.setDouble(3, objetivo.getValorAtual());
			stmt.setDate(4, Date.valueOf(objetivo.getDataDeCriacao()));
			stmt.setDate(5, Date.valueOf(objetivo.getDataDeConclusao()));
			stmt.setString(6, objetivo.getDescricaoDoObjetivo());
			stmt.setInt(7, objetivo.getCodigoDoObjetivo());
			stmt.setLong(8, objetivo.getNumeroDeCPF());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao editar o Objetivo.");
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
	public void excluirObjetivo(Integer codigoDoObjetivo) throws DBException {
		
		Connection conexao = ConnectionManager.getInstance().getConnection();
		
		try {
			
			String sql = "DELETE FROM T_FNT_OBJTVO WHERE CD_OBJETIVO = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigoDoObjetivo);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover Objetivo.");
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
}
