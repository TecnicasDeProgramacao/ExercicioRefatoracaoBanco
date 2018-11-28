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

	@BeforeEach
	public void setUp() throws Exception 
	{
		Status s1 = new Gold();
		Status s2 = new Platinum();
		Conta c1 = new Conta(1, "Juca Batista", 136680.0, s1);
		Conta c2 = new Conta(2, "Jorge Furtado");
		Conta c3 = new Conta(3, "Ian de Braganca e Lins");
		
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
		
		HashMap<Integer, Conta> contas = new HashMap<Integer, Conta>();
		LinkedList<Operacao> operacoes = new LinkedList<Operacao>();
		
		contas.put(1, c1);
		contas.put(2, c2);
		
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
	void testOperacaoCredito() 
	{
		double valorOp = Contas.getInstance().getCorrente().getSaldo();
		valorOp+=45;
		LogicaOperacoes.instance().operacaoCredito(45);
		assertEquals(valorOp, Contas.getInstance().getCorrente().getSaldo());
	}
	
	@Test
	void testOperacaoDebito() 
	{
		double valorOp = Contas.getInstance().getCorrente().getSaldo();
		valorOp-=15;
		LogicaOperacoes.instance().operacaoDebito(15);
		assertEquals(valorOp, Contas.getInstance().getCorrente().getSaldo());
	}

	@Test
	void testGetSaldo() 
	{	
		assertEquals(Contas.getInstance().getCorrente().getSaldo(), LogicaOperacoes.instance().solicitaSaldo());
	}
	
	@Test
	void testSolicitaExtrato() 
	{
		List<Operacao> lst = Operacoes.getInstance().getExtrato(130);
		assertEquals(lst, LogicaOperacoes.instance().solicitaExtrato());
	}
	
	@Test 
	void testTotalCreditos()
	{
		int num = Operacoes.getInstance().totalCreditos(130, 10, 2018);
		assertEquals(num, LogicaOperacoes.instance().totalCreditos(10, 2018));
	}
	
	@Test 
	void testTotalDebitos()
	{
		int num = Operacoes.getInstance().totalDebitos(130, 10, 2018);
		assertEquals(num, LogicaOperacoes.instance().totalDebitos(10, 2018));
	}
	
	@Test 
	void testSaldoMedio()
	{
		double num = Operacoes.getInstance().getSaldoMedioMes(130, 10, 2018);
		assertEquals(num, LogicaOperacoes.instance().solicitaSaldoMedio(10, 2018));
	}
}
