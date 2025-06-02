package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoInteragisci;
import it.uniroma3.diadia.comandi.ComandoSaluta;

class StregaTest {

	private Comando comando;
	private Partita partita;
	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception{
		Labirinto casa = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10)
				.addStanza("bagno")
				.addAttrezzo("spazzolino", 1)
				.addAttrezzo("asciugamano", 2)
				.addAdiacenza("salotto", "camera", "nord")
				.addAdiacenza("salotto", "bagno", "est")
				.addPersonaggio("salotto", "strega")
				.getLabirinto();
		this.partita = new Partita(casa);
		this.io = new IOSimulator();
	}
	
	@Test
	void testSaluta() {
		this.comando = new ComandoSaluta();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Ciao, io sono Sabrina la strega. Piacere di conoscerti!");
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getLast(), "Ciao, io sono Sabrina la strega. Ci siamo gia' presentati!");
	}
	
	void testAgisciSenzaSalutare() {
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getFirst(), "Maleducato! Normalmente si saluta! Adesso ti sposterò in una brutta stanza!");
		assertEquals("camera", this.partita.getStanzaCorrente().getNome());
	}
	
	void testAgisciDopoAverSalutato() {
		this.comando = new ComandoSaluta();
		this.comando.esegui(partita, io);
		this.comando = new ComandoInteragisci();
		this.comando.esegui(partita, io);
		assertEquals(this.io.getOutput().getLast(), "Che persona educata, permettimi di portarti in una bella stanza");
		assertEquals("camera", this.partita.getStanzaCorrente().getNome());
	}
	
}
