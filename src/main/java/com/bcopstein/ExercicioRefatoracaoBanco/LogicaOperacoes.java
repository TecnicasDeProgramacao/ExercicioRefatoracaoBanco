package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.GregorianCalendar;
import java.util.List;

public class LogicaOperacoes {

	private Operacoes operacoes;
	private Contas contas;
	
	public LogicaOperacoes(){		
	}
	
	
	/*public Boolean operacaoCredito(double valor, Conta conta)
	{
		
		if (valor < 0.0) 
		{
			return false;
		}
		this.loadOperacoes();
		conta.deposito(valor);
		
		GregorianCalendar date = new GregorianCalendar();
		Operacao op = new Operacao(
				date.get(GregorianCalendar.DAY_OF_MONTH),
				date.get(GregorianCalendar.MONTH)+1,
				date.get(GregorianCalendar.YEAR),
				date.get(GregorianCalendar.HOUR),
				date.get(GregorianCalendar.MINUTE),
				date.get(GregorianCalendar.SECOND),
				conta.getNumero(),
				conta.getStatus(),
				valor,
				0);
		operacoes.add(op);   
		return true;
	}*/
	
	
}
