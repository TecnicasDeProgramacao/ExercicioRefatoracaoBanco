package com.bcopstein.ExercicioRefatoracaoBanco;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContasTests {

	//100;Luis Antonio Feliz;2600.000000;0
	//130;Aloisio Dexter;50.000000;0
	Map<Integer, Conta> contasM;
	private Conta mockConta = mock(Conta.class);
	private Conta mockConta2 = mock(Conta.class);


	
	@BeforeEach
	public void setUp() throws Exception {
		contasM = new HashMap<Integer, Conta>();		
		//when(mockPersistencia.loadContas()()).thenReturn(list);
		
		when(mockConta.getNumero()).thenReturn(100);
		when(mockConta.getCorrentista()).thenReturn("Luis Antonio Feliz");
		when(mockConta.getSaldo()).thenReturn(2600.00);
		when(mockConta2.getNumero()).thenReturn(130);
		when(mockConta2.getCorrentista()).thenReturn("Aloisio Dexter");
		when(mockConta2.getSaldo()).thenReturn(500.00);
		
		contasM.put(100, mockConta);
		contasM.put(130, mockConta2);

		Field f  = Contas.getInstance().getClass().getDeclaredField("contas");
		f.setAccessible(true);
		f.set(Contas.getInstance(), contasM);
	}
	
	
	@Test
	void testGetConta()  {
		assertEquals(mockConta, Contas.getInstance().getConta(100));
	}
	
	@Test
	void testSetGetCorr() {
		Contas.getInstance().setCorrente(130);
		
		assertEquals(mockConta2, Contas.getInstance().getCorrente());
	}
	
	@Test
	public void testsetCorrExeption() 
	{				
		assertThrows(NumberFormatException.class,() -> Contas.getInstance().setCorrente(3));
	}

}
