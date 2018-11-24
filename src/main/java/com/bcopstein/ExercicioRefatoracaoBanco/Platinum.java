package com.bcopstein.ExercicioRefatoracaoBanco;

class Platinum implements Status
{
	public Status getStatus() { return this; }
	
	public String getStrStatus() { return "Platinum"; }
	
	public double valorizaDeposito() { return 1.025; }
	
	public double getLimRetiradaDiaria() { return 500000.0; }
}
