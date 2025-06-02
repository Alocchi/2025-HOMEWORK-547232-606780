package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoPosa();
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	@Test
	void testPosaAttrezzo() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("attrezzo"), attrezzo);
		assertEquals(this.io.getOutput().getFirst(), "hai posato attrezzo");
	}
	
	@Test
	void testPrendiAttrezzoInesistente() {
		this.comando.setParametro("AttrezzoTest");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "l'oggetto AttrezzoTest non è nella borsa");
	}
	
	@Test
	void testPrendiAttrezzoNullo() {
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "non hai specificato l'oggetto");
	}

}
