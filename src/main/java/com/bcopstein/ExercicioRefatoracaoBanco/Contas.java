package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class Contas 
{
	
	private static Contas instance = null;
	private Map<Integer, Conta> contas;
	private Conta corrente = null;

	private Contas() 
	{
		this.contas = new HashMap<Integer, Conta>();
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
	
	public void setCorrente(int curr)
	{		
		Conta c = this.getConta(curr);
		if( c == null)
		{
			throw new NumberFormatException("Conta invalida");
		}
		this.corrente = this.getConta(curr);
	}
	
	public void save()
	{
		Persistencia.getInstance().saveContas(this.contas);
	}

	public void loadContas()
	{
		this.contas = Persistencia.getInstance().loadContas();
	}
	
	public Conta getConta(int codConta)
	{
		return contas.get(codConta);
	}
}
