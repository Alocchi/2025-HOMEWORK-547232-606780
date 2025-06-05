package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoSaluta;

class CaneTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception{
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.addPersonaggio("salotto", "cane")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
	}
	
	@Test
	void testSaluta() {
		this.comando = new ComandoSaluta();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Ciao, io sono Bernardo il canetto. BAU!");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getLast(), "Ciao, io sono Bernardo il canetto. Ci siamo gia' presentati!");
	}
	
	@Test
	void testAgisci() {
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "AHIA! Il cane ti ha morso !\nHai perso 1 CFU ...");
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testRiceviRegalo() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("osso", 1));
		this.comando = new ComandoRegala();
		this.comando.setParametro("osso");
		this.comando.esegui(partita, io);
		assertEquals("Il cane inzia a fare le feste! E' così felice che lascia un regalo a sua volta!", this.io.getOutput().getFirst());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("pallina"));
	}
	
	@Test
	void testRiceviRegaloSbagliato() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("attrezzo", 1));
		this.comando = new ComandoRegala();
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals("AHIA! Il cane ti ha morso !\nHai perso 1 CFU ...", this.io.getOutput().getFirst());
		assertEquals(19, this.partita.getGiocatore().getCfu());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
}
