package br.com.fiap.fintech.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Lancamento;
import br.com.fiap.fintech.dao.LancamentoDAO;
import br.com.fiap.fintech.exception.DatabaseException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleLancamentoDAO implements LancamentoDAO {
	
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public void cadastrarLancamento(Lancamento lancamento) throws DatabaseException {
		String sqlQuery = "INSERT INTO T_FNT_LANTO("
				+ "cd_lancamento, nr_cpf, dt_lancamento, hr_lancamento, "
				+ "vl_lancamento, tx_lancamento, tp_lancamento, ds_categoria) "
				+ "VALUES(SQ_LANTO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setLong(1, lancamento.getNumeroDoCPF());
			pstmt.setDate(2, java.sql.Date.valueOf(lancamento.getDataDoLancamento()));
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(lancamento.getHoraDoLancamento()));
			pstmt.setDouble(4, lancamento.getValorDoLancamento());
			pstmt.setString(5, lancamento.getDescricaoDoLancamento());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao cadastrar lançamento.");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleLancamentoDAO.cadastrarLancamento()");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void atualizarLancamento(Lancamento lancamento) throws DatabaseException {
		String sqlQuery = "UPDATE T_FNT_LANTO "
				+ "SET dt_lancamento = ?, hr_lancamento = ?, vl_lancamento = ?, tx_lancamento = ? "
				+ "WHERE cd_lancamento = ? AND nr_cpf = ?";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setDate(1, java.sql.Date.valueOf(lancamento.getDataDoLancamento()));
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(lancamento.getHoraDoLancamento()));
			pstmt.setDouble(3, lancamento.getValorDoLancamento());
			pstmt.setString(4, lancamento.getDescricaoDoLancamento());
			pstmt.setInt(5,lancamento.getCodigoDoLancamento());
			pstmt.setLong(6, lancamento.getNumeroDoCPF());
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao atualizar lançamento.");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleLancamentoDAO.atualizarLancamento()");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void removerLancamento(Integer codigoDoLancamento) throws DatabaseException {
		String sqlQuery = "DELETE FROM T_FNT_LANTO "
				+ "WHERE cd_lancamento = ?";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, codigoDoLancamento);
			pstmt.executeUpdate();
			connection.commit();
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao remover lançamento.");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleLancamentoDAO.cadastrarLancamento()");
				e.printStackTrace();
			}
		}
	}



	@Override
	public List<Lancamento> listarLancamentos() {
		String sqlQuery = "SELECT * FROM T_FNT_LANTO";
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();	
		Lancamento lancamento = new Lancamento();
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				lancamento.setCodigoDoLancamento(rs.getInt("cd_lancamento"));
				lancamento.setNumeroDoCPF(rs.getLong("nr_cpf"));
				lancamento.setDataDoLancamento(rs.getDate("dt_lancamento").toLocalDate());
				lancamento.setHoraDoLancamento(rs.getTimestamp("hr_lancamento").toLocalDateTime());
				lancamento.setValorDoLancamento(rs.getDouble("vl_lancamento"));
				lancamento.setDescricaoDoLancamento(rs.getString("tx_lancamento"));
				lancamentos.add(lancamento);
			}
			
			return lancamentos;
		} catch(SQLException e) {
			System.err.println("Erro ao listar lancamentos em OraclelancamentoDAO.listarlancamento()");
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OraclelancamentoDAO.listarlancamento()");
				e.printStackTrace();
				return null;
			}
		}
	}

}
