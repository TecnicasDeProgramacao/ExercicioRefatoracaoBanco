package com.bcopstein.ExercicioRefatoracaoBanco;
public class Conta {
	public final int SILVER = 0;
	public final int GOLD = 1;
	public final int PLATINUM = 2;
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
	
	public double getSaldo() {
		return saldo;
	}

	public Integer getNumero() {
		return numero;
	}
	
	public String getCorrentista() {
		return correntista;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public String getStrStatus() {
		return this.status.getStrStatus();
	}
	
	public double getLimRetiradaDiaria() {
		return this.status.getLimRetiradaDiaria();
	}
	
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

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", correntista=" + correntista + ", saldo=" + saldo + ", status=" + this.status.getStrStatus()
				+ "]";
	}
}
