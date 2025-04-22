package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

class ComandoVaiTest {

	private Comando comando;
	private Partita partita;
	private IO io;

	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoVai();
		this.partita = new Partita();
		this.io = new IOConsole();
	}
	
	@Test
	void testVaiSud() {
		this.comando.setParametro("sud");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getStanzaCorrente().getNome(), "Aula N10");
		//da modificare una volta implementato IOSimulator
	}
	
	@Test
	void testVaiInDirezioneNonEsistente() {
		comando.setParametro("destra");
		this.comando.esegui(partita, io);
		//da modificare una volta implementato IOSimulator
	}
	
	@Test
	void testVaiInDirezioneNulla() {
		this.comando.esegui(partita, io);
		//da modificare una volta implementato IOSimulator
	}

}