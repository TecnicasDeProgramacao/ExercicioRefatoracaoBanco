package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Operacoes {

	private static Operacoes instance;
	private List<Operacao> operacoes;
	
	private Operacoes() 
	{
		this.operacoes = new LinkedList<Operacao>();
	}

	public static Operacoes getInstance() 
	{
		if(instance == null)
		{
			instance = new Operacoes();
		}
		
		return (instance);
	}
	
	public List<Operacao> operacaoPorConta(int numConta)
	{
		List<Operacao> opsSelecionadas = new LinkedList<Operacao>();
		if(this.operacoes.size() <= 0) 
		{
			return opsSelecionadas;
		}
		
		for(Operacao op : this.operacoes)
		{
			if(op.getNumeroConta() == numConta)
			{
				opsSelecionadas.add(op);
			}
		}		
		return opsSelecionadas;
	}
	
	public void loadOperacoes()
	{
		this.operacoes = Persistencia.getInstance().loadOperacoes();
	}

	public void addOperacao(Operacao op)
	{
		this.operacoes.add(op);
	}
	
	public void saveOperacoes()
	{
		Persistencia.getInstance().saveOperacoes(this.operacoes);
	}
	
	public double somaValoresDiarios(int numConta, int tipoOperacao)
	{
		this.loadOperacoes();
		int totalDoDia = 0;
		GregorianCalendar date = new GregorianCalendar();
		for(Operacao o : this.getListaOperacoes()) 
		{					
			if(o.getNumeroConta() == numConta && o.getDia() == date.get(GregorianCalendar.DAY_OF_MONTH)
					&& o.getMes() == date.get(GregorianCalendar.MONTH)+1 && o.getAno() == date.get(GregorianCalendar.YEAR) && 
					o.getTipoOperacao() == tipoOperacao) 
			{
				totalDoDia += o.getValorOperacao();
			}
		}
		return totalDoDia;
	}
	
	public List<Operacao> getExtrato(int conta)
	{
		List<Operacao> opsConta = new LinkedList<Operacao>();
		for(Operacao op: this.getListaOperacoes())
		{
			if(op.getNumeroConta() == conta)
			{
				opsConta.add(op);
			}
		}
		return opsConta;
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
		
		int totalDoDia = 0;
		GregorianCalendar date = new GregorianCalendar();
		totalDoDia += valor; 

		for(Operacao o : operacoes) 
		{					
			if(o.getNumeroConta() == conta.getNumero() && o.getDia() == date.get(GregorianCalendar.DAY_OF_MONTH)
					&& o.getMes() == date.get(GregorianCalendar.MONTH)+1 && o.getAno() == date.get(GregorianCalendar.YEAR) && 
					o.getTipoOperacao() == 1) 
			{
				totalDoDia += o.getValorOperacao();
			}
		}          	            	  

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
			this.addOperacao(op);
			this.saveOperacoes();
			return true;
		}	
	} 
	
	public Boolean operacaoCredito(double valor, Conta conta)
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
		this.addOperacao(op);
		this.saveOperacoes();
		return true;
	}
	
	public int totalCreditos(int codConta, int mes, int ano)
	{
		int total = 0;
		GregorianCalendar date = new GregorianCalendar();		

		for(Operacao o : operacoes) 
		{					
			if(o.getNumeroConta() == codConta && o.getMes() == mes+1 && o.getAno() == ano && 
					o.getTipoOperacao() == 0) 
			{
				total+= 1;
			}
		}
		return total;
	}
	
	public int totalDebitos(int codConta, int mes, int ano)
	{
		int total = 0;		

		for(Operacao o : operacoes) 
		{					
			if(o.getNumeroConta() == codConta && o.getMes() == mes+1 && o.getAno() == ano && 
					o.getTipoOperacao() == 1) 
			{
				total+= 1;
			}
		}
		return total;
	}
}





