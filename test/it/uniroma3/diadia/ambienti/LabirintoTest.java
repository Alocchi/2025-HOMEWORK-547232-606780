package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {
	
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() throws Exception{
		this.labirinto = new Labirinto();
	}

	@Test
	void testEntrata() {
		assertEquals("Atrio", this.labirinto.getEntrata().getNome());
	}

	@Test
	void testUscita() {
		assertEquals("Biblioteca", this.labirinto.getUscita().getNome());
	}
	
}