package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class DiaDiaTest {

	private IOSimulator io;
	private DiaDia diadia;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.labirinto = new LabirintoBuilder()
				.addStanzaBloccata("Atrio", "nord", "chiave")
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1, "Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addAttrezzo("lanterna", 3, "Aula N10")
				.addStanzaBuia("Aula N11", "lanterna")
				.addAttrezzo("evaihc", 2, "Aula N11")
				.addStanzaMagica("Laboratorio Campus")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
//				.addAdiacenza("Aula N10", "Aula N11", "est")
//				.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
				.addPersonaggio("Atrio", "strega")
				.getLabirinto();

	}

	@Test
	void testPartitaVincente() {
		LinkedList<String> input = new LinkedList<String>();
		input.add("vai sud");
		input.add("prendi lanterna");
		input.add("vai nord");
		input.add("vai est");
		input.add("posa lanterna");
		input.add("prendi evaihc");
		input.add("vai ovest");
		input.add("vai ovest");
		input.add("posa evaihc");
		input.add("prendi evaihc");
		input.add("posa evaihc");
		input.add("prendi evaihc");
		input.add("posa evaihc");
		input.add("prendi evaihc");
		input.add("posa evaihc");
		input.add("prendi chiave");
		input.add("vai est");
		input.add("posa chiave");
		input.add("vai nord");
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(this.labirinto, this.io);
		this.diadia.gioca();
		assertEquals("Hai vinto!", this.io.getOutput().getLast());
	}

	@Test
	void testFineCFU() {
		LinkedList<String> input = new LinkedList<String>();
		for(int i = 0; i < 20; i++) {
			input.add("vai est");
		}
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(this.labirinto, this.io);
		this.diadia.gioca();
		assertEquals("Hai esaurito i CFU...", this.io.getOutput().getLast());
	}

	@Test
	void testPartitaInterrotta() throws Exception {
		LinkedList<String> input = new LinkedList<String>();
		input.add("fine");
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(this.labirinto, this.io);
		this.diadia.gioca();
		assertEquals("Grazie di aver giocato!", this.io.getOutput().getLast());
	}

}
