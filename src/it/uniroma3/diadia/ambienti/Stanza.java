package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza implements Comparable<Stanza>{

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<Direzione, Stanza>();
		this.attrezzi = new HashMap<String, Attrezzo>();
		this.personaggio = null;
	}

	public enum Direzione {
		NORD(0) { @Override
			public Direzione opposta() { return SUD; }
		},
		EST(90) { @Override
			public Direzione opposta() {return OVEST;}
		},
		SUD(180){ @Override
			public Direzione opposta() {return NORD;}
		},
		OVEST(270) { @Override
			public Direzione opposta() { return EST; }
		};
		private final int gradi;
		private Direzione(int gradi) {
			this.gradi = gradi;
		}
		public int getGradi() { return this.gradi; }
		
		public abstract Direzione opposta();
	}
	
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		try {
			Direzione direzioneEnum = Direzione.valueOf(direzione.toUpperCase());
			this.stanzeAdiacenti.put(direzioneEnum, stanza);
			stanza.getMapStanzeAdiacenti().put(direzioneEnum.opposta(), this);
		}
		catch (IllegalArgumentException e){
			return;
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		try {
			Direzione direzioneEnum = Direzione.valueOf(direzione.toUpperCase());
			return this.stanzeAdiacenti.get(direzioneEnum);
		}
		catch (IllegalArgumentException e){
			return null;
		}		
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		List<Attrezzo> lista = new LinkedList<Attrezzo>();
		lista.addAll(this.attrezzi.values());
		return lista;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return this.attrezzi.containsKey(attrezzo.getNome());
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");	
		for(Direzione direzione : this.stanzeAdiacenti.keySet()) {
			risultato.append(" " + direzione.toString().toLowerCase());
		}
		risultato.append("\nAttrezzi nella stanza: ");	
		for (String attrezzo : this.attrezzi.keySet()) {
			risultato.append(attrezzo.toString()+" ");
		}
		if(personaggio != null) {
			String personaggio = this.personaggio.getClass().toString();
			String tipoPersonaggio = personaggio.replace("class it.uniroma3.diadia.personaggi.", "");
			risultato.append("\nc'è qualcuno nella stanza (" + tipoPersonaggio + ")");
		}
		
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo){
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo){
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.keySet().size();
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al riferimento dell'oggetto).
	 * @param wanted riferimento all'oggetto da rimuovere
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(this.isEmpty()) {
			return false;
		}
		return this.attrezzi.remove(attrezzo.getNome()).equals(attrezzo);
	}

	private boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		return this.nome.equals(that.getNome());
	}

	public List<String> getDirezioni() {
		List<String> direzioni = new ArrayList<String>();
		for(Direzione direzioneEnum : this.stanzeAdiacenti.keySet()) {
			direzioni.add(direzioneEnum.toString().toLowerCase());
		}
		return direzioni;
	}

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	@Override
	public int compareTo(Stanza o) {
		Stanza that = (Stanza)o;
		if(this.getNumeroAttrezzi() == that.getNumeroAttrezzi()) {
			return this.getNome().compareTo(that.getNome());
		}
		return this.getNumeroAttrezzi() - that.getNumeroAttrezzi();
	}

}