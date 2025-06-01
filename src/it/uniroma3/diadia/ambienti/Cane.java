package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio{
	
	private static final String NOME_DEFAULT = "Bernardo il canetto";
	private static final String PRESENTAZIONE_DEFAULT = "BAU!";
	private static final String MESSAGGIO_MORSO = "AHIA! Il cane ti ha morso !\nHai perso 1 CFU ...";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	public Cane() {
		super(NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
	}

	@Override
	public String agisci(Partita partita) {
		String msg = MESSAGGIO_MORSO;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
		return msg;
	}

}
