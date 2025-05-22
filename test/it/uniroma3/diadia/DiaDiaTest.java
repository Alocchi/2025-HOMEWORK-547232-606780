package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiaDiaTest {

	private IOSimulator io;
	private DiaDia diadia;
	
	@Test
	void testPartitaVincente() throws Exception {
		String[] input = new String[19];
		input[0] = "vai sud";
		input[1] = "prendi lanterna";
		input[2] = "vai nord";
		input[3] = "vai est";
		input[4] = "posa lanterna";
		input[5] = "prendi evaihc";
		input[6] = "vai ovest";
		input[7] = "vai ovest";
		input[8] = "posa evaihc";
		input[9] = "prendi evaihc";
		input[10] = "posa evaihc";
		input[11] = "prendi evaihc";
		input[12] = "posa evaihc";
		input[13] = "prendi evaihc";
		input[14] = "posa evaihc";
		input[15] = "prendi chiave";
		input[16] = "vai est";
		input[17] = "posa chiave";
		input[18] = "vai nord";
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Hai vinto!", this.io.getOutput()[this.io.getNumeroElementiOutput() - 1]);
	}
	
	@Test
	void testFineCFU() throws Exception {
		String[] input = new String[20];
		input[0] = "vai est";
		input[1] = "vai est";
		input[2] = "vai est";
		input[3] = "vai est";
		input[4] = "vai est";
		input[5] = "vai est";
		input[6] = "vai est";
		input[7] = "vai est";
		input[8] = "vai est";
		input[9] = "vai est";
		input[10] = "vai est";
		input[11] = "vai est";
		input[12] = "vai est";
		input[13] = "vai est";
		input[14] = "vai est";
		input[15] = "vai est";
		input[16] = "vai est";
		input[17] = "vai est";
		input[18] = "vai est";
		input[19] = "vai est";
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Hai esaurito i CFU...", this.io.getOutput()[this.io.getNumeroElementiOutput() - 1]);
	}
	
	@Test
	void testPartitaInterrotta() throws Exception {
		String[] input = new String[1];
		input[0] = "fine";
		this.io = new IOSimulator(input);
		this.diadia = new DiaDia(io);
		this.diadia.gioca();
		assertEquals("Grazie di aver giocato!", this.io.getOutput()[this.io.getNumeroElementiOutput() - 1]);
	}

}
