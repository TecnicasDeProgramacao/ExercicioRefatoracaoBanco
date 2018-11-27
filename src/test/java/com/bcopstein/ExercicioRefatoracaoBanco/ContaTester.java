package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContaTester {

	private Conta conta;
	private Status mockStatus = mock(Status.class); 
	
	@BeforeEach
	public void setUp() throws Exception {
		when(mockStatus.getStrStatus()).thenReturn("Silver");
	}
	
	@Test
	public void testGetNumeroConta() {		
		conta = new Conta(1, "umNome");
		//double result = conta.getSaldo();
		assertEquals(1, conta.getNumero());
	}

	@Test
	public void testGetCorrentista(){
		conta = new Conta(1, "umNome");
		assertEquals("umNome", conta.getCorrentista());
	}
	
	@Test
	public void testGetStatus()
	{
		conta = new Conta(1, "umNome");
		assertEquals("Silver", conta.getStrStatus());
	}
}
