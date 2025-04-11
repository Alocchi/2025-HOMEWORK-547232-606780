package it.uniroma3.diadia;

/**
 * Prendi un attrezzo dalla stanza corrente ed aggiungilo
 * alla borsa
 */
public class ComandoPrendi implements Comando {
	private String wanted;

	@Override
	public void esegui(Partita partita, IOConsole console) {
		if(partita.getStanzaCorrente().hasAttrezzo(this.wanted)) {
			partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.wanted));
			partita.getStanzaCorrente().removeAttrezzo(partita.getStanzaCorrente().getAttrezzo(this.wanted));
			console.mostraMessaggio("hai preso " + this.wanted);
			console.mostraMessaggio(partita.toString());
		}
		else {
			console.mostraMessaggio("l'oggetto " + this.wanted + " non è in " + partita.getStanzaCorrente().getNome());
			console.mostraMessaggio(partita.toString());
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.wanted = parametro;
	}
}
