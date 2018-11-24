package com.bcopstein.ExercicioRefatoracaoBanco;

class Silver implements Status
{
	public Status getStatus() { return this; }
	
	public String getStrStatus() { return "Silver"; }
	
	public double valorizaDeposito() { return 1.0; }
	
	public double getLimRetiradaDiaria() { return 5000.0; }
}
