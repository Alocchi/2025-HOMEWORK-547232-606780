package it.uniroma3.diadia.giocatore;

import java.io.FileNotFoundException;
import java.io.IOException;
import it.uniroma3.diadia.ConfigurazioniIniziali;

public class Giocatore {

	static final public int CFU_INIZIALI = ConfigurazioniIniziali.getCFU();
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(int pesoMax) {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa(pesoMax);
	}
	
	public Giocatore() throws FileNotFoundException, IOException {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public int getCfu() {
		return cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

}
