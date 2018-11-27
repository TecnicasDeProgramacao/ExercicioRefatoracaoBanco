package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SilverTests 
{
	private Status silver;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		this.silver = new Silver();
	}

	@Test
	void testGetStatus() 
	{
		assertEquals(true, this.silver.getStatus() instanceof Silver);
	}
	
	@Test
	void testGetStrStatus() 
	{
		assertEquals("Silver", this.silver.getStrStatus());
	}
	
	@Test
	void testValorizaDeposito() 
	{
		assertEquals(1.0, this.silver.valorizaDeposito());
	}
	
	@Test
	void testGetLimRetradaDiaria() 
	{
		assertEquals(5000.0, this.silver.getLimRetiradaDiaria());
	}

}
