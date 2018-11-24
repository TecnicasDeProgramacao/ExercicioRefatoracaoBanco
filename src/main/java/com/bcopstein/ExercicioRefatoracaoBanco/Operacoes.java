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
		for(Operacao o : operacoes) 
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
	
	public List<Operacao> getListaOperacoes()
	{
		return operacoes;
	}
}
















