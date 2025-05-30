package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
	
	public DiaDia(Labirinto labirinto, IO io){
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
		IO io = new IOConsole();
		
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10)
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto(); 
		
		DiaDia gioco = new DiaDia(bilocale, io);
		gioco.gioca();
	}
}