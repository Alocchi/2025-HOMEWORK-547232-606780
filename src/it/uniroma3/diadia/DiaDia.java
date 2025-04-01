package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

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

	static final private String[] ELENCO_COMANDI = {"vai", "aiuto", "prendi", "posa", "fine"};

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
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome() == null) {
			this.console.mostraMessaggio("Comando non valido");
		}
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if(this.partita.isFinita()) {
			if (this.partita.vinta()) {
				this.console.mostraMessaggio("Hai vinto!");
			}
			if(this.partita.getGiocatore().getCfu() == 0) {
				this.console.mostraMessaggio("Hai perso!");
			}
			return true;
		}
		return false;
	}


	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(String comando : ELENCO_COMANDI)
			this.console.mostraMessaggio(comando);
		this.console.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null) {
				this.console.mostraMessaggio("Direzione inesistente");
			}

			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu() - 1;
				this.partita.getGiocatore().setCfu(cfu);
			}
		}
		this.console.mostraMessaggio(this.partita.toString());
	}
	
	/**
	 * Prendi un attrezzo dalla stanza corrente ed aggiungilo
	 * alla borsa
	 */
	private void prendi(String wanted){
		if(this.partita.getStanzaCorrente().hasAttrezzo(wanted)) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(wanted));
			this.partita.getStanzaCorrente().removeAttrezzo(this.partita.getStanzaCorrente().getAttrezzo(wanted));
			this.console.mostraMessaggio("hai preso " + wanted);
			this.console.mostraMessaggio(this.partita.toString());
		}
		else {
			this.console.mostraMessaggio("l'oggetto " + wanted + " non è in " + this.partita.getStanzaCorrente().getNome());
			this.console.mostraMessaggio(this.partita.toString());
		}
	}
	
	/**
	 * Prendi un attrezzo dalla borsa ed aggiungilo alla
	 * stanza corrente
	 */
	private void posa(String wanted){
		if(this.partita.getGiocatore().getBorsa().hasAttrezzo(wanted)) {
			this.partita.getStanzaCorrente().addAttrezzo(this.partita.getGiocatore().getBorsa().getAttrezzo(wanted));
			this.partita.getGiocatore().getBorsa().removeAttrezzo(wanted);
			this.console.mostraMessaggio("hai posato " + wanted);
			this.console.mostraMessaggio(this.partita.toString());
		}
		else {
			this.console.mostraMessaggio("l'oggetto " + wanted + " non è nella borsa ");
			this.console.mostraMessaggio(this.partita.toString());
		}
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}