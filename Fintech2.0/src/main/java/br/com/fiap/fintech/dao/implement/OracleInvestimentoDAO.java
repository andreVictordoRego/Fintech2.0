package br.com.fiap.fintech.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO{
	
	private Connection conexao;

	@Override	
	public void cadastrarNovoInvestimento(Investimento investimento) throws DBException{
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_FNT_INVEST (CD_INVESTIMENTO, NR_CPF, VL_RENTABILIDADE, DT_ENTRADA, DT_VENCIMENTO, VL_INVESTIMENTO, NM_APLICACAO) VALUES (SQ_TB_INVEST.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInteger(1, usuario.getNumeroDeCPF());
			stmt.setDouble(2, investimento.getValorDaRentabilidade());
			stmt.setDate(3, Date.valueOf(investimento.getDataDeEntrada()));
			stmt.setDate(4, Date.valueOf(investimento.getDataDeVencimento()));
			stmt.setDouble(5, investimento.getValorDeInvestimento());
			stmt.setString(6, investimento.getNomeDoInvestimento());
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Investimento> listarInvestimentos(){
		List<Investimento> lista = new ArrayList<Investimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_INVEST INNER JOIN T_FNT_USUARIO ON T_FNT_INVEST.NR_CPF = T_FNT_USUARIO.NR_CPF");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_INVESTIMENTO");
				Double vlRentabilidade = rs.getDouble("VL_RENTABILIDADE");
				Date dtEntrada = rs.getDate("DT_ENTRADA");
				LocalDate dtEntradaLocal = dtEntrada.toLocalDate();
				Date dtVencimento = rs.getDate("DT_VENCIMENTO");
				LocalDate dtVencimentoLocal = dtVencimento.toLocalDate();
				Double vlInvestimento = rs.getDouble("VL_INVESTIMENTO");
				String nomeAplicacao = rs.getString("NM_APLICACAO");
				
				
				Investimento investimento = new Investimento(codigo, vlRentabilidade, dtEntradaLocal, dtVencimentoLocal, vlInvestimento, nomeAplicacao);
				
				lista.add(investimento);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return lista;
	}

	@Override
	public void editarInvestimento(Investimento investimento) throws DBException{
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_FNT_INVEST SET VL_RENTABILIDADE = ?, DT_ENTRADA = ?, DT_VENCIMENTO = ?, VL_INVESTIMENTO = ?, NM_APLICACAO = ? WHERE CD_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, investimento.getValorDaRentabilidade());
			stmt.setDate(2, investimento.getDataDeEntrada());
			stmt.setDate(3, investimento.getDataDeVencimento());
			stmt.setDouble(4, investimento.getValorDeInvestimento());
			stmt.setString(5, investimento.getNomeDoInvestimento());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void excluirInvestimento(String nomeDoInvestimento) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_FNT_INVEST WHERE NM_APLICACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeDoInvestimento);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Boolean fecharInvestimento(Integer codigoDoInvestimento) {
		
	}
}
