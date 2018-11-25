package com.bcopstein.ExercicioRefatoracaoBanco;

public interface OperacaoFactory 
{
	public Operacao factoryMethod(int dia, int mes, int ano, int hora, int minuto, int segundo, int numeroConta, Status statusConta,
			double valorOperacao);
}
