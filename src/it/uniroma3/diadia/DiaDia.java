package it.uniroma3.diadia;

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
	private IOConsole console;

	public DiaDia(IOConsole console, int pesoMax){
		this.partita = new Partita(pesoMax);
		this.console = console;
	}
	
	public DiaDia(IOConsole console){
		this.partita = new Partita();
		this.console = console;
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		this.console.mostraMessaggio("\nInizi in " + partita.getStanzaCorrente().getDescrizione());
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.console);
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
		}

		if (this.partita.getGiocatore().getCfu() == 0) {
			this.console.mostraMessaggio("Hai esaurito i CFU...");
		}

		return this.partita.isFinita();
	}


	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}