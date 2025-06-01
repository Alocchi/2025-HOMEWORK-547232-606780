package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaCreata;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanze = new HashMap<String, Stanza>();
		this.ultimaStanzaCreata = null;
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
		if(this.stanze.containsKey(nome)) {
			this.labirinto.setEntrata(this.stanze.get(nome));
		}
		else {
			this.addStanza(nome);
			this.labirinto.setEntrata(this.stanze.get(nome));
		}
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		if(this.stanze.containsKey(nome)) {
			this.labirinto.setUscita(this.stanze.get(nome));
		}
		else {
			this.addStanza(nome);
			this.labirinto.setUscita(this.stanze.get(nome));
		}
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaCreata = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza stanza = new StanzaMagica(nome);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaCreata = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome, soglia);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaCreata = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String chiave) {
		Stanza stanza = new StanzaBloccata(nome, direzione, chiave);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaCreata = stanza;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String oggetto) {
		Stanza stanza = new StanzaBuia(nome, oggetto);
		this.stanze.put(nome, stanza);
		this.ultimaStanzaCreata = stanza;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.ultimaStanzaCreata.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addPersonaggio(String stanza, String nome) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		StringBuilder nomePersonaggio = new StringBuilder("it.uniroma3.diadia.ambienti.");
		nomePersonaggio.append(Character.toUpperCase(nome.charAt(0)));
		nomePersonaggio.append(nome.substring(1));
		AbstractPersonaggio personaggio = (AbstractPersonaggio)Class.forName(nomePersonaggio.toString()).newInstance();
		this.stanze.get(stanza).setPersonaggio(personaggio);
		
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String s1, String s2, String direzione) {
		this.stanze.get(s1).impostaStanzaAdiacente(direzione, this.stanze.get(s2));
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.stanze;
	}
	
}
