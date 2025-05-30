package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class PrendiTest {
	
	private Comando comando;
	private Partita partita;
	private IO io;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoPrendi();
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOConsole();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testPrendiAttrezzo() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getGiocatore().getBorsa().getAttrezzo("attrezzo"), attrezzo);
	}
	
	@Test
	void testPrendiAttrezzoInesistente() {
		this.comando.setParametro("AttrezzoTest");
		this.comando.esegui(partita, io);
	}
	
	@Test
	void testPrendiAttrezzoNullo() {
		this.comando.esegui(partita, io);
	}

}
