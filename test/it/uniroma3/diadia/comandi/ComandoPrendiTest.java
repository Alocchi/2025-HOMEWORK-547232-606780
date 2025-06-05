package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoPrendi();
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testPrendiAttrezzo() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo"), attrezzo);
		assertEquals(this.io.getOutput().getFirst(), "hai preso attrezzo");
	}
	
	@Test
	void testPrendiAttrezzoOltrePesoMassimo() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("AttrezzoTest", 999));
		this.comando.setParametro("AttrezzoTest");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "l'oggetto non entra in borsa");
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("AttrezzoTest"));
	}
	
	@Test
	void testPrendiAttrezzoInesistente() {
		this.comando.setParametro("AttrezzoTest");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "l'oggetto AttrezzoTest non è in salotto");
	}
	
	@Test
	void testPrendiAttrezzoNullo() {
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "non hai specificato l'oggetto");
	}

}
