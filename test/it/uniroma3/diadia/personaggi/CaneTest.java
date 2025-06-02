package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoSaluta;

class CaneTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception{
		Labirinto monolocale = new LabirintoBuilder()
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
	
	void testAgisci() {
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "AHIA! Il cane ti ha morso !\\nHai perso 1 CFU ...");
		assertEquals(19, this.partita.getGiocatore().getCfu());
	}
	
}
