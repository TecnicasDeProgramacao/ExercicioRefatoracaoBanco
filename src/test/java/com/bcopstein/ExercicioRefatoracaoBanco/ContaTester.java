package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaTester {

	private Conta conta;
	
	@Test
	public void testGetNumeroConta() {		
		conta = new Conta(1, "umNome");
		//double result = conta.getSaldo();
		assertEquals(1, conta.getNumero());
	}

}
