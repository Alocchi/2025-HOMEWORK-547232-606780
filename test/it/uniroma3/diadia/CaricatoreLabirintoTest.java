package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class CaricatoreLabirintoTest {
	
	private StringReader stringReader;
	private CaricatoreLabirinto caricatore;
	
	@Test
	void testMonolocaleConAttrezzo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, FormatoFileNonValidoException {
		String labirintoInput = 
	            "Stanze: salotto\n" +
	            "Stanze buie: \n" +
	            "Stanze magiche: \n" +
	            "Stanze bloccate: \n" +
	            "Inizio: salotto\n" +
	            "Vincente: salotto\n" +
	            "Attrezzi: attrezzo 1 salotto\n" +
	            "Uscite: \n" +
	            "Personaggi: ";
		stringReader = new StringReader(labirintoInput);
		caricatore = new CaricatoreLabirinto(stringReader);
		caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        assertEquals("salotto", labirinto.getEntrata().getNome());
        assertEquals("salotto", labirinto.getUscita().getNome());
        assertTrue(labirinto.getEntrata().hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testBilocaleConAttrezzi() throws InstantiationException, IllegalAccessException, ClassNotFoundException, FormatoFileNonValidoException {
		String labirintoInput = 
	            "Stanze: salotto, camera\n" +
	            "Stanze buie: \n" +
	            "Stanze magiche: \n" +
	            "Stanze bloccate: \n" +
	            "Inizio: salotto\n" +
	            "Vincente: camera\n" +
	            "Attrezzi: attrezzo 1 salotto, attrezzo2 2 camera\n" +
	            "Uscite: salotto est camera\n" +
	            "Personaggi: ";
		stringReader = new StringReader(labirintoInput);
		caricatore = new CaricatoreLabirinto(stringReader);
		caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        assertEquals("salotto", labirinto.getEntrata().getNome());
        assertEquals("camera", labirinto.getUscita().getNome());
        assertTrue(labirinto.getEntrata().hasAttrezzo("attrezzo"));
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("est").hasAttrezzo("attrezzo2"));
        assertEquals("camera", labirinto.getEntrata().getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testTrilocaleVerticaleConAttrezzi() throws InstantiationException, IllegalAccessException, ClassNotFoundException, FormatoFileNonValidoException {
		String labirintoInput = 
	            "Stanze: biblioteca, N10, N11\n" +
	            "Stanze buie: \n" +
	            "Stanze magiche: \n" +
	            "Stanze bloccate: \n" +
	            "Inizio: N10\n" +
	            "Vincente: N11\n" +
	            "Attrezzi: martello 10 biblioteca, pinza 2 N10\n" +
	            "Uscite: biblioteca nord N10, biblioteca sud N11\n" +
	            "Personaggi: ";
		stringReader = new StringReader(labirintoInput);
		caricatore = new CaricatoreLabirinto(stringReader);
		caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        assertEquals("N10", labirinto.getEntrata().getNome());
        assertEquals("N11", labirinto.getUscita().getNome());
        assertEquals("biblioteca", labirinto.getEntrata().getStanzaAdiacente("sud").getNome());
        assertEquals("N11", labirinto.getEntrata().getStanzaAdiacente("sud").getStanzaAdiacente("sud").getNome());
        assertTrue(labirinto.getEntrata().hasAttrezzo("pinza"));
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("sud").hasAttrezzo("martello"));
	}	
	
	@Test
	void testCompleto() throws InstantiationException, IllegalAccessException, ClassNotFoundException, FormatoFileNonValidoException {
		String labirintoInput = 
	            "Stanze: biblioteca, N10\n" +
	            "Stanze buie: N11 lanterna\n" +
	            "Stanze magiche: laboratorioCampus\n" +
	            "Stanze bloccate: atrio nord chiave\n" +
	            "Inizio: atrio\n" +
	            "Vincente: biblioteca\n" +
	            "Attrezzi: osso 1 atrio, lanterna 3 N10, evaihc 2 N11\n" +
	            "Uscite: atrio nord biblioteca, atrio sud N10, atrio est N11, atrio ovest laboratorioCampus, N11 est laboratorioCampus\n" +
	            "Personaggi: cane atrio";
		stringReader = new StringReader(labirintoInput);
		caricatore = new CaricatoreLabirinto(stringReader);
		caricatore.carica();
        Labirinto labirinto = caricatore.getLabirinto();
        assertEquals("atrio", labirinto.getEntrata().getNome());
        assertEquals("biblioteca", labirinto.getUscita().getNome());
        assertEquals("N10", labirinto.getEntrata().getStanzaAdiacente("sud").getNome());
        assertEquals("N11", labirinto.getEntrata().getStanzaAdiacente("est").getNome());
        assertEquals("laboratorioCampus", labirinto.getEntrata().getStanzaAdiacente("ovest").getNome());
        //atrio è bloccato a nord quindi getStanzaAdiacente restituisce la stanza stessa
        assertEquals("atrio", labirinto.getEntrata().getStanzaAdiacente("nord").getNome());
        //per testare controllo quindi l'adiacenza partendo da biblioteca
        assertEquals("atrio", labirinto.getUscita().getStanzaAdiacente("sud").getNome());
        assertEquals("laboratorioCampus", labirinto.getEntrata().getStanzaAdiacente("est").getStanzaAdiacente("est").getNome());
        assertTrue(labirinto.getEntrata().hasAttrezzo("osso"));
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("sud").hasAttrezzo("lanterna"));
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("est").hasAttrezzo("evaihc"));
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("est").isBuia());
        assertTrue(labirinto.getEntrata().getStanzaAdiacente("ovest").isMagica());
        assertTrue(labirinto.getEntrata().isBloccata());
        assertEquals("Bernardo il canetto", labirinto.getEntrata().getPersonaggio().getNome());
	}	
	
}