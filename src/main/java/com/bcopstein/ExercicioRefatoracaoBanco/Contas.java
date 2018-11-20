package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.Map;
import java.util.Collection;

public class Contas 
{
	
	private static Contas instance = null;
	private Map<Integer, Conta> contas;
	private Conta corrente = null;

	private Contas() 
	{
		this.contas = Persistencia.getInstance().loadContas();
	}
	
	public static Contas getInstance()
	{
		if(instance == null)
		{
			instance = new Contas();
		}
		return instance;
	}
	
	public Conta getCorrente()
	{
		return this.corrente;
	}
	
	public void setCorrente(Conta curr)
	{
		this.corrente = curr;
	}
	
	public void save()
	{
		Persistencia.getInstance().saveContas((Collection<Conta>)this.contas);
	}

}
