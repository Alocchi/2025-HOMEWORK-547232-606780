package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private static final String NOME_DEFAULT = "Otto il mago";
	
	private static final String PRESENTAZIONE_DEFAULT = "Piacere di conoescerti!";
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";

	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private static final String MESSAGGIO_RICEVI_REGALO = "Questo oggetto sembra molto pesante, lascia che lo alleggerisca con la mia magia";

	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	public Mago() {
		super(NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
		this.attrezzo = new Attrezzo("mantello", 2);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg = MESSAGGIO_RICEVI_REGALO;
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2));
		return msg;
	}

}
