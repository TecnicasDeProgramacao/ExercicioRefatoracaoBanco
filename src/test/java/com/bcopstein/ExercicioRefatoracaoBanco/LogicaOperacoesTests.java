package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class LogicaOperacoesTests {

	private LinkedList<Operacao> operacoes;
	HashMap<Integer, Conta> contas;
	
	@BeforeEach
	public void setUp() throws Exception 
	{
		Status s1 = new Gold();
		Status s2 = new Platinum();
		Conta c1 = new Conta(1, "Juca Batista", 136680.0, s1);
		Conta c2 = new Conta(2, "Jorge Furtado");
		Conta c3 = new Conta(3, "Ian de Braganca e Lins", 260500.0, s2);
		
		//Operacoes Juca
		Operacao op1 = new Operacao(2,5,2018,2,32,17,1,s1,5000.0,0);
		Operacao op2 = new Operacao(8,5,2018,1,27,42,1,s1,15000.0,0);
		Operacao op3 = new Operacao(15,5,2018,9,21,36,1,s1,10000.0,0);
		Operacao op4 = new Operacao(1,6,2018,5,47,26,1,s1,8000.0,0);
		Operacao op5 = new Operacao(22,6,2018,4,29,41,1,s1,50000.0,0);
		Operacao op6 = new Operacao(4,8,2018,7,44,14,1,s1,15000.0,1);
		Operacao op7 = new Operacao(14,8,2018,1,27,34,1,s1,5000.0,1);
		
		//Operacoes Ian
		Operacao op8 = new Operacao(15,8,2018,4,14,17,3,s2,400000.0,0);
		Operacao op9 = new Operacao(28,8,2018,7,32,14,3,s2,150000.0,1);
		Operacao op10 = new Operacao(12,9,2018,8,7,0,3,s2,20000.0,0);
		Operacao op11 = new Operacao(30,9,2018,3,25,43,3,s2,10000.0,1);
		
		this.contas = new HashMap<Integer, Conta>();
		this.operacoes = new LinkedList<Operacao>();
		
		contas.put(1, c1);
		contas.put(2, c2);
		contas.put(3, c3);
		
		operacoes.add(op1);
		operacoes.add(op2);
		operacoes.add(op3);
		operacoes.add(op4);
		operacoes.add(op5);
		operacoes.add(op6);
		operacoes.add(op7);
		operacoes.add(op8);
		operacoes.add(op9);
		operacoes.add(op10);
		operacoes.add(op11);
		
		Field cts  = Contas.getInstance().getClass().getDeclaredField("contas");
		cts.setAccessible(true);
		cts.set(Contas.getInstance(), contas);
		
		Field ops  = Operacoes.getInstance().getClass().getDeclaredField("operacoes");
		ops.setAccessible(true);
		ops.set(Operacoes.getInstance(), operacoes);
		
		Contas.getInstance().setCorrente(1);
	}
	
	@Test
	void testDefinirContaUso() {
		LogicaOperacoes.instance().definirContaUso(2);
		assertEquals(2, Contas.getInstance().getCorrente().getNumero());
	}
	
	@Test
	void testGetLimRetirada() 
	{
		assertEquals(50000.0, LogicaOperacoes.instance().getLimRet());
	}
	
	@Test
	void testGetStatusConta() 
	{
		assertEquals("Gold", LogicaOperacoes.instance().getStatusConta());
	}
	
	@Test
	void testGetNomeCorrente() 
	{	
		assertEquals("Juca Batista", LogicaOperacoes.instance().getNomeCorrente());
	}
	
	@Test
	void testOperacaoCreditoSilver() 
	{
		LogicaOperacoes.instance().definirContaUso(2);
		Operacao op = LogicaOperacoes.instance().operacaoCredito(400.0);
		assertEquals(400, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoCreditoGold() 
	{
		LogicaOperacoes.instance().definirContaUso(1);
		Operacao op = LogicaOperacoes.instance().operacaoCredito(1000.0);
		assertEquals(137690.0, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoCreditoPlatinum() 
	{
		LogicaOperacoes.instance().definirContaUso(3);
		Operacao op = LogicaOperacoes.instance().operacaoCredito(50000.0);
		assertEquals(311750.0, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoDebitoSilver() 
	{
		LogicaOperacoes.instance().definirContaUso(2);
		LogicaOperacoes.instance().operacaoCredito(1500.0);
		Operacao op = LogicaOperacoes.instance().operacaoDebito(1000.0);
		assertEquals(500.0, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoDebitoSilverSaldoInsuficiente() 
	{
		LogicaOperacoes.instance().definirContaUso(2);
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(5.0));
	}
	
	@Test
	void testOperacaoDebitoSilverLimDiario1() 
	{
		LogicaOperacoes.instance().definirContaUso(2);
		LogicaOperacoes.instance().operacaoCredito(6000.0);	
		LogicaOperacoes.instance().operacaoDebito(5000.0);		
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(1.0));
	}
	
	@Test
	void testOperacaoDebitoSilverLimDiario2() 
	{
		LogicaOperacoes.instance().definirContaUso(2);
		LogicaOperacoes.instance().operacaoCredito(6000.0);		
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(5001.0));
	}
	
	@Test
	void testOperacaoDebitoGold() 
	{
		LogicaOperacoes.instance().definirContaUso(1);
		Operacao op = LogicaOperacoes.instance().operacaoDebito(1000.0);
		assertEquals(135680.0, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoDebitoGoldSaldoInsuficiente() 
	{
		LogicaOperacoes.instance().definirContaUso(1);
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(136690.0));
	}
	
	@Test
	void testOperacaoDebitoGoldLimDiario1() 
	{
		LogicaOperacoes.instance().definirContaUso(1);
		LogicaOperacoes.instance().operacaoDebito(50000.0);		
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(1.0));
	}
	
	@Test
	void testOperacaoDebitoGoldLimDiario2() 
	{
		LogicaOperacoes.instance().definirContaUso(1);	
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(50001.0));
	}
	
	@Test
	void testOperacaoDebitoPlatinum() 
	{
		LogicaOperacoes.instance().definirContaUso(3);
		Operacao op = LogicaOperacoes.instance().operacaoDebito(1000.0);
		assertEquals(259500, Contas.getInstance().getCorrente().getSaldo());
		assertTrue(this.operacoes.contains(op));
	}
	
	@Test
	void testOperacaoDebitoPlatinumSaldoInsuficiente() 
	{
		LogicaOperacoes.instance().definirContaUso(3);
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(261000.0));
	}
	
	@Test
	void testOperacaoDebitoPlatinumLimDiario1() 
	{
		LogicaOperacoes.instance().definirContaUso(3);
		LogicaOperacoes.instance().operacaoCredito(250000.0);	
		LogicaOperacoes.instance().operacaoDebito(500000.0);		
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(1.0));
	}
	
	@Test
	void testOperacaoDebitoPlatinumLimDiario2() 
	{
		LogicaOperacoes.instance().definirContaUso(3);
		LogicaOperacoes.instance().operacaoCredito(250000.0);		
		assertThrows(IllegalArgumentException.class,() -> LogicaOperacoes.instance().operacaoDebito(500001.0));
	}

	@Test
	void testGetSaldo() 
	{	
		assertEquals(136680, LogicaOperacoes.instance().solicitaSaldo());
	}
	
	@Test
	void testSolicitaExtrato() 
	{
		List<Operacao> lst = new LinkedList<Operacao>();
		for(Operacao o: this.operacoes)
		{
			if(o.getNumeroConta() == 1)
			{
				lst.add(o);
			}
		}
		assertEquals(lst, LogicaOperacoes.instance().solicitaExtrato());
	}
	
	@Test 
	void testTotalCreditos()
	{
		assertEquals(3, LogicaOperacoes.instance().totalCreditos(5, 2018));
	}
	
	@Test 
	void testTotalDebitos()
	{
		assertEquals(2, LogicaOperacoes.instance().totalDebitos(8, 2018));
	}
	
	@Test 
	void testSaldoMedio()
	{
		assertEquals(650000.0/30.0, LogicaOperacoes.instance().solicitaSaldoMedio(5, 2018));
	}
}
