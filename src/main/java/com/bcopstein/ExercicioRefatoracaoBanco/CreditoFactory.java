package com.bcopstein.ExercicioRefatoracaoBanco;

public class CreditoFactory implements OperacaoFactory
{
	private static OperacaoFactory cf = null;;
	
	private CreditoFactory() {}
	
	public static OperacaoFactory instance()
	{
		if(cf == null) return new CreditoFactory();
		return cf;
	}
	
	public Operacao factoryMethod(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta, Status statusConta,
			double valorOperacao)
	{
		return new Operacao(dia, mes, ano, hora, minuto, segundo, numeroConta, statusConta,
				valorOperacao, 1);
	}
}
