package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.stanza = new Stanza("n11");
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
	void testImpostaStanzaAdiacente() {
		this.stanza.impostaStanzaAdiacente("nord", new Stanza("test"));
		assertEquals("test", this.stanza.getStanzaAdiacente("nord").getNome());
	}
	
	void testImpostaStanzaAdiacenteDirezioneInesistente() {
		this.stanza.impostaStanzaAdiacente("sopra", new Stanza("test"));
		Set<Direzione> stanze = this.stanza.getMapStanzeAdiacenti().keySet();
		Iterator<Direzione> it = stanze.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test
	void testImpostaStanzaAdiacenteDueVolte() {
		this.stanza.impostaStanzaAdiacente("nord", new Stanza("test1"));
		this.stanza.impostaStanzaAdiacente("nord", new Stanza("test2"));
		assertEquals("test2", this.stanza.getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	void testGetAttrezzi() {
		this.stanza.addAttrezzo(new Attrezzo("spada", 5));
		List<Attrezzo> attrezzi = this.stanza.getAttrezzi();
		Iterator<Attrezzo> it = attrezzi.iterator();
		assertTrue(it.hasNext());
		assertEquals("attrezzo", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("spada", it.next().getNome());
		assertFalse(it.hasNext());
	}
	
	@Test
	void testGetNumeroAttrezzi() {
		this.stanza.addAttrezzo(new Attrezzo("spada", 5));
		this.stanza.addAttrezzo(new Attrezzo("scudo", 5));
		this.stanza.addAttrezzo(new Attrezzo("elmo", 5));
		assertEquals(4, this.stanza.getNumeroAttrezzi());
	}
	
	@Test
	void testGetDirezioni() { 
		this.stanza.impostaStanzaAdiacente("nord", new Stanza("n12"));
		List<String> direzioni = this.stanza.getDirezioni();
		List<String> attesa = new ArrayList<String>();
		attesa.add("nord");
		assertEquals(attesa, direzioni);
	}
	
	@Test
	void testCompareTo() {
		Stanza stanza = new Stanza("test");
		stanza.addAttrezzo(new Attrezzo("spada", 5));
		stanza.addAttrezzo(new Attrezzo("scudo", 5));
		assertTrue(stanza.compareTo(this.stanza) > 0);
	}
}
