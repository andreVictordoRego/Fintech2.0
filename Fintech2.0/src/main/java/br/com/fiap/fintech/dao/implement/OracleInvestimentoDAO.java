package br.com.fiap.fintech.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Banco;
import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.InvestimentoAberto;
import br.com.fiap.fintech.bean.InvestimentoFechado;
import br.com.fiap.fintech.bean.TipoDeInvestimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {
	
	private Connection conexao = ConnectionManager.getInstance().getConnection();

	public OracleInvestimentoDAO() {
		conexao = ConnectionManager.getInstance().getConnection();
	}
	private PreparedStatement stmt;
	private ResultSet rs;

	@Override
	public void cadastrarNovoInvestimento(Investimento investimento) throws DBException, SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		
		PreparedStatement stmt = null;
		

		String sql = "INSERT INTO T_FNT_INVST (CD_INVESTIMENTO, NR_CPF, VL_RENTABILIDADE, DT_ENTRADA, DT_VENCIMENTO, VL_INVESTIMENTO, NM_APLICACAO, TP_INVST, BANCO) VALUES (SQ_TB_INVST.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {			
			stmt = conexao.prepareStatement(sql);			
			//stmt.setLong(1, usuario.getNumeroDoCPF);
			stmt.setLong(1, 41300);
			
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
	public void lancarNovoInvestimentoAberto(Investimento investimento) throws SQLException, DBException {
		
		conexao = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmtBuscaCodigo = null;
		PreparedStatement stmtInvestimentoAberto = null;
		ResultSet rsBuscaCodigo = null;
		Integer codigo = 0;
		
		String sqlConsultaCodigo = "SELECT CD_INVESTIMENTO FROM T_FNT_INVST WHERE NM_APLICACAO = ?";
		
		String sqlInvestimentoAberto = "INSERT INTO T_FNT_INV_ABRTO (CD_INVESTIMENTO, DT_MES, VL_TOTAL_RENT) VALUES (?, ?, ?)";
		
		try {
			conexao.setAutoCommit(false);
			
			stmtBuscaCodigo = conexao.prepareStatement(sqlConsultaCodigo);

			stmtBuscaCodigo.setString(1, investimento.getNomeDoInvestimento());
			rsBuscaCodigo = stmtBuscaCodigo.executeQuery();
			
			if(rsBuscaCodigo.next()) {
				System.out.println("2");
				codigo = rsBuscaCodigo.getInt("CD_INVESTIMENTO");
				System.out.println("2");
			}
			
					
			stmtInvestimentoAberto = conexao.prepareStatement(sqlInvestimentoAberto);
			stmtInvestimentoAberto.setInt(1, codigo);
			//VERIFICAR FUNCIONAMENTO DA SERVLET
			stmtInvestimentoAberto.setDate(2, Date.valueOf(investimento.getDataDeEntrada()));
			stmtInvestimentoAberto.setDouble(3, 0.0);
			
			
			stmtInvestimentoAberto.executeUpdate();
			conexao.commit();
			
		} catch (SQLException e) {
			conexao.rollback();
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar investimento.");
		} finally {
			try {
				if(stmtBuscaCodigo!= null) stmtBuscaCodigo.close();
				if (stmtInvestimentoAberto!= null) stmtInvestimentoAberto.close();
				if(rsBuscaCodigo!= null) rsBuscaCodigo.close();
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
			//stmt.setLong(1, usuario.getNumeroDoCPF);
			stmt.setLong(1, 41300);

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
			if (rs != null) {
				try {
					rs.close();
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
			//stmt.setLong(9, usuario.getNumeroDoCPF);
			stmt.setLong(9, 41300);
			
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

	@Override
	public void excluirInvestimento(String nomeDoInvestimento) {
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		PreparedStatement stmtAberto = null;
		PreparedStatement stmtFechado = null;

		try {
			String sqlInvAberto = "DELETE FROM T_FNT_INV_ABRTO WHERE CD_INVESTIMENTO IN (SELECT CD_INVESTIMENTO FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?)";
			stmtAberto = conexao.prepareStatement(sqlInvAberto);
			
			stmtAberto.setString(1, nomeDoInvestimento);
			//stmtAberto.setLong(2, usuario.getNumeroDoCPF);
			stmtAberto.setLong(2, 41300);
			
			stmtAberto.executeUpdate();
			
			String sqlFechado = "DELETE FROM T_FNT_INV_FCHDO WHERE CD_INVESTIMENTO IN (SELECT CD_INVESTIMENTO FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?)";
			
			stmtFechado = conexao.prepareStatement(sqlFechado);
			
			stmtFechado.setString(1, nomeDoInvestimento);
			//stmtFechado.setLong(2, usuario.getNumeroDoCPF);
			stmtFechado.setLong(2, 41300);
			
			stmtFechado.executeUpdate();
			
			
			String sql = "DELETE FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeDoInvestimento);
			//stmt.setLong(2, usuario.getNumeroDoCPF);
			stmt.setLong(2, 41300);
			
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
			if (stmtAberto != null) {
				try {
					stmtAberto.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmtFechado != null) {
				try {
					stmtFechado.close();
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
	public void excluirInvestimentoAberto(String nomeDoInvestimento) {
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		PreparedStatement stmtAberto = null;

		try {
			String sqlInvAberto = "DELETE FROM T_FNT_INV_ABRTO WHERE CD_INVESTIMENTO IN (SELECT CD_INVESTIMENTO FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?)";
			stmtAberto = conexao.prepareStatement(sqlInvAberto);
			
			stmtAberto.setString(1, nomeDoInvestimento);
			//stmtAberto.setLong(2, usuario.getNumeroDoCPF);
			stmtAberto.setLong(2, 41300);
			
			stmtAberto.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtAberto != null) {
				try {
					stmtAberto.close();
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
	public void excluirInvestimentoFechado(String nomeDoInvestimento) {
		
		conexao = ConnectionManager.getInstance().getConnection();
		
		PreparedStatement stmtFechado = null;

		try {			
			String sqlFechado = "DELETE FROM T_FNT_INV_FCHDO WHERE CD_INVESTIMENTO IN (SELECT CD_INVESTIMENTO FROM T_FNT_INVST WHERE NM_APLICACAO = ? AND NR_CPF = ?)";
			
			stmtFechado = conexao.prepareStatement(sqlFechado);
			
			stmtFechado.setString(1, nomeDoInvestimento);
			//stmtFechado.setLong(2, usuario.getNumeroDoCPF);
			stmtFechado.setLong(2, 41300);
			
			stmtFechado.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtFechado != null) {
				try {
					stmtFechado.close();
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
	public List<InvestimentoAberto> listarInvestimentosAbertos(Integer codigoDoinvestimento) {
		List<InvestimentoAberto> investimentosAbertos = new ArrayList<InvestimentoAberto>();
		InvestimentoAberto investimentoAberto;
		String sqlQuery = "SELECT * FROM T_FNT_INV_ABRTO "
				+ "AS ABERTO INNER JOIN T_FNT_INVST AS INV "
				+ "WHERE ABERTO.cd_investimento = INV.cd_investimento";
		
		try {
			stmt = conexao.prepareStatement(sqlQuery);
			stmt.setLong(1, codigoDoinvestimento);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				investimentoAberto = new InvestimentoAberto();
				investimentoAberto.setChavePrimariaEstrantrangeira(rs.getInt("cd_investimento"));
				investimentoAberto.setValorTotalDoRendimento(rs.getDouble("vl_total_rent"));
				investimentosAbertos.add(investimentoAberto);
			}
		} catch(SQLException e) {
			System.err.println("ERRO AO LISTAR INVESTIMENTOS ABERTOS");
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
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
		
		return investimentosAbertos;
	}
	
	@Override
	public void registrarFechamentoDeInvestimento(InvestimentoAberto investimentoAberto) throws SQLException {
		
		conexao = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		
		String sqlQuery = "INSERT INTO T_FNT_INV_FCHDO("
				+ "cd_investimento, dt_saida, vl_liquidez) "
				+ "VALUES(?, ?, ?) ";
		
		try {
			stmt = conexao.prepareStatement(sqlQuery);
			stmt.setInt(1, investimentoAberto.getChavePrimariaEstrantrangeira());
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			stmt.setDouble(3, 1000.0);
						
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.err.println("ERRO AO REGISTRAR FECHAMENTO");
			e.printStackTrace();
		} finally {
			if(stmt!= null) {
				stmt.close();
			}
			if(conexao!= null) {
				conexao.close();
			}
		}
	}
	
	public Boolean fecharInvestimento(Integer codigoDoInvestimento) throws SQLException {
		String sqlQuery = "DELETE FROM T_FNT_INV_ABRTO "
				+ "WHERE cd_investimento = ?";
		
		try {
			stmt = conexao.prepareStatement(sqlQuery);
			stmt.setInt(1, codigoDoInvestimento);
			stmt.executeUpdate();
			return true;
		} catch(SQLException e) {
			System.err.println("ERRO AO EXCLUIR INVESTIMENTO PARA FECHAMENTO");
			e.printStackTrace();
			return false;
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conexao != null) {
				conexao.close();
			}
		}
	}

	
}
