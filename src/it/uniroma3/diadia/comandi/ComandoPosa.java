package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Prendi un attrezzo dalla borsa ed aggiungilo alla
 * stanza corrente
 */
public class ComandoPosa implements Comando {
	private String wanted;

	@Override
	public void esegui(Partita partita, IO io) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.wanted)) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(this.wanted));
			partita.getGiocatore().getBorsa().removeAttrezzo(this.wanted);
			io.mostraMessaggio("hai posato " + this.wanted);
			io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		}
		else {
			io.mostraMessaggio("l'oggetto " + this.wanted + " non è nella borsa ");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.wanted = parametro;
	}

}
