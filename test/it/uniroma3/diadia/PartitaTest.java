package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class PartitaTest {
	
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception{
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) 
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		this.partita = new Partita(bilocale);
	}
	
	@Test
	void testNuovaPartita() {
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testPartitaVinta() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testFinePerCfuFiniti(){
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
}
