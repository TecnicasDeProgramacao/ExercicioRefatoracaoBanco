package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.List;
import java.util.Collection;
import java.util.LinkedList;

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
	
	@BeforeEach
	public void setUp() throws Exception {
		operacoesM = new LinkedList<Operacao>();		
		//when(mockPersistencia.loadContas()()).thenReturn(list);
		
		//when(mockOp1.getNumero()).thenReturn(100);
		//when(mockConta.getCorrentista()).thenReturn("Luis Antonio Feliz");
		//when(mockConta.getSaldo()).thenReturn(2600.00);
		//when(mockConta2.getNumero()).thenReturn(130);
		//when(mockConta2.getCorrentista()).thenReturn("Aloisio Dexter");
		//when(mockConta2.getSaldo()).thenReturn(500.00);
		
		//contasM.put(100, mockConta);
		//contasM.put(130, mockConta2);

		//Field f  = Contas.getInstance().getClass().getDeclaredField("contas");
		//f.setAccessible(true);
		//f.set(Contas.getInstance(), contasM);
	}
	
	//@Test
	//void test() 
	//{
		//fail("Not yet implemented");
	//}

}
