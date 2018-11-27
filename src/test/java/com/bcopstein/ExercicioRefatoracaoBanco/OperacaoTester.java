package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OperacaoTester {

	
private Status mockStatus = mock(Status.class); 
	
	Operacao op;
	@BeforeEach
	public void setUp() throws Exception {
		when(mockStatus.getStrStatus()).thenReturn("Silver");
		when(mockStatus.getStatus()).thenReturn(mockStatus);
		
		
		op = new Operacao(1,1,2000,22,30,12,100, mockStatus,100.0,0);
	}
	
	@Test
	public void testToString() 
	{
		String ver = "1/1/2000 22:30:12 100 Silver <C> 100.0";
		assertEquals(ver, op.toString());
	}
	
	@Test
	public void testGetDia() {
		assertEquals(1,op.getDia());
	}

	@Test
	public void testGetMes() {
		assertEquals(1,op.getMes());
	}

	@Test
	public void testGetAno() {
		assertEquals(2000, op.getAno());
	}
	
	@Test
	public void testGetHora() {
		assertEquals(22,op.getHora());
	}
	
	@Test
	public void testGetMinuto() {
		assertEquals(30, op.getMinuto());
	}

	@Test
	public void testGetSegundo() {
		assertEquals(12, op.getSegundo());
	}

	@Test
	public void testGetNumeroConta() {
		assertEquals(100, op.getNumeroConta());
	}

	@Test
	public void testGetStatusConta() {
		assertEquals(mockStatus, op.getStatusConta());
	}

	@Test
	public void testGetValorOperacao() {
		assertEquals(100.0, op.getValorOperacao());
	}

	@Test
	public void testGetTipoOperacao() {
		assertEquals(0, op.getTipoOperacao());
	}
	
}
