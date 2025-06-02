package it.uniroma3.diadia.personaggi;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio{
	
	private static final String NOME_DEFAULT = "Sabrina la strega";
	
	private static final String PRESENTAZIONE_DEFAULT = "Piacere di conoscerti!";
	
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
			Set<Stanza> stanzeAdiacentiOrdinate = new TreeSet<Stanza>();
			stanzeAdiacentiOrdinate.addAll(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
			Stanza teletrasporto = Collections.max(stanzeAdiacentiOrdinate);
			partita.setStanzaCorrente(teletrasporto);
		}
		else {
			msg = NON_HA_SALUTO;
			Set<Stanza> stanzeAdiacentiOrdinate = new TreeSet<Stanza>();
			stanzeAdiacentiOrdinate.addAll(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
			Stanza teletrasporto = Collections.min(stanzeAdiacentiOrdinate);
			partita.setStanzaCorrente(teletrasporto);
		}
		
		return msg;
	}

	
	
}
