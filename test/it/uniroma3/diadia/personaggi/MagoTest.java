package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoRegala;
import it.uniroma3.diadia.comandi.ComandoSaluta;

class MagoTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception{
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.addPersonaggio("salotto", "mago")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
	}
	
	@Test
	void testSaluta() {
		this.comando = new ComandoSaluta();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Ciao, io sono Otto il mago. Piacere di conoescerti!");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getLast(), "Ciao, io sono Otto il mago. Ci siamo gia' presentati!");
	}
	
	@Test
	void testAgisci() {
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!");
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("mantello"));
	}
	
	@Test
	void testAgisciSecondaVolta() {
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getLast(), "Mi spiace, ma non ho piu' nulla...");
	}
	
	@Test
	void testRiceviRegalo() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("attrezzo", 4));
		this.comando = new ComandoRegala();
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals("Questo oggetto sembra molto pesante, lascia che lo alleggerisca con la mia magia", this.io.getOutput().getFirst());
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
		assertEquals(2, this.partita.getStanzaCorrente().getAttrezzo("attrezzo").getPeso());
	}
	
}
