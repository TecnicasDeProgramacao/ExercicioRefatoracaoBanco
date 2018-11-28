package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogicaOperacoesTests {

	@BeforeEach
	public void setUp() throws Exception 
	{
		Contas.getInstance().loadContas();
		Contas.getInstance().setCorrente(130);
	}
	
	@Test
	void testDefinirContaUso() {
		LogicaOperacoes.instance().definirContaUso(130);
		assertEquals(130, Contas.getInstance().getCorrente().getNumero());
	}
	
	@Test
	void testGetLimRetirada() 
	{
		assertEquals(Contas.getInstance().getCorrente().getLimRetiradaDiaria(), LogicaOperacoes.instance().getLimRet());
	}
	
	@Test
	void testGetStatusConta() 
	{
		assertEquals(Contas.getInstance().getCorrente().getStrStatus(), LogicaOperacoes.instance().getStatusConta());
	}
	
	@Test
	void testGetNomeCorrent() 
	{	
		assertEquals("Aloisio Dexter", LogicaOperacoes.instance().getNomeCorrent());
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
