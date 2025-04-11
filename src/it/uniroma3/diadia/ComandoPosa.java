package it.uniroma3.diadia;

/**
 * Prendi un attrezzo dalla borsa ed aggiungilo alla
 * stanza corrente
 */
public class ComandoPosa implements Comando {
	private String wanted;

	@Override
	public void esegui(Partita partita, IOConsole console) {
		if(partita.getGiocatore().getBorsa().hasAttrezzo(this.wanted)) {
			partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(this.wanted));
			partita.getGiocatore().getBorsa().removeAttrezzo(this.wanted);
			console.mostraMessaggio("hai posato " + this.wanted);
			console.mostraMessaggio(partita.toString());
		}
		else {
			console.mostraMessaggio("l'oggetto " + this.wanted + " non è nella borsa ");
			console.mostraMessaggio(partita.toString());
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.wanted = parametro;
	}

}
