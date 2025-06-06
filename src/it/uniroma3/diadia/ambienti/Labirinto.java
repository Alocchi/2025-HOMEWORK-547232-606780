package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Labirinto {
	private Stanza entrata;
	private Stanza uscita;

	public static class LabirintoBuilder {
		
		private Stanza entrata;
		private Stanza uscita;
		private Map<String, Stanza> stanze;
		
		public LabirintoBuilder() {
			this.stanze = new HashMap<String, Stanza>();
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			if(this.stanze.containsKey(nome)) {
				this.entrata = this.stanze.get(nome);
			}
			else {
				this.addStanza(nome);
				this.entrata = this.stanze.get(nome);
			}
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nome) {
			if(this.stanze.containsKey(nome)) {
				this.uscita = this.stanze.get(nome);
			}
			else {
				this.addStanza(nome);
				this.uscita = this.stanze.get(nome);
			}
			return this;
		}
		
		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.stanze.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza stanza = new StanzaMagica(nome);
			this.stanze.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanza = new StanzaMagica(nome, soglia);
			this.stanze.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String chiave) {
			Stanza stanza = new StanzaBloccata(nome, direzione, chiave);
			this.stanze.put(nome, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String oggetto) {
			Stanza stanza = new StanzaBuia(nome, oggetto);
			this.stanze.put(nome, stanza);
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso, String stanza) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			this.stanze.get(stanza).addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addPersonaggio(String stanza, String nome) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			StringBuilder nomePersonaggio = new StringBuilder("it.uniroma3.diadia.personaggi.");
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
		
		public boolean isStanzaValida(String nomeStanza) {
			return this.stanze.containsKey(nomeStanza);
		}
		
		public Labirinto getLabirinto() {
			Labirinto labirinto = new Labirinto();
			labirinto.setEntrata(entrata);
			labirinto.setUscita(uscita);
			return labirinto;
		}
		
		public Stanza getEntrata() {
			return this.entrata;
		}
		
		public Stanza getUscita() {
			return this.uscita;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}
		
	}
	
	private Labirinto() { }

	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}
	
	public void setEntrata(Stanza entrata) {
		this.entrata = entrata;
	}

	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}

	public Stanza getEntrata() {
		return this.entrata;
	}

	public Stanza getUscita() {
		return this.uscita;
	}


}
