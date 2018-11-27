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
		assertEquals(true, conta.getStatus() instanceof Silver);
	}
	
	@Test
	public void testGetLimRetiradaDiaria()
	{
		Conta conta1 = new Conta(1,"Joao",1200.0,new Silver());
		Conta conta2 = new Conta(1,"Joao",52000.0,new Gold());
		Conta conta3 = new Conta(1,"Joao",350000.0,new Platinum());
		
		assertEquals(5000, conta1.getLimRetiradaDiaria());
		assertEquals(50000, conta2.getLimRetiradaDiaria());
		assertEquals(500000, conta3.getLimRetiradaDiaria());
	}
	
	@ParameterizedTest
	@CsvSource({"48800.0,Gold,50000.0","48799.0,Silver,49999.0","400000.0,Gold,401200.0"})
	public void testDepositoSilver(double valor, String newStatus, double newSaldo)
	{
		Conta conta1 = new Conta(1,"Joao",1200.0,new Silver());		
		
		assertEquals(conta1.getStrStatus(), "Silver");
		conta1.deposito(valor);
		assertEquals(conta1.getSaldo(),newSaldo);
		assertEquals(conta1.getStrStatus(),newStatus);
	}
	
	@ParameterizedTest
	@CsvSource({"146533.0,Gold,199998.33","146535.0,Platinum,200000.35"})
	public void testDepositoGold(double valor, String newStatus, double newSaldo)
	{
		Conta conta2 = new Conta(1,"Joao",52000.0,new Gold());
		
		assertEquals(conta2.getStrStatus(), "Gold");
		conta2.deposito(valor);
		assertEquals(conta2.getSaldo(),newSaldo);
		assertEquals(conta2.getStrStatus(),newStatus);		
	}
	
	@ParameterizedTest
	@CsvSource({"200000.0,Platinum,555000","100.0,Platinum,350102.5"})
	public void testDepositoPlatinum(double valor, String newStatus, double newSaldo)
	{
		Conta conta3 = new Conta(1,"Joao",350000.0,new Platinum());
		
		assertEquals(conta3.getStrStatus(), "Platinum");
		conta3.deposito(valor);
		assertEquals(conta3.getSaldo(),newSaldo);
		assertEquals(conta3.getStrStatus(),newStatus);
	}
	
	@ParameterizedTest
	@CsvSource({"3500.0,Silver,36500.0","0.0,Silver,40000.0","80000.0,Silver,40000.0"})
	public void testRetiradaSilver(double valor, String newStatus, double newSaldo)
	{
		Conta conta1 = new Conta(1,"Joao",40000.0,new Silver());		
		
		assertEquals(conta1.getStrStatus(), "Silver");
		conta1.retirada(valor);
		assertEquals(conta1.getSaldo(),newSaldo);
		assertEquals(conta1.getStrStatus(),newStatus);
	}
	
	@ParameterizedTest
	@CsvSource({"42000.0,Gold,25000.0","42001.0,Silver,24999.0","0.0,Gold,67000.0","80000.0,Gold,67000.0"})
	public void testRetiradaGold(double valor, String newStatus, double newSaldo)
	{
		Conta conta2 = new Conta(1,"Joao",67000.0,new Gold());
		
		assertEquals(conta2.getStrStatus(), "Gold");
		conta2.retirada(valor);
		assertEquals(conta2.getSaldo(),newSaldo);
		assertEquals(conta2.getStrStatus(),newStatus);		
	}
	
	@ParameterizedTest
	@CsvSource({"250000.0,Platinum,100000.0","250001.0,Gold,99999.0","340000.0,Gold,10000.0","0.0,Platinum,350000.0","500000.0,Platinum,350000.0"})
	public void testRetiradaPlatinum(double valor, String newStatus, double newSaldo)
	{
		Conta conta3 = new Conta(1,"Joao",350000.0,new Platinum());
		
		assertEquals(conta3.getStrStatus(), "Platinum");
		conta3.retirada(valor);
		assertEquals(conta3.getSaldo(),newSaldo);
		assertEquals(conta3.getStrStatus(),newStatus);
	}
	
}
