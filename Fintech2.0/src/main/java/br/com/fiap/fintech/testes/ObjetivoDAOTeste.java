package br.com.fiap.fintech.testes;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.implement.OracleObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;

public class ObjetivoDAOTeste {

	public static void main(String[] args) throws DBException {
				
		Objetivo obj = new Objetivo();
		obj.setCodigoDoObjetivo(1);
		obj.setNomeDoObjetivo("Compra Casa");
		obj.setValorAtual(00.0);
		obj.setValorDoObjetivo(750000.0);
		obj.setDataDeCriacao(LocalDate.of(2024, 5, 1));
		obj.setDataDeConclusao(LocalDate.of(2026, 6, 1));
		obj.setDescricaoDoObjetivo("Descricao completa da casa");

		
		Usuario usuario = new Usuario(65881, "Andre Victor", LocalDate.of(1991, 03, 28), "Masculino", "email@email.com", null, "12X3456");
		OracleObjetivoDAO dao = new OracleObjetivoDAO();
		
		//dao.criarNovoObjetivo(obj, usuario);
		//System.out.println("Objetivo Cadastrado");
		
		
		List<Objetivo> lista = dao.listarObjetivos(usuario);
		for(Objetivo item : lista) {
			System.out.println(
					"Codigo: " +item.getCodigoDoObjetivo()+ " " +
					"Nome: " +item.getNomeDoObjetivo()+ " " +
					"Valor Total: " +item.getValorDoObjetivo()+ " " +
					"Valor Atual: " +item.getValorAtual()+ " " +
					"Data Criacao: " +item.getDataDeCriacao()+ " " +
					"Data Conclusao: " +item.getDataDeConclusao()+ " " +
					"Descricao: " +item.getDescricaoDoObjetivo()
					);
		}
		
	}

}


