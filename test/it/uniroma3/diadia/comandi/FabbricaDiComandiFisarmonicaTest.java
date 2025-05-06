package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandi fabbrica;
	private Comando comando;
	
	@BeforeEach
	void setUp() throws Exception{
		this.fabbrica = new FabbricaDiComandiFisarmonica();	
	}

	@Test
	void testComandoVai() {
		this.comando = fabbrica.costruisciComando("vai sud");
		assertEquals(this.comando.getNome(), "vai");
		assertEquals(this.comando.getParametro(), "sud");
	}
	
	@Test
	void testComandoPrendi() {
		this.comando = fabbrica.costruisciComando("prendi spada");
		assertEquals(this.comando.getNome(), "prendi");
		assertEquals(this.comando.getParametro(), "spada");
	}
	
	@Test
	void testComandoPosa() {
		this.comando = fabbrica.costruisciComando("posa spada");
		assertEquals(this.comando.getNome(), "posa");
		assertEquals(this.comando.getParametro(), "spada");
	}
	
	@Test
	void testComandoAiuto() {
		this.comando = fabbrica.costruisciComando("aiuto");
		assertEquals(this.comando.getNome(), "aiuto");
		assertNull(this.comando.getParametro());
	}
	
	@Test
	void testComandoFine() {
		this.comando = fabbrica.costruisciComando("fine");
		assertEquals(this.comando.getNome(), "fine");
		assertNull(this.comando.getParametro());
	}
	
	@Test
	void testComandoGuarda() {
		this.comando = fabbrica.costruisciComando("guarda");
		assertEquals(this.comando.getNome(), "guarda");
		assertNull(this.comando.getParametro());
	}
	
	@Test
	void testComandoNonValido() {
		this.comando = fabbrica.costruisciComando("aaaa");
		assertEquals(this.comando.getNome(), "non valido");
		assertNull(this.comando.getParametro());
	}

}
