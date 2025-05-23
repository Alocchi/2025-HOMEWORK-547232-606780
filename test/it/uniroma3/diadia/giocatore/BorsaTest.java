package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.borsa = new Borsa();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.borsa.addAttrezzo(this.attrezzo);
	}
		
	@Test
	void testHasAttrezzo() {
		assertFalse(this.borsa.hasAttrezzo("spada"));
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testPesoMassimo() {
		Attrezzo spada = new Attrezzo("spada", 100);
		assertFalse(this.borsa.addAttrezzo(spada));
	}
	
	@Test
	void testGetAttrezzo(){
		assertEquals(attrezzo, this.borsa.getAttrezzo("attrezzo"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.borsa.removeAttrezzo("attrezzo"));
		assertFalse(this.borsa.hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		this.borsa.removeAttrezzo("attrezzo");
		assertFalse(this.borsa.removeAttrezzo("attrezzo"));
	}
	
}
