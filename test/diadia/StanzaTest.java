package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.stanza = new Stanza("n11");
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.stanza.addAttrezzo(this.attrezzo);
	}
		
	@Test
	void testHasAttrezzo() {
		assertFalse(this.stanza.hasAttrezzo("spada"));
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(this.stanza.hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testGetAttrezzo(){
		assertEquals(attrezzo, this.stanza.getAttrezzo("attrezzo"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoVuota() {
		this.stanza.removeAttrezzo(attrezzo);
		assertFalse(this.stanza.removeAttrezzo(attrezzo));
	}
	
}
