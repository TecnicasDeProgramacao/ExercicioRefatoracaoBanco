package com.bcopstein.ExercicioRefatoracaoBanco;

public class StatusContaFactory 
{
	enum Tipo {SILVER, GOLD, PLATINUM};
	
	public static StatusContaFactory scf = null;
	
	private StatusContaFactory() {}
	
	public static StatusContaFactory instance()
	{
		if(scf == null)
		{
			return new StatusContaFactory();
		}
		return scf;
	}
	
	public Status createStatus(Tipo st)
	{
		Status s = null;
		switch(st)
		{
			case SILVER: s = new Silver(); break;
			case GOLD: s = new Gold(); break;
			case PLATINUM: s = new Platinum(); break;
			default: throw new IllegalArgumentException();
		}
		return s;
	}

}
