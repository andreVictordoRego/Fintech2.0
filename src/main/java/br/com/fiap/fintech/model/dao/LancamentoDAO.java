package br.com.fiap.fintech.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.jdbc.ConnectionManager;
import br.com.fiap.fintech.model.bean.Lancamento;

public class LancamentoDAO {
	
	private Connection conexao = ConnectionManager.getConnection();
	private PreparedStatement pstmt;
	private ResultSet resultSet;
	
	public void cadastrarNovoLancamento(Lancamento lancamento) {
		String sqlQuery = "INSERT INTO T_FNT_LANTO("
				+ "cd_lancamento, nr_cpf, dt_lancamento, hr_lancamento, vl_lancamento, ds_lancamento) "
				+ "VALUES(SQ_LANTO.NEXTVAL, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setLong(1, lancamento.getNumeroDoCPF());
			pstmt.setDate(2, java.sql.Date.valueOf(lancamento.getDtDoLancamento()));
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(lancamento.getHrDoLancamento()));
			pstmt.setDouble(4, lancamento.getVlDoLancamento());
			pstmt.setString(5, lancamento.getDsDoLancamento());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.err.println("Erro ao cadastrar lancamento.");
			e.printStackTrace();
		} 
	}
	
	public void atualizarLancamento(Lancamento lancamento) throws SQLException {
		conexao.setAutoCommit(false);
		String sqlQuery = "UPDATE T_FNT_LANTO "
				+ "SET dt_lancamento = ?, hr_lancamento = ?, vl_lancamento = ?, ds_lancamento = ? "
				+ "WHERE cd_lancamento = ? AND nr_cpf = ?";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setDate(1, java.sql.Date.valueOf(lancamento.getDtDoLancamento()));
			pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(lancamento.getHrDoLancamento()));
			pstmt.setDouble(3, lancamento.getVlDoLancamento());
			pstmt.setString(4, lancamento.getDsDoLancamento());
			pstmt.setInt(5,lancamento.getCodigoDoLancamento());
			pstmt.setLong(6, lancamento.getNumeroDoCPF());
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			conexao.rollback();
		}
	}
	
	public List<Lancamento> listarLancamentos(Integer numeroDoCpf) {
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		Lancamento lancamento = new Lancamento();
		String sqlQuery = "SELECT * FROM T_FNT_LANTO "
				+ "WHERE nr_cpf = ?";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setInt(1, numeroDoCpf);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				lancamento.setCodigoDoLancamento(resultSet.getInt("cd_lancamento"));
				lancamento.setDtDoLancamento(resultSet.getDate("dt_lancamento").toLocalDate());
				lancamento.setHrDoLancamento(resultSet.getTimestamp("hr_lancamento").toLocalDateTime());
				lancamento.setVlDoLancamento(resultSet.getDouble("vl_lancamento"));
				lancamento.setDsDoLancamento(resultSet.getString("ds_lancamento"));
				lancamentos.add(lancamento);
			}
			
		} catch(SQLException e) {
			System.err.println("");
		}
		
		return lancamentos;
	}
	
	public Boolean excluirLancamento(Integer codigoDoLancamento) throws SQLException {
		conexao.setAutoCommit(false);
		String sqlQuery = "DELETE FROM T_FNT_LANTO "
				+ "WHERE cd_lancamento = ?";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setInt(1, codigoDoLancamento);
			pstmt.executeUpdate();
			conexao.commit();
			return true;
		} catch(SQLException e) {
			conexao.rollback();
			return false;
		}
	}
}
