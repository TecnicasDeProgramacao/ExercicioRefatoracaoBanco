package com.bcopstein.ExercicioRefatoracaoBanco;

import org.jmlspecs.models.JMLDouble; /*EH NECESSARIO INCLUIR O JAR DO OPENJML PARA O ECLIPSE NAO RECLAMAR*/
public class Conta {
	/*
	 * @ instance initially saldo = 0;
	 * @ instance initially status.getStrStatus() == "Silver";
	 * @ instance invariant numero > 0;
	 * @ instance invariant saldo >= 0;
	 * @ instance invariant nome.length() > 0 && nome != null;
	 */
	
	public final int LIM_SILVER_GOLD = 50000;
	public final int LIM_GOLD_PLATINUM = 200000;
	public final int LIM_PLATINUM_GOLD = 100000;
	public final int LIM_GOLD_SILVER = 25000;

	private int numero;
	private String correntista;
	private double saldo;
	
	private Status status;

	public Conta(int umNumero, String umNome) {
		numero = umNumero;
		correntista = umNome;
		saldo = 0.0;
		status = new Silver();
	}
	
	public Conta(int umNumero, String umNome, double umSaldo, Status umStatus) {
		numero = umNumero;
		correntista = umNome;
		saldo = umSaldo;
		status = umStatus;
	}
	
	/*
	 * @ ensures \result >= 0;
	 */
	public /*@ pure helper @*/ double getSaldo() {
		return saldo;
	}

	/*
	 * @ ensures \result > 0;
	 */
	public int getNumero() {
		return numero;
	}
	
	/*
	 * @ ensures \result != null && result.length() > 0;
	 */
	public String getCorrentista() {
		return correntista;
	}
	
	/*
	 * @ ensures (\result.getStrStatus == "Silver" || \result.getStrStatus == "Gold" || \result.getStrStatus == "Platinum");
	 */
	public Status getStatus() {
		return this.status;
	}
	
	/*
	 * @ ensures (\result == "Silver" || \result == "Gold" || \result == "Platinum");
	 */
	public String getStrStatus() {
		return this.status.getStrStatus();
	}
	
	/*
	 * @ ensures (getStrStatus() == "Silver") ==> (\result == 5000.0);
	 * @ ensures (getStrStatus() == "Gold") ==> (\result == 50000.0); 
	 * @ ensures (getStrStatus() == "Platinum") ==> (\result == 500000.0);
	 */ 
	public double getLimRetiradaDiaria() {
		return this.status.getLimRetiradaDiaria();
	}
	
	/*
	 * @ requires valor >= 0;
	 * @ ensures (\old(this.getStrStatus()) == "Silver") ==> saldo == \old(saldo) + valor;
	 * @ ensures (\old(this.getStrStatus()) == "Gold") ==> JMLDouble.approximatelyEqualTo(saldo, (double)(\old(saldo) + (valor * 1.01)), (double)0.001);
	 * @ ensures (\old(this.getStrStatus()) == "Platinum") ==> JMLDouble.approximatelyEqualTo(saldo, (double)(\old(saldo) + (valor * 1.025)), (double)0.001);
	 * @ ensures (\old(this.getStrStatus()) == "Silver) && (this.saldo > 50000) ==> this.getStrStatus() == "Gold";
	 * @ ensures (\old(this.getStrStatus()) == "Gold) && (this.saldo > 200000) ==> this.getStrStatus() == "Platinum";
	 */
	public void deposito(double valor) 
	{
		saldo += valor * this.status.valorizaDeposito();
		if(this.status instanceof Silver)
		{
			if(saldo >= LIM_SILVER_GOLD)
			{
				this.status = new Gold();
			}
		}
		else if(this.status instanceof Gold)
		{
			if(saldo >= LIM_GOLD_PLATINUM)
			{
				this.status = new Platinum();
			}
		}
	}
	
	/*
	 * @ requires valor >= 0;
	 * @ requires valor <= getLimRetiradaDiaria();
	 * @ ensures JMLDouble.approximatelyEqualTo(saldo, (double)(\old(saldo) - valor), (double)0.001);
	 * @ ensures (\old(this.getStrStatus()) == "Gold) && (this.saldo < 25000) ==> this.getStrStatus() == "Silver";
	 * @ ensures (\old(this.getStrStatus()) == "Platinum) && (this.saldo > 100000) ==> this.getStrStatus() == "Gold";
	 */
	public void retirada(double valor) {
		if (saldo - valor < 0.0) {
			return;
		} else {
			saldo = saldo - valor;
			if (status instanceof Platinum) {
				if (saldo < LIM_PLATINUM_GOLD) {
					status = new Gold();
				}
			} else if (status instanceof Gold) {
				if (saldo < LIM_GOLD_SILVER) {
					status = new Silver();
				}
			}
		}
	}

	/*
	 * @ ensures \result.length() > 0 && \result != null;
	 */
	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", correntista=" + correntista + ", saldo=" + saldo + ", status=" + this.status.getStrStatus()
				+ "]";
	}
}
