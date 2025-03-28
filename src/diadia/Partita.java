package diadia;

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
	
	public Partita(int pesoMax){
		this.labirinto = new Labirinto();
		this.giocatore = new Giocatore(pesoMax);
		this.stanzaCorrente = this.labirinto.getEntrata();
		this.stanzaVincente = this.labirinto.getUscita();		
		this.finita = false;
	}
	
	public Partita(){
		this.labirinto = new Labirinto();
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
		return this.getStanzaCorrente() + "\nCFU rimanenti: " + giocatore.getCfu();
	}
}
