package br.com.fiap.fintech.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.fintech.bean.Banco;
import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.TipoDeInvestimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {
	
	private Connection conexao;

	public OracleInvestimentoDAO() {
		conexao = ConnectionManager.getInstance().getConnection();
	}
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public void cadastrarNovoInvestimento(Investimento investimento) throws DBException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO T_FNT_INVST (CD_INVESTIMENTO, NR_CPF, VL_RENTABILIDADE, DT_ENTRADA, DT_VENCIMENTO, VL_INVESTIMENTO, NM_APLICACAO, TP_INVST, BANCO) VALUES (SQ_TB_INVST.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, 41300);
			stmt.setDouble(2, investimento.getValorDaRentabilidade());
			stmt.setDate(3, Date.valueOf(investimento.getDataDeEntrada()));
			stmt.setDate(4, Date.valueOf(investimento.getDataDeVencimento()));
			stmt.setDouble(5, investimento.getValorDeInvestimento());
			stmt.setString(6, investimento.getNomeDoInvestimento());
			stmt.setString(7, investimento.getTipoDeInvestimento().getTipoDeInvestimento());
			stmt.setString(8, investimento.getBanco().getBanco());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar investimento.");
		} finally {
			try {
			if (stmt != null) stmt.close();
			if (conexao != null) conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Investimento> listarInvestimentos() {
		List<Investimento> lista = new ArrayList<Investimento>();
		
		conexao = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_INVST WHERE NR_CPF = ?");
			stmt.setInt(1, 41300);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Investimento investimento = new Investimento();
				investimento.setCodigoDoInvestimento(rs.getInt("CD_INVESTIMENTO"));
				investimento.setValorDaRentabilidade(rs.getDouble("VL_RENTABILIDADE"));
				investimento.setDataDeEntrada(rs.getDate("DT_ENTRADA").toLocalDate());
				investimento.setDataDeVencimento(rs.getDate("DT_VENCIMENTO").toLocalDate());
				investimento.setValorDeInvestimento(rs.getDouble("VL_INVESTIMENTO"));
				investimento.setNomeDoInvestimento(rs.getString("NM_APLICACAO"));
				
				String tipoInvestimentoString = rs.getString("TP_INVST");
				TipoDeInvestimento tipoInvestimento = TipoDeInvestimento.valueOf(tipoInvestimentoString.toUpperCase().replace(" ", "_"));
				investimento.setTipoDeInvestimento(tipoInvestimento);
				
				String tipoBancoString = rs.getString("BANCO").toUpperCase().replace(" ", "_");
				Banco banco = Banco.valueOf(tipoBancoString);
				investimento.setBanco(banco);
				
				lista.add(investimento);
			}
		} catch (SQLException e) {
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
	public void editarInvestimento(Investimento investimento) throws DBException {
		
		conexao = ConnectionManager.getInstance().getConnection();

		try {
			String sql = "UPDATE T_FNT_INVST SET VL_RENTABILIDADE = ?, DT_ENTRADA = ?, DT_VENCIMENTO = ?, VL_INVESTIMENTO = ?, NM_APLICACAO = ?, TP_INVST = ?, BANCO = ? WHERE CD_INVESTIMENTO = ? AND NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, investimento.getValorDaRentabilidade());
			stmt.setDate(2, Date.valueOf(investimento.getDataDeEntrada()));
			stmt.setDate(3, Date.valueOf(investimento.getDataDeVencimento()));
			stmt.setDouble(4, investimento.getValorDeInvestimento());
			stmt.setString(5, investimento.getNomeDoInvestimento());
			stmt.setString(6, investimento.getTipoDeInvestimento().getTipoDeInvestimento());
			stmt.setString(7, investimento.getBanco().getBanco());
			stmt.setInt(8, investimento.getCodigoDoInvestimento());
			stmt.setInt(9, 41300);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao editar investimento.");
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

	public void excluirInvestimento(String nomeDoInvestimento) {
		
		conexao = ConnectionManager.getInstance().getConnection();

		try {
			String sql = "DELETE FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeDoInvestimento);
			stmt.setInt(2, 41300);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
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
	}

	public Boolean fecharInvestimento(Integer codigoDoInvestimento) {

	}
}
