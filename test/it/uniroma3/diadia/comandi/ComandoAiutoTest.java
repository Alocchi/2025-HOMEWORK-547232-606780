package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoAiutoTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoAiuto();
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testAiuto() {
		this.comando.esegui(partita, io);
		List<String> output = this.io.getOutput();
		Iterator<String> it = output.iterator();
		assertEquals("Aiuto", it.next());
		assertEquals("Fine", it.next());
		assertEquals("Guarda", it.next());
		assertEquals("Interagisci", it.next());
		assertEquals("Posa", it.next());
		assertEquals("Prendi", it.next());
		assertEquals("Regala", it.next());
		assertEquals("Saluta", it.next());
		assertEquals("Vai", it.next());
		assertFalse(it.hasNext());
	}

}
