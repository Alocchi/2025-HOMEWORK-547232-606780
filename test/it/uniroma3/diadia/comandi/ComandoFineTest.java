package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoFineTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoFine();
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testFine() {
		this.comando.esegui(partita, io);
		assertEquals("Grazie di aver giocato!", this.io.getOutput().getFirst());
	}
}
