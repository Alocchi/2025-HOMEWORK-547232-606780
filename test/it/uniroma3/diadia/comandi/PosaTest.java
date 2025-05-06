package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class PosaTest {

	private Comando comando;
	private Partita partita;
	private IO io;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoPosa();
		this.partita = new Partita();
		this.io = new IOConsole();
		this.attrezzo = new Attrezzo("attrezzo", 10);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}
	
	@Test
	void testPosaAttrezzo() {
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals(this.partita.getStanzaCorrente().getAttrezzo("attrezzo"), attrezzo);
		//da modificare una volta implementato IOSimulator
	}
	
	@Test
	void testPrendiAttrezzoInesistente() {
		this.comando.setParametro("AttrezzoTest");
		this.comando.esegui(partita, io);
		//da modificare una volta implementato IOSimulator
	}
	
	@Test
	void testPrendiAttrezzoNullo() {
		this.comando.esegui(partita, io);
		//da modificare una volta implementato IOSimulator
	}

}
