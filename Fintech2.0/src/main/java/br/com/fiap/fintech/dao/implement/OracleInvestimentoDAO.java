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

public class OracleInvestimentoDAO implements InvestimentoDAO {

	private Connection conexao = ConnectionManager.getInstance().getConnection();
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public void cadastrarNovoInvestimento(Investimento investimento) throws DBException {

		try {
			String sql = "INSERT INTO T_FNT_INVEST (CD_INVESTIMENTO, NR_CPF, VL_RENTABILIDADE, DT_ENTRADA, DT_VENCIMENTO, VL_INVESTIMENTO, NM_APLICACAO) VALUES (SQ_TB_INVEST.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInteger(1, usuario.getNumeroDeCPF());
			stmt.setDouble(2, investimento.getValorDaRentabilidade());
			stmt.setDate(3, Date.valueOf(investimento.getDataDeEntrada()));
			stmt.setDate(4, Date.valueOf(investimento.getDataDeVencimento()));
			stmt.setDouble(5, investimento.getValorDeInvestimento());
			stmt.setString(6, investimento.getNomeDoInvestimento());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar investimento.");
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
	public List<Investimento> listarInvestimentos() {
		List<Investimento> lista = new ArrayList<Investimento>();
		Investimento investimento = new Investimento();

		try {
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_INVEST WHERE NR_CPF = ?");
			stmt.setInt(1, usuario.getNumeroDoCPF);

			rs = stmt.executeQuery();

			while (rs.next()) {
				investimento.setCodigoDoInvestimento(rs.getInt("CD_INVESTIMENTO"));
				investimento.setValorDaRentabilidade(rs.getDouble("VL_RENTABILIDADE"));
				investimento.setDataDeEntrada(rs.getDate("DT_ENTRADA").toLocalDate());
				investimento.setDataDeVencimento(rs.getDate("DT_VENCIMENTO").toLocalDate());
				investimento.setValorDeInvestimento(rs.getDouble("VL_INVESTIMENTO"));
				investimento.setNomeDoInvestimento(rs.getString("NM_APLICACAO"));

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

		try {
			String sql = "UPDATE T_FNT_INVEST SET VL_RENTABILIDADE = ?, DT_ENTRADA = ?, DT_VENCIMENTO = ?, VL_INVESTIMENTO = ?, NM_APLICACAO = ? WHERE CD_INVESTIMENTO = ? AND NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, investimento.getValorDaRentabilidade());
			stmt.setDate(2, Date.valueOf(investimento.getDataDeEntrada()));
			stmt.setDate(3, Date.valueOf(investimento.getDataDeVencimento()));
			stmt.setDouble(4, investimento.getValorDeInvestimento());
			stmt.setString(5, investimento.getNomeDoInvestimento());
			stmt.setInt(6, investimento.getCodigoDoInvestimento());
			stmt.setInt(7, usuario.getNumeroDeCPF());
			
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

		try {
			String sql = "DELETE FROM T_FNT_INVEST WHERE NM_APLICACAO = ? AND NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeDoInvestimento);
			stmt.setInteger(2, usuario.getNumeroCPF);
			
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
