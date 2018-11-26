package com.bcopstein.ExercicioRefatoracaoBanco;

public interface Status 
{
	public Status getStatus();
	
	public String getStrStatus();
	
	/*
	 * @ ensures (getStrStatus() == "Silver") ==> \result == 1.0;
	 * @ ensures (getStrStatus() == "Gold") ==> \result == 1.01;
	 * @ ensures (getStrStatus() == "Platinum") ==> \result == 1.025;  
	 */
	public double valorizaDeposito();
	
	/*
	 * @ ensures (getStrStatus() == "Silver") ==> \result == 5000.0;
	 * @ ensures (getStrStatus() == "Gold") ==> \result == 50000.0;
	 * @ ensures (getStrStatus() == "Platinum") ==> \result == 500000.0;  
	 */
	public double getLimRetiradaDiaria();
}
