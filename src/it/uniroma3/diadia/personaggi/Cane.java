package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private Attrezzo attrezzo;
	
	private static final String NOME_DEFAULT = "Bernardo il canetto";
	
	private static final String PRESENTAZIONE_DEFAULT = "BAU!";
	
	private static final String MESSAGGIO_MORSO = "AHIA! Il cane ti ha morso !\nHai perso 1 CFU ...";
	
	private static final String MESSAGGIO_REGALO_CORRETTO = "Il cane inzia a fare le feste! E' così felice che lascia un regalo a sua volta!";

	public Cane(String nome, String presentaz, Attrezzo attrezzo) {
		super(nome, presentaz);
		this.attrezzo = attrezzo;
	}
	
	public Cane() {
		super(NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
		this.attrezzo = new Attrezzo("pallina", 1);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_MORSO;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(attrezzo.getNome().equals("osso") || attrezzo.getNome().equals("pallina")) {
			msg = MESSAGGIO_REGALO_CORRETTO;
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			return msg;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			msg = this.agisci(partita);
		}
		return msg;
	}

}
