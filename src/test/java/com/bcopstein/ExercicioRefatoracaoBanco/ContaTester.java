package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContaTester {

	private Conta conta;
	
	@Test
	public void testGetNumeroConta() {		
		conta = new Conta(1, "umNome");
		//double result = conta.getSaldo();
		assertEquals(1, conta.getNumero());
	}

}
