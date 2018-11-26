package com.bcopstein.ExercicioRefatoracaoBanco;

public class DebitoFactory implements OperacaoFactory
{
	private static OperacaoFactory df = null;;
	
	private DebitoFactory() {}
	
	public static OperacaoFactory instance()
	{
		if(df == null) df = new DebitoFactory();
		return df;
	}
	
	public Operacao factoryMethod(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta, Status statusConta,
			double valorOperacao)
	{
		return new Operacao(dia, mes, ano, hora, minuto, segundo, numeroConta, statusConta,
				valorOperacao, 1);
	}
}
