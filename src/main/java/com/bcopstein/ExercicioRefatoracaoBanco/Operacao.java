package com.bcopstein.ExercicioRefatoracaoBanco;
public class Operacao {
	public final int CREDITO = 0;
	public final int DEBITO = 1;
    
	private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;
    private int segundo;
    private int numeroConta;
    private Status statusConta;
    private double valorOperacao;
    private int tipoOperacao;
    
	public Operacao(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta, Status statusConta,
			double valorOperacao, int tipoOperacao) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
		this.numeroConta = numeroConta;
		this.statusConta = statusConta;
		this.valorOperacao = valorOperacao;
		this.tipoOperacao = tipoOperacao;
	}

	public int getDia() {
		return dia;
	}

	public int getMes() {
		return mes;
	}

	public int getAno() {
		return ano;
	}

	public int getHora() {
		return hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public Status getStatusConta() {
		return statusConta;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public int getTipoOperacao() {
		return tipoOperacao;
	}
	
	public boolean equals(Operacao op)
	{
		if(this.getAno() == op.getAno() && this.getDia() == op.getDia() && this.getMes() == op.getMes()
			&& this.getHora() == op.getHora() && this.getMinuto() == op.getMinuto() && this.getSegundo() == op.getSegundo()
			&& this.getNumeroConta() == op.getNumeroConta() && this.getStatusConta() == op.getStatusConta() &&
			this.getTipoOperacao() == op.getTipoOperacao() && this.getValorOperacao() == op.getValorOperacao())
		{
			return true;
		}
		return false;
	}
	    
	@Override
	public String toString() {
		String tipo = "<C>";
		if (tipoOperacao == 1) {
			tipo = "<D>"; 
		}
		String line = dia+"/"+mes+"/"+ano+" "+
	                  hora+":"+minuto+":"+segundo+" "+
				      numeroConta+" "+
	                  statusConta.getStrStatus() +" "+
				      tipo+" "+
	                  valorOperacao;
		return(line);
	}
}
