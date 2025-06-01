package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio{
	
	private static final String NOME_DEFAULT = "Sabrina la strega";
	
	private static final String PRESENTAZIONE_DEFAULT = "Piacere di conoescerti !";
	
	private static final String NON_HA_SALUTO = "Maleducato! Normalmente si saluta! Adesso ti sposterò in una brutta stanza!";
	
	private static final String HA_SALUTATO = "Che persona educata, permettimi di portarti in una bella stanza";
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	public Strega() {
		super(NOME_DEFAULT, PRESENTAZIONE_DEFAULT);
	}

	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.haSalutato()) {
			msg = HA_SALUTATO;
		}
		else {
			msg = NON_HA_SALUTO;
		}
		
		return msg;
	}

	
	
}
