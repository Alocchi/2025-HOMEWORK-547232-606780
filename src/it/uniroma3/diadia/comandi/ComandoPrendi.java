package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Prendi un attrezzo dalla stanza corrente ed aggiungilo
 * alla borsa
 */
public class ComandoPrendi implements Comando {
	private String wanted;

	@Override
	public void esegui(Partita partita, IO io){
		if(partita.getStanzaCorrente().hasAttrezzo(this.wanted)) {
			if(partita.getGiocatore().getBorsa().getPeso() + partita.getStanzaCorrente().getAttrezzo(this.wanted).getPeso() > partita.getGiocatore().getBorsa().getPesoMax()) {
				io.mostraMessaggio("l'oggetto non entra in borsa");
			}
			else {
				partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.wanted));
				partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.wanted));
				io.mostraMessaggio("hai preso " + this.wanted);
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
		}
		else {
			if(wanted == null) {
				io.mostraMessaggio("non hai specificato l'oggetto");
			}
			else {
				io.mostraMessaggio("l'oggetto " + this.wanted + " non è in " + partita.getStanzaCorrente().getNome());
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.wanted = parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return wanted;
	}
}
