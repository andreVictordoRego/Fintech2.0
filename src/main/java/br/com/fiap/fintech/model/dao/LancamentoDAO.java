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
	
	public void cadastrarNovoLancamento(Lancamento lancamento) throws SQLException {
		conexao.setAutoCommit(false);
		String sqlQuery = "INSERT INTO T_FNT_LANTO("
				+ "SQ_LANC.NEXTVAL, nr_cpf, dt_lancamento, hr_lancamento, vl_lancamento, tx_descricao) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setInt(1, lancamento.getNumeroDoCPF());
			pstmt.setDate(2, java.sql.Date.valueOf(lancamento.getDtDoLancamento()));
			pstmt.setDate(3, java.sql.Date.valueOf(lancamento.getHrDoLancamento()));
			pstmt.setDouble(4, lancamento.getVlDoLancamento());
			pstmt.setString(5, lancamento.getDsDoLancamento());
			pstmt.executeUpdate();
			conexao.commit();
		} catch(SQLException e) {
			conexao.rollback();
		} 
	}
	
	public void atualizarLancamento(Lancamento lancamento) throws SQLException {
		conexao.setAutoCommit(false);
		String sqlQuery = "UPDATE T_FNT_LANTO "
				+ "SET dt_lancamento = ?, hr_lancamento = ?, vl_lancamento = ?, tx_descricao = ? "
				+ "WHERE cd_lancamento = ? AND nr_cpf = ?";
		
		try {
			pstmt = conexao.prepareStatement(sqlQuery);
			pstmt.setDate(1, java.sql.Date.valueOf(lancamento.getDtDoLancamento()));
			pstmt.setDate(2, java.sql.Date.valueOf(lancamento.getHrDoLancamento()));
			pstmt.setDouble(3, lancamento.getVlDoLancamento());
			pstmt.setString(4, lancamento.getDsDoLancamento());
			pstmt.setInt(5,lancamento.getCodigoDoLancamento());
			pstmt.setInt(6, lancamento.getNumeroDoCPF());
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
				lancamento.setHrDoLancamento(resultSet.getDate("hr_lancamento").toLocalDate());
				lancamento.setVlDoLancamento(resultSet.getDouble("vl_lancamento"));
				lancamento.setDsDoLancamento(resultSet.getString("tx_descricao"));
				lancamentos.add(lancamento);
			}
			
		} catch(SQLException e) {
			
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
