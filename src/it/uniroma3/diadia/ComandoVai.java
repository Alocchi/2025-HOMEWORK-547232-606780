package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */

public class ComandoVai implements Comando {
	private String direzione;

	/**
	 * esecuzione del comando
	 */
	@Override
	public void esegui(Partita partita, IOConsole console) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(direzione == null) {
			console.mostraMessaggio("Dove vuoi andare ?\n Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			console.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		//console.mostraMessaggio(partita.getStanzaCorrente().getNome());
		console.mostraMessaggio(partita.toString());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}