package br.com.fiap.fintech.testes;

import java.time.LocalDate;
import java.util.List;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class ObjetivoDAOTeste {

	public static void main(String[] args) throws DBException {
				
		Objetivo obj = new Objetivo();
		obj.setCodigoDoObjetivo(7);
		obj.setNumeroDeCPF(38580555599L);
		obj.setNomeDoObjetivo("Compra Ferrari");
		obj.setValorAtual(00.0);
		obj.setValorDoObjetivo(5300.0);
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
		
		ObjetivoDAO dao = DAOFactory.getObjetivoDAO();
		
		List<Objetivo> lista = dao.listarObjetivos(obj.getNumeroDeCPF());
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
		
		dao.excluirObjetivo(obj.getCodigoDoObjetivo());
		System.out.println("excluido!");
		 
		List<Objetivo> lista1 = dao.listarObjetivos(obj.getNumeroDeCPF());
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
		
		dao.criarNovoObjetivo(obj);
		System.out.println("Objetivo Cadastrado");
		
		dao.editarObjetivo(obj2);		
		System.out.println("editado");

	}

}


