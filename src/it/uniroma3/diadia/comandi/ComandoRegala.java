package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private static final String MESSAGGIO_NESSUNO = "Non c'è nessuno a cui dare il regalo...";

	private String messaggio;
	
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita, IO io) {
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Non puoi regalare qualcosa che non hai");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			this.messaggio = personaggio.riceviRegalo(attrezzo, partita);
			io.mostraMessaggio(this.messaggio);

		} else io.mostraMessaggio(MESSAGGIO_NESSUNO);
		
	}

	@Override
	public String getNome() {
		return "regala";
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

}
