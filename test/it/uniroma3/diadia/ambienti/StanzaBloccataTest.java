package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private Stanza stanza;
	private Stanza stanzaBloccata;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.stanzaBloccata = new StanzaBloccata("n11", "nord", "chiave");
		this.stanza = new Stanza("n12");
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.stanza.addAttrezzo(this.attrezzo);
		stanzaBloccata.impostaStanzaAdiacente("nord", stanza);
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
	void testChiuso() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testAperto() {
		this.stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(stanza, stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testDescrizione() {
		assertEquals("n11\nUscite: nord\nAttrezzi nella stanza: \nla direzione nord è bloccata", stanzaBloccata.getDescrizione());
	}
}
