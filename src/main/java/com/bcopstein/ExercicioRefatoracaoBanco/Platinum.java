package com.bcopstein.ExercicioRefatoracaoBanco;

class Platinum implements Status
{
	/*
	 * @ ensures \result.getStrStatus() == "Platinum";
	 */
	public Status getStatus() { return this; }
	
	/*
	 * @ ensures \result == "Platinum";
	 */
	public String getStrStatus() { return "Platinum"; }
	
	public double valorizaDeposito() { return 1.025; }
	
	public double getLimRetiradaDiaria() { return 500000.0; }
}
