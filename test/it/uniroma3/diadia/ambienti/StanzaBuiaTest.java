package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception{
		this.stanza = new StanzaBuia("n11", "lanterna");
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.stanza.addAttrezzo(this.attrezzo);
	}
	
	@Test
	void testHasAttrezzo() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(this.stanza.hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testGetAttrezzo(){
		assertEquals(attrezzo, this.stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoVuota() {
		this.stanza.removeAttrezzo(attrezzo);
		assertFalse(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testBuio() {
		assertEquals("qui c'è buio pesto", stanza.getDescrizione());
	}
	
	@Test
	void testIlluminato() {
		this.stanza.addAttrezzo(new Attrezzo("lanterna", 1));
		assertNotEquals("qui c'è buio pesto", stanza.getDescrizione());
	}

}
