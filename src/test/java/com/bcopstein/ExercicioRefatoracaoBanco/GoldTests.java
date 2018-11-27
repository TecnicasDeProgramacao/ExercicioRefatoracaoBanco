package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GoldTests 
{
	private Status gold;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		this.gold = new Gold();
	}

	@Test
	void testGetStatus() 
	{
		assertEquals(true, this.gold.getStatus() instanceof Gold);
	}
	
	@Test
	void testGetStrStatus() 
	{
		assertEquals("Gold", this.gold.getStrStatus());
	}
	
	@Test
	void testValorizaDeposito() 
	{
		assertEquals(1.01, this.gold.valorizaDeposito());
	}
	
	@Test
	void testGetLimRetradaDiaria() 
	{
		assertEquals(50000.0, this.gold.getLimRetiradaDiaria());
	}

}
