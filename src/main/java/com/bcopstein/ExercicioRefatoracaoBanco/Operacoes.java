package com.bcopstein.ExercicioRefatoracaoBanco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	
	public List<Operacao> getExtrato(int conta)
	{
		List<Operacao> opsConta = new LinkedList<Operacao>();
		for(Operacao op: this.operacoes)
		{
			if(op.getNumeroConta() == conta)
			{
				opsConta.add(op);
			}
		}
		return opsConta;
	}
	
	public Operacao operacaoDebito(double valor, Conta conta)
	{
		if (valor < 0.0 || valor > conta.getSaldo()) 
		{
			throw new NumberFormatException("Saldo insuficiente");			
		}
		if(valor > conta.getLimRetiradaDiaria()) 
		{
			throw new IllegalArgumentException("Ultrapassa limite diario");
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
			throw new IllegalArgumentException("Ultrapassa limite diario");
		}
		else
		{
			conta.retirada(valor);
			Operacao op = DebitoFactory.instance().factoryMethod(
					date.get(GregorianCalendar.DAY_OF_MONTH),
					date.get(GregorianCalendar.MONTH)+1,
					date.get(GregorianCalendar.YEAR),
					date.get(GregorianCalendar.HOUR),
					date.get(GregorianCalendar.MINUTE),
					date.get(GregorianCalendar.SECOND),
					conta.getNumero(),
					conta.getStatus(),
					valor);
			this.addOperacao(op);
			return op;
		}	
	} 
	
	public Operacao operacaoCredito(double valor, Conta conta)
	{
		if (valor < 0.0)
		{
			throw new NumberFormatException("Valor invalido");
		}
		conta.deposito(valor);

		GregorianCalendar date = new GregorianCalendar();
		Operacao op = CreditoFactory.instance().factoryMethod(
				date.get(GregorianCalendar.DAY_OF_MONTH),
				date.get(GregorianCalendar.MONTH)+1,
				date.get(GregorianCalendar.YEAR),
				date.get(GregorianCalendar.HOUR),
				date.get(GregorianCalendar.MINUTE),
				date.get(GregorianCalendar.SECOND),
				conta.getNumero(),
				conta.getStatus(),
				valor);
		this.addOperacao(op);
		return op;
	}

	public double getSaldoMedioMes(int conta, int mes, int ano)
	{		
		ArrayList<Double> saldosDias = new ArrayList<Double>();
		double totalMes = 0;
		int day = 1;
		double saldoDia = 0;
		ArrayList<Operacao> opsMes = new ArrayList<Operacao>();
		for(Operacao op: this.operacoes)
		{
			if(op.getNumeroConta() == conta)
			{				
				if(op.getAno() < ano)
				{
					if(op.getTipoOperacao() == 0) //CRÉDITO
					{
						saldoDia += op.getValorOperacao();
					}
					else //DÉBITO
					{
						saldoDia -= op.getValorOperacao();
					}
				}
				
				else if(op.getMes() < mes && op.getAno() == ano)
				{					
					if(op.getTipoOperacao() == 0) //CRÉDITO
					{
						saldoDia += op.getValorOperacao();
					}
					else //DÉBITO
					{
						saldoDia -= op.getValorOperacao();
					}
				}
				
				else if(op.getAno() == ano && op.getMes() == mes)
				{
					opsMes.add(op);
				}
			}
		}
		
		if(opsMes.isEmpty())
		{
			for(int i = 1; i < 31; i++)
			{
				saldosDias.add(saldoDia);
			}
		}
		else
		{
			for(Operacao op: opsMes)
			{
				while(op.getDia() > day)
				{
					saldosDias.add(saldoDia);
					day++;
				}
				
				if(op.getTipoOperacao() == 0)
				{
					saldoDia += op.getValorOperacao();
				}
				else
				{
					saldoDia -= op.getValorOperacao();
				}
			}
			while(saldosDias.size() < 30)
			{
				saldosDias.add(saldoDia);
			}
		}
		for(Double d: saldosDias)
		{
			totalMes += d;
		}
		double media = totalMes/30.0;
		return media;
	}

	
	public int totalCreditos(int codConta, int mes, int ano)
	{
		int total = 0;
		GregorianCalendar date = new GregorianCalendar();
		for(Operacao o : operacoes) 
		{					
			if(o.getNumeroConta() == codConta && o.getMes() == mes && o.getAno() == ano && 
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
			if(o.getNumeroConta() == codConta && o.getMes() == mes && o.getAno() == ano && 
					o.getTipoOperacao() == 1) 
			{
				total+= 1;
			}
		}
		return total;
	}
}
