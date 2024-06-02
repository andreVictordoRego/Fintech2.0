package br.com.fiap.fintech.teste;

import java.time.LocalDate;
import java.util.List;

import br.com.fiap.fintech.bean.Banco;
import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.InvestimentoAberto;
import br.com.fiap.fintech.bean.InvestimentoFechado;
import br.com.fiap.fintech.bean.TipoDeInvestimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteInvestimentoDAO {

	public static void main(String[] args) throws DBException {
		
		InvestimentoDAO dao = DAOFactory.getInvestimentoDAO();
				
//		//Cadastrar um investimento
//		Investimento investimento = new Investimento(
//				1,
//				"Investimento C",
//				TipoDeInvestimento.LCA,
//				Banco.CAIXA_ECONOMICA,
//				1.,
//				LocalDate.now(),
//				LocalDate.of(2028, 06, 30),
//				2500.0);
//		try {
//			dao.cadastrarNovoInvestimento(investimento);
//			System.out.println("Investimento cadastrado!");
//		} catch (DBException e) {
//			e.printStackTrace();
//		}
		
		
		//Buscar um produto pelo codigo e editar
		Investimento investimento = new Investimento(18,
				"Investimento H",
				TipoDeInvestimento.LCI,
				Banco.INTER,
				5.,
				LocalDate.now(),
				LocalDate.of(2028, 06, 30),
				2500.0);
		
		dao.editarInvestimento(investimento);
		
		System.out.println("Invetimento Editado.");
		
		//Listar investimentos
		List<Investimento> lista = dao.listarInvestimentos();
		for(Investimento item : lista) {
			System.out.println(item.getNomeDoInvestimento() + " " + item.getTipoDeInvestimento() + " " + item.getBanco() + " " + item.getValorDaRentabilidade() + " " + item.getDataDeEntrada() + " " + item.getDataDeVencimento() + " " + item.getValorDeInvestimento());
		}

		
//		//Remover um investimento
//		try {
//			dao.excluirInvestimento("Investimento C");
//			System.out.println("Investimento Excluido.");
//		} catch(DBException e) {
//			e.printStackTrace();
//		}
		
		
		
		
		/*Investimento investimento = new Investimento();
		InvestimentoFechado investimentoFechado = new InvestimentoFechado(LocalDate.of(2024, 5 , 31), 100.00);

        investimento.setInvestimentoFechado(investimentoFechado);
        
        System.out.println(investimento.getInvestimentoAberto());
        System.out.println(investimento.getInvestimentoFechado().toString());
        
        System.out.println("-----------------------------------------------------");
        
        Investimento investimento2 = new Investimento();
        InvestimentoAberto investimentoAberto = new InvestimentoAberto(LocalDate.of(2024, 5, 31), 1110.00);
        
        investimento2.setInvestimentoAberto(investimentoAberto);
        
        System.out.println(investimento2.getInvestimentoAberto().toString());
        System.out.println(investimento2.getInvestimentoFechado());
        
        Investimento investimento3 = new Investimento();
        InvestimentoAberto investimentoAberto3 = new InvestimentoAberto(LocalDate.of(2024, 5, 31), 2110.00);
        InvestimentoFechado investimentoFechado3 = new InvestimentoFechado(LocalDate.of(2024, 6 , 01 ), 150.00);
        
        investimento3.setInvestimentoAberto(investimentoAberto3);
        investimento3.setInvestimentoFechado(investimentoFechado3);
        
        System.out.println("-----------------------------------------------------");
        
        System.out.println(investimento3.getInvestimentoAberto().toString());
        System.out.println(investimento3.getInvestimentoFechado().toString());*/
	}

}
