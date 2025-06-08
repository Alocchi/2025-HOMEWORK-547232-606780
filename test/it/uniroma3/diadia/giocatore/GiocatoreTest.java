package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() throws Exception{
		this.giocatore = new Giocatore();
	}
	
	@Test
	void testCfu() {
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	void testBorsa() {
		assertEquals(10, this.giocatore.getBorsa().getPesoMax());
	}
	
	@Test
	void testBorsaPesoMaxVariabile() {
		this.giocatore = new Giocatore(100);
		assertEquals(100, this.giocatore.getBorsa().getPesoMax());
	}

}
