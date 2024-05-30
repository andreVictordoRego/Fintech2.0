package br.com.fiap.fintech.dao.implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleObjetivoDAO implements ObjetivoDAO{

	private Connection conexao;
	
	@Override
	public void criarNovoObjetivo(Objetivo objetivo, Usuario usuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_FNT_OBJTVO (CD_OBJETIVO, NR_CPF, NM_OBJETIVO, VL_OBJETIVO, VL_ATUAL, DT_CRIACAO, DT_CONCLUSAO, DS_OBJETIVO) VALUES (SQ_TB_OBJTVO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getNumeroDeCPF());
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
			try {
				stmt.close();
				conexao.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Objetivo> listarObjetivos(Usuario usuario) {
		
		List<Objetivo> lista = new ArrayList<Objetivo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "SELECT * FROM T_FNT_OBJTVO WHERE NR_CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, usuario.getNumeroDeCPF());
						
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Integer codigo = rs.getInt("CD_OBJETIVO");
				String nome = rs.getString("NM_OBJETIVO");
				Double valor = rs.getDouble("VL_OBJETIVO");
				Double valorAtual = rs.getDouble("VL_ATUAL");
				Date dtCriacao = rs.getDate("DT_CRIACAO");
				LocalDate dtCriacaoLocal = dtCriacao.toLocalDate();
				Date dtConclusao = rs.getDate("DT_CONCLUSAO");
				LocalDate dtConclusaoLocal = dtConclusao.toLocalDate();
				String descricao = rs.getString("DS_OBJETIVO");
				
				Objetivo objetivo = new Objetivo(codigo, nome, valor, valorAtual, dtCriacaoLocal, dtConclusaoLocal, descricao);
				
				lista.add(objetivo);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public void editarObjetivo(Objetivo objetivo) throws DBException {
		
	}

	@Override
	public void excluirObjetivo(String nomeDoObjetivo) {
		
	}



}
