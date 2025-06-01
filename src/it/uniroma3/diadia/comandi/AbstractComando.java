package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{
	
	public void setParametro(String parametro) {
		//di default il metodo non fa nulla
	}
	
	public String getParametro() {
		return null;
	}
	
	abstract public void esegui(Partita partita, IO io);
	
	abstract public String getNome();
}
