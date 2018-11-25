package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.GregorianCalendar;
import java.util.List;

public class LogicaOperacoes {

	private static LogicaOperacoes log;
	private Operacoes operacoes;
	private Contas contas;
	
	public LogicaOperacoes(){}
	
	public static LogicaOperacoes instance()
	{
		if(log == null) return new LogicaOperacoes();
		return log;
	}
	
	public void definirContaUso(int curr){
				
		Contas.getInstance().setCorrente(curr);		
	}
	
	public Operacao operacaoCredito(double valor){
		
		return Operacoes.getInstance().operacaoCredito(valor, Contas.getInstance().getCorrente());
	}
	
	public Operacao operacaoDebito(double valor){
		return Operacoes.getInstance().operacaoDebito(valor, Contas.getInstance().getCorrente());
	}
	
	public List<Operacao> solicitaExtrato(){
		return Operacoes.getInstance().getExtrato(Contas.getInstance().getCorrente().getNumero());	
	}
	
	public double solicitaSaldo(){
		return Contas.getInstance().getCorrente().getSaldo();
	}
	
	public double solicitaSaldoMedio(int mes, int ano){
		return Operacoes.getInstance().getSaldoMedioMes(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public int totalCreditos(int mes, int ano){
		return Operacoes.getInstance().totalCreditos(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public int totalDebitos(int mes, int ano){
		return Operacoes.getInstance().totalDebitos(Contas.getInstance().getCorrente().getNumero(), mes, ano);
	}
	
	public int getNumeroConta()
	{
		return Contas.getInstance().getCorrente().getNumero();
	}
	
	public String getNomeCorrent()
	{
		return Contas.getInstance().getCorrente().getCorrentista();
	}
	
	public String getStatusConta()
	{
		return Contas.getInstance().getCorrente().getStrStatus();
	}
	
	public double getLimRet()
	{
		return Contas.getInstance().getCorrente().getLimRetiradaDiaria();
	}
	
	public void loadContas()
	{
		Contas.getInstance().loadContas();
	}
	
	public void loadOperacoes()
	{
		Operacoes.getInstance().loadOperacoes();
	}
	
	public void saveContas()
	{
		Contas.getInstance().save();
	}
	
	public void saveOperacoes()
	{
		Operacoes.getInstance().saveOperacoes();
	}
}
