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
		obj.setNomeDoObjetivo("Compra Video Game");
		obj.setValorAtual(00.0);
		obj.setValorDoObjetivo(5000.0);
		obj.setDataDeCriacao(LocalDate.of(2024, 5, 1));
		obj.setDataDeConclusao(LocalDate.of(2026, 6, 1));
		obj.setDescricaoDoObjetivo("Descricao completa do produto");

		Objetivo obj2 = new Objetivo();
		obj2.setCodigoDoObjetivo(3);
		obj2.setNomeDoObjetivo("Compra PS5");
		obj2.setValorAtual(10.0);
		obj2.setValorDoObjetivo(3400.0);
		obj2.setDataDeCriacao(LocalDate.of(2024, 5, 1));
		obj2.setDataDeConclusao(LocalDate.of(2026, 6, 1));
		obj2.setDescricaoDoObjetivo("Descricao completa do PS5");
		
		OracleObjetivoDAO dao = new OracleObjetivoDAO();
		
		/*
		dao.criarNovoObjetivo(obj);
		System.out.println("Objetivo Cadastrado");
		
		dao.editarObjetivo(obj2);
		System.out.println("editado");
		 */
		 
		List<Objetivo> lista1 = dao.listarObjetivos();
		for(Objetivo item : lista1) {
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
		
		dao.excluirObjetivo("Compra Carro");

		
		List<Objetivo> lista = dao.listarObjetivos();
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


