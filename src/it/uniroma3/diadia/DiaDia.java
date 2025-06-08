package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;

	public DiaDia(Labirinto labirinto, IO io, int pesoMax){
		this.partita = new Partita(labirinto, pesoMax);
		this.io = io;
	}
	
	public DiaDia(Labirinto labirinto, IO io) throws FileNotFoundException, IOException{
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca(){
		String istruzione; 

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.io.mostraMessaggio("\nInizi in " + partita.getStanzaCorrente().getNome());
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	
	private boolean processaIstruzione(String istruzione){
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.io);
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
		}

		if (this.partita.getGiocatore().getCfu() == 0) {
			this.io.mostraMessaggio("Hai esaurito i CFU...");
		}

		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		Scanner scannerDiLinee = new Scanner(System.in);
		try {
			IO io = new IOConsole(scannerDiLinee);
			
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto("resources/labirinto.txt");
			caricatore.carica();
			Labirinto labirinto = caricatore.getLabirinto();
			
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			scannerDiLinee.close();
		}
		
	}
}