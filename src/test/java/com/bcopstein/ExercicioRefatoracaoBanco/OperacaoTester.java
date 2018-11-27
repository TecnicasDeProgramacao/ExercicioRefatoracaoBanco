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
		
		op = new Operacao(1,1,2000,22,30,12,100, mockStatus,100.0,0);
	}
	
	
	@Test
	public void testGetDia() {
		assertEquals(1,op.getDia());
	}

	public void testGetMes() {
		assertEquals(1,op.getMes());
	}

	public void testGetAno() {
		assertEquals(2000, op.getAno());
	}

	public void testGetHora() {
		assertEquals(22,op.getHora());
	}

	public void testGetMinuto() {
		assertEquals(30, op.getMinuto());
	}

	public void testGetSegundo() {
		assertEquals(12, op.getSegundo());
	}

	/*public int getNumeroConta() {
		return numeroConta;
	}

	public Status getStatusConta() {
		return statusConta;
	}

	public double getValorOperacao() {
		return valorOperacao;
	}

	public int getTipoOperacao() {
		return tipoOperacao;
	}*/

}
