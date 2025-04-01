package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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