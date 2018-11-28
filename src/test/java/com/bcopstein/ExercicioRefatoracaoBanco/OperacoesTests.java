package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.List;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class OperacoesTests 
{
	private List<Operacao> operacoesM;
	private Operacao mockOp1 = mock(Operacao.class);
	private Operacao mockOp2 = mock(Operacao.class);
	private Operacao mockOp3 = mock(Operacao.class);
	private Operacao mockOp4 = mock(Operacao.class);

	
	//30;1;2018;15;52;33;300;0;1000.000000;0
	//
	@BeforeEach
	public void setUp() throws Exception {
		operacoesM = new LinkedList<Operacao>();		
		
		
		when(mockOp1.getNumeroConta()).thenReturn(100);		
		when(mockOp1.getAno()).thenReturn(2018);
		when(mockOp1.getMes()).thenReturn(3);
		when(mockOp1.getDia()).thenReturn(5);
		when(mockOp1.getHora()).thenReturn(11);
		when(mockOp1.getMinuto()).thenReturn(42);
		when(mockOp1.getSegundo()).thenReturn(37);
		when(mockOp1.getTipoOperacao()).thenReturn(0);
		when(mockOp1.getValorOperacao()).thenReturn(140.0);
		//when(mockConta.getCorrentista()).thenReturn("Luis Antonio Feliz");
		//when(mockConta.getSaldo()).thenReturn(2600.00);
		when(mockOp2.getNumeroConta()).thenReturn(100);
		when(mockOp2.getAno()).thenReturn(2018);
		when(mockOp2.getMes()).thenReturn(3);
		when(mockOp2.getDia()).thenReturn(12);
		when(mockOp2.getHora()).thenReturn(3);
		when(mockOp2.getMinuto()).thenReturn(14);
		when(mockOp2.getSegundo()).thenReturn(22);
		when(mockOp2.getTipoOperacao()).thenReturn(0);
		when(mockOp2.getValorOperacao()).thenReturn(60.0);
		//when(mockConta2.getCorrentista()).thenReturn("Aloisio Dexter");
		//when(mockConta2.getSaldo()).thenReturn(500.00);
		when(mockOp3.getNumeroConta()).thenReturn(130);
		when(mockOp3.getAno()).thenReturn(2018);
		when(mockOp3.getMes()).thenReturn(10);
		when(mockOp3.getDia()).thenReturn(15);
		when(mockOp3.getHora()).thenReturn(2);
		when(mockOp3.getMinuto()).thenReturn(24);
		when(mockOp3.getSegundo()).thenReturn(41);
		when(mockOp3.getTipoOperacao()).thenReturn(1);

		
		when(mockOp4.getNumeroConta()).thenReturn(100);
		when(mockOp4.getAno()).thenReturn(2018);
		when(mockOp4.getMes()).thenReturn(3);
		when(mockOp4.getDia()).thenReturn(23);
		when(mockOp4.getHora()).thenReturn(1);
		when(mockOp4.getMinuto()).thenReturn(34);
		when(mockOp4.getSegundo()).thenReturn(51);
		when(mockOp4.getTipoOperacao()).thenReturn(1);
		when(mockOp4.getValorOperacao()).thenReturn(50.0);
		
		operacoesM.add(mockOp1);
		operacoesM.add(mockOp2);
		operacoesM.add(mockOp4);
		operacoesM.add(mockOp3);

		Field f  = Operacoes.getInstance().getClass().getDeclaredField("operacoes");
		f.setAccessible(true);
		f.set(Operacoes.getInstance(), operacoesM);
	}
	
	@Test
	void testGetExtrato() 
	{
		List<Operacao> lst = new LinkedList<Operacao>();
		lst.add(mockOp1);
		lst.add(mockOp2);
		lst.add(mockOp4);
		assertEquals(lst, Operacoes.getInstance().getExtrato(100));
	}
	
	
	@Test
	void testOperacaoCredito() 
	{
		Conta mockConta = mock(Conta.class);
		Silver mockStatus = mock(Silver.class);
		when(mockConta.getNumero()).thenReturn(100);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		when(mockConta.getCorrentista()).thenReturn("José Bezerra");
		when(mockConta.getSaldo()).thenReturn(0.0);
		GregorianCalendar date = new GregorianCalendar();
		int dia = date.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = date.get(GregorianCalendar.MONTH)+1;
		int ano = date.get(GregorianCalendar.YEAR);
		int hora = date.get(GregorianCalendar.HOUR);
		int minuto = date.get(GregorianCalendar.MINUTE);
		int segundo = date.get(GregorianCalendar.SECOND);
		Operacao op1 = CreditoFactory.instance().factoryMethod(dia, mes, ano, hora, minuto, segundo, mockConta.getNumero(), mockConta.getStatus(), 20.0);
		Operacao op2 = Operacoes.getInstance().operacaoCredito(20.0, mockConta);
		assertTrue(operacoesM.contains(op2));
		assertTrue(op1.equals(op2));		
	}
	
	@Test
	void testOperacaoDebito()
	{
		Conta mockConta = mock(Conta.class);
		Silver mockStatus = mock(Silver.class);
		when(mockConta.getLimRetiradaDiaria()).thenReturn(5000.0);
		when(mockConta.getNumero()).thenReturn(5654);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		when(mockConta.getCorrentista()).thenReturn("José Bezerra");
		when(mockConta.getSaldo()).thenReturn(50.0);
		GregorianCalendar date = new GregorianCalendar();
		int dia = date.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = date.get(GregorianCalendar.MONTH)+1;
		int ano = date.get(GregorianCalendar.YEAR);
		int hora = date.get(GregorianCalendar.HOUR);
		int minuto = date.get(GregorianCalendar.MINUTE);
		int segundo = date.get(GregorianCalendar.SECOND);
		Operacao op1 = DebitoFactory.instance().factoryMethod(dia, mes, ano, hora, minuto, segundo, mockConta.getNumero(), mockConta.getStatus(), 20);
		Operacao op2 = Operacoes.getInstance().operacaoDebito(20.0, mockConta);
		assertTrue(operacoesM.contains(op2));
		assertTrue(op1.equals(op2));		
	}
	
	@Test
	void testOperacaoDebitoAcimaLimDiario1()
	{
		Conta mockConta = mock(Conta.class);
		Silver mockStatus = mock(Silver.class);
		when(mockConta.getLimRetiradaDiaria()).thenReturn(5000.0);
		when(mockConta.getNumero()).thenReturn(5654);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		when(mockConta.getCorrentista()).thenReturn("José Bezerra");
		when(mockConta.getSaldo()).thenReturn(6000.0);
		Operacoes.getInstance().operacaoDebito(4999.0, mockConta);
		assertThrows(IllegalArgumentException.class,() -> Operacoes.getInstance().operacaoDebito(2.0, mockConta));
	}
	
	@Test
	void testOperacaoDebitoAcimaLimDiario2()
	{
		Conta mockConta = mock(Conta.class);
		Silver mockStatus = mock(Silver.class);
		when(mockConta.getLimRetiradaDiaria()).thenReturn(5000.0);
		when(mockConta.getNumero()).thenReturn(5654);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		when(mockConta.getCorrentista()).thenReturn("José Bezerra");
		when(mockConta.getSaldo()).thenReturn(6000.0);
		assertThrows(IllegalArgumentException.class,() -> Operacoes.getInstance().operacaoDebito(5001.0, mockConta));
	}
	
	@Test
	void testOperacaoDebitoSaldoInsuficiente()
	{
		Conta mockConta = mock(Conta.class);
		Silver mockStatus = mock(Silver.class);
		when(mockConta.getLimRetiradaDiaria()).thenReturn(5000.0);
		when(mockConta.getNumero()).thenReturn(5654);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		when(mockConta.getCorrentista()).thenReturn("José Bezerra");
		when(mockConta.getSaldo()).thenReturn(150.0);
		assertThrows(NumberFormatException.class,() -> Operacoes.getInstance().operacaoDebito(1600.0, mockConta));
	}
	
	@Test
	void testTotalCreditos() 
	{
		assertEquals(2, Operacoes.getInstance().totalCreditos(100, 3, 2018));
	}
	
	@Test
	void testTotalDebitos() 
	{
		assertEquals(1, Operacoes.getInstance().totalDebitos(100, 3, 2018));
	}
	
	@Test
	void testGetSaldoMedioMes()
	{
		assertEquals(146, Operacoes.getInstance().getSaldoMedioMes(100, 3, 2018));
	}

}
