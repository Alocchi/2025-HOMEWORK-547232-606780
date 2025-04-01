package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception{
		this.partita = new Partita();
		
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
