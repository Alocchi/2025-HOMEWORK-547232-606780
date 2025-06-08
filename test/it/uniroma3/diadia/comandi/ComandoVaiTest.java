package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

class ComandoVaiTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;

	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoVai();
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10, "camera") 
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		this.partita = new Partita(bilocale);
		this.io = new IOSimulator();
	}
	
	@Test
	void testVaiSud() {
		this.comando.setParametro("nord");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getStanzaCorrente().getNome(), "camera");
	}
	
	@Test
	void testVaiInDirezioneNonEsistente() {
		comando.setParametro("destra");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Direzione inesistente");
	}
	
	@Test
	void testVaiInDirezioneNulla() {
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Dove vuoi andare ?\n Devi specificare una direzione");
	}
	
	@Test
	void testVaiMonolocale() throws FileNotFoundException, IOException {
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		String[] direzioni = {"nord", "sud", "est", "ovest"};
		for(String direzione : direzioni) {
			comando.setParametro(direzione);
			this.comando.esegui(partita, io);
			assertEquals(this.io.getOutput().getFirst(), "Direzione inesistente");
		}	
	}

}