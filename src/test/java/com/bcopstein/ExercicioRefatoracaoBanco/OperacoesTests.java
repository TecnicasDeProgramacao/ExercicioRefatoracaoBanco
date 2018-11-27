package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.List;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static org.junit.Assert.*;
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
		when(mockOp1.getTipoOperacao()).thenReturn(0);
		//when(mockConta.getCorrentista()).thenReturn("Luis Antonio Feliz");
		//when(mockConta.getSaldo()).thenReturn(2600.00);
		when(mockOp2.getNumeroConta()).thenReturn(100);
		when(mockOp2.getAno()).thenReturn(2018);
		when(mockOp2.getMes()).thenReturn(3);
		when(mockOp2.getTipoOperacao()).thenReturn(0);
		//when(mockConta2.getCorrentista()).thenReturn("Aloisio Dexter");
		//when(mockConta2.getSaldo()).thenReturn(500.00);
		when(mockOp3.getNumeroConta()).thenReturn(130);
		when(mockOp3.getAno()).thenReturn(2018);
		when(mockOp3.getMes()).thenReturn(10);
		when(mockOp4.getTipoOperacao()).thenReturn(1);

		
		when(mockOp4.getNumeroConta()).thenReturn(100);
		when(mockOp4.getAno()).thenReturn(2018);
		when(mockOp4.getMes()).thenReturn(3);
		when(mockOp4.getTipoOperacao()).thenReturn(1);
		
		operacoesM.add(mockOp1);
		operacoesM.add(mockOp2);
		operacoesM.add(mockOp3);
		operacoesM.add(mockOp4);

		Field f  = Operacoes.getInstance().getClass().getDeclaredField("operacoes");
		f.setAccessible(true);
		f.set(Operacoes.getInstance(), operacoesM);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testOperacaoPorConta() 
	{
		Operacao[] lst = {mockOp1,mockOp2,mockOp4};
		
		assertThat(lst, is(Operacoes.getInstance().getExtrato(100).toArray()));
	}
	
	/*@Test
	void testOperacaoCredito() 
	{		
		Operacao mockAux = mock(Operacao.class);
		Conta mockConta = mock(Conta.class);
		Status mockStatus = mock(Status.class);
		GregorianCalendar date = new GregorianCalendar();
		/*Operacao op = CreditoFactory.instance().factoryMethod(
				date.get(GregorianCalendar.DAY_OF_MONTH),
				date.get(GregorianCalendar.MONTH)+1,
				date.get(GregorianCalendar.YEAR),
				date.get(GregorianCalendar.HOUR),
				date.get(GregorianCalendar.MINUTE),
				date.get(GregorianCalendar.SECOND),
				conta.getNumero(),
				conta.getStatus(),
				valor);//
		when(mockConta.getNumero()).thenReturn(100);
		when(mockConta.getStatus()).thenReturn(mockStatus);
		
		when(mockAux.getDia()).thenReturn(GregorianCalendar.DAY_OF_MONTH);
		when(mockAux.getMes()).thenReturn((GregorianCalendar.MONTH)+1);
		when(mockAux.getAno()).thenReturn(GregorianCalendar.YEAR);
		when(mockAux.getHora()).thenReturn(GregorianCalendar.HOUR);
		when(mockAux.getDia()).thenReturn(GregorianCalendar.DAY_OF_MONTH);
	}*/
	
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

}
