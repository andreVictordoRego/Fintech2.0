package br.com.fiap.fintech.teste;

import java.time.LocalDate;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.InvestimentoAberto;
import br.com.fiap.fintech.bean.InvestimentoFechado;

public class TesteInvestimentoDAO {

	public static void main(String[] args) {
		Investimento investimento = new Investimento();
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
        System.out.println(investimento3.getInvestimentoFechado().toString());
	}

}
