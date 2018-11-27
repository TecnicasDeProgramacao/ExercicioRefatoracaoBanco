package com.bcopstein.ExercicioRefatoracaoBanco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PlatinumTests 
{
	private Status Platinum;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		this.Platinum = new Platinum();
	}

	@Test
	void testGetStatus() 
	{
		assertEquals(true, this.Platinum.getStatus() instanceof Platinum);
	}
	
	@Test
	void testGetStrStatus() 
	{
		assertEquals("Platinum", this.Platinum.getStrStatus());
	}
	
	@Test
	void testValorizaDeposito() 
	{
		assertEquals(1.025, this.Platinum.valorizaDeposito());
	}
	
	@Test
	void testGetLimRetradaDiaria() 
	{
		assertEquals(500000.0, this.Platinum.getLimRetiradaDiaria());
	}

}
