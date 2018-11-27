package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContaTests {

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
	public void testGetStrStatus()
	{
		conta = new Conta(1, "umNome");
		assertEquals("Silver", conta.getStrStatus());
	}
	
	@Test
	public void testGetStatus()
	{
		conta = new Conta(1, "umNome");
		assertEquals("Silver", conta.getStatus().getStrStatus());
	}
	
	public void testGetLimRetiradaDiaria()
	{
		Conta conta1 = new Conta(1,"Joao",1200.0,new Silver());
		Conta conta2 = new Conta(1,"Joao",52000.0,new Gold());
		Conta conta3 = new Conta(1,"Joao",350000.0,new Platinum());
		
		assertEquals(5000, conta1.getLimRetiradaDiaria());
		assertEquals(50000, conta2.getLimRetiradaDiaria());
		assertEquals(500000, conta3.getLimRetiradaDiaria());
	}
}
