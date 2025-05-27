package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private int pesoMax;
	private Map<String, Attrezzo> attrezzi;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String, Attrezzo>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return attrezzi.containsKey(attrezzo.getNome());
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		Collection<Attrezzo> temp = new ArrayList<Attrezzo>();
		temp = this.attrezzi.values();
		for (Attrezzo attrezzo : temp) {
			peso += attrezzo.getPeso();
		}

		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if(this.isEmpty()) {
			return false;
		}
		Attrezzo temp = this.attrezzi.get(nomeAttrezzo);
		return this.attrezzi.remove(nomeAttrezzo).equals(temp);
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Comparator<Attrezzo> comp = new Comparator<Attrezzo>(){
			@Override
			public int compare(Attrezzo a1, Attrezzo a2) {
				if(a1.getPeso() == a2.getPeso()) {
					return a1.compareTo(a2);
				}
				return a1.getPeso() - a2.getPeso();
			}
		};
		List<Attrezzo> attrezziOrdinati = new ArrayList<Attrezzo>(attrezzi.values());
		Collections.sort(attrezziOrdinati, comp);
		
		return attrezziOrdinati;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		return new TreeSet<Attrezzo>(this.attrezzi.values());
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set<Attrezzo> tmp = new TreeSet<Attrezzo>();
		Map<Integer,Set<Attrezzo>> mappa = new TreeMap<Integer, Set<Attrezzo>>();
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			if(mappa.containsKey(attrezzo.getPeso())) {
				tmp = mappa.get(attrezzo.getPeso());
				tmp.add(attrezzo);
			}
			else {
				tmp = new TreeSet<Attrezzo>();
				tmp.add(attrezzo);
				mappa.put(attrezzo.getPeso(), tmp);
			}
		}
		
		return mappa;
	}
	
	public String toString() {
		Collection<Attrezzo> temp = new ArrayList<Attrezzo>();
		temp = this.getContenutoOrdinatoPerNome();
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : temp)
				s.append(attrezzo.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
