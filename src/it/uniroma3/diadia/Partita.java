package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	private Giocatore giocatore;
	
	public Partita(Labirinto labirinto, int pesoMax){
		this.labirinto = labirinto;
		this.giocatore = new Giocatore(pesoMax);
		this.stanzaCorrente = this.labirinto.getEntrata();
		this.stanzaVincente = this.labirinto.getUscita();		
		this.finita = false;
	}
	
	public Partita(Labirinto labirinto) throws FileNotFoundException, IOException{
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.stanzaCorrente = this.labirinto.getEntrata();
		this.stanzaVincente = this.labirinto.getUscita();		
		this.finita = false;
	}	

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public String toString() {
		return this.getStanzaCorrente().getDescrizione() + "\nCFU rimanenti: " + giocatore.getCfu() + "\n" + giocatore.getBorsa();
	}
}
