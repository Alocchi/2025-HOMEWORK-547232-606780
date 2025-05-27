package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiaDiaTest {

	private IOSimulator io;
	private DiaDia diadia;
	
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
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Hai vinto!", this.io.getOutput().getLast());
	}
	
	@Test
	void testFineCFU() {
		LinkedList<String> input = new LinkedList<String>();
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		input.add("vai est");
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Hai esaurito i CFU...", this.io.getOutput().getLast());
	}
	
	@Test
	void testPartitaInterrotta() throws Exception {
		LinkedList<String> input = new LinkedList<String>();
		input.add("fine");
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Grazie di aver giocato!", this.io.getOutput().getLast());
	}

}
