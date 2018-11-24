package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.GregorianCalendar;
import java.util.List;

public class LogicaOperacoes {

	private Operacoes operacoes;
	private Contas contas;
	
	public LogicaOperacoes(){		
	}
	
	public Boolean operacaoDebito(double valor, Conta conta)
	{
		if (valor < 0.0 || valor > conta.getSaldo()) 
		{
			//throw new NumberFormatException("Saldo insuficiente");
			return false;
		}
		if(valor > conta.getLimRetiradaDiaria()) 
		{
			return false;
		}
		
		double totalDoDia = valor;		
		GregorianCalendar date = new GregorianCalendar();
		totalDoDia += Operacoes.getInstance().somaValoresDiarios(conta.getNumero(), 1);; 
		Operacoes.getInstance().loadOperacoes();		
		
		if(totalDoDia > conta.getLimRetiradaDiaria()) 
		{
			return false;
		}
		else
		{
			conta.retirada(valor);
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
					1);
			Operacoes.getInstance().addOperacao(op);
			Operacoes.getInstance().saveOperacoes();
			return true;
		}		
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
