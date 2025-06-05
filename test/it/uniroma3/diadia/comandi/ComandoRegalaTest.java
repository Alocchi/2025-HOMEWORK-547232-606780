package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Strega;

class ComandoRegalaTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception{
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		this.io = new IOSimulator();
	}
	
	@Test
	void testRegalaANessuno() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("attrezzo", 1));
		this.comando = new ComandoRegala();
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals("Non c'è nessuno a cui dare il regalo...", this.io.getOutput().getFirst());
	}
	
	@Test
	void testRegaloCheNonHai() {
		this.comando = new ComandoRegala();
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		assertEquals("Non puoi regalare qualcosa che non hai", this.io.getOutput().getFirst());
	}
	
	@Test
	void testRegaloABuonFine() {
		this.partita.getStanzaCorrente().setPersonaggio(new Strega());
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("attrezzo", 1));
		this.comando = new ComandoRegala();
		this.comando.setParametro("attrezzo");
		this.comando.esegui(partita, io);
		this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo");
	}
}
