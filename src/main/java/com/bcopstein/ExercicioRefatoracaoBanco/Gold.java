package com.bcopstein.ExercicioRefatoracaoBanco;

class Gold implements Status
{
	/*
	 * @ ensures \result.getStrStatus() == "Gold";
	 */
	public Status getStatus() { return this; }
	
	/*
	 * @ ensures \result == "Gold";
	 */
	public String getStrStatus() { return "Gold"; }
	
	public double valorizaDeposito() { return 1.01; }
	
	public double getLimRetiradaDiaria() { return 50000.0; }
}
