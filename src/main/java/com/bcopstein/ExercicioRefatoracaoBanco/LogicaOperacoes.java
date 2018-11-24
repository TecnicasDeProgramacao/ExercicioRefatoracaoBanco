package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.GregorianCalendar;
import java.util.List;

public class LogicaOperacoes {

	private Operacoes operacoes;
	private Contas contas;
	
	public LogicaOperacoes(){		
				
	}
	
	public static void definirContaUso(int curr){
				
		Contas.getInstance().setCorrente(curr);		
	}
	
	public static boolean operacaoCredito(double valor){
		
		return Operacoes.getInstance().operacaoCredito(valor, Contas.getInstance().getCorrente());
	}
	
	public static boolean operacaoDebito(double valor){
		return Operacoes.getInstance().operacaoDebito(valor, Contas.getInstance().getCorrente());
	}
	
	public static List<Operacao> solicitaExtrato(){
		return Operacoes.getInstance().getExtrato(Contas.getInstance().getCorrente().getNumero());	
	}
	
	public static double solicitaSaldo(){
		return Contas.getInstance().getCorrente().getSaldo();
	}
	
	public static double solicitaSaldoMedio(int mes, int ano){
		return Operacoes.getInstance().getSaldoMedioMes(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public static int totalCreditos(int mes, int ano){
		return Operacoes.getInstance().totalCreditos(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public static int totalDebitos(int mes, int ano){
		return Operacoes.getInstance().totalDebitos(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public static int getNumeroConta()
	{
		return Contas.getInstance().getCorrente().getNumero();
	}
	
	public static String getNomeCorrent()
	{
		return Contas.getInstance().getCorrente().getCorrentista();
	}
	
	public static String getStatusConta()
	{
		return Contas.getInstance().getCorrente().getStrStatus();
	}
	
	public static double getLimRet()
	{
		return Contas.getInstance().getCorrente().getLimRetiradaDiaria();
	}
	
}
