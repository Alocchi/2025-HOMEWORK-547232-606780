package it.uniroma3.diadia;

public interface Comando {
	
	/**
	* esecuzione del comando
	*/
	public void esegui(Partita partita, IOConsole console);
	
	/**
	* set parametro del comando
	*/
	public void setParametro(String parametro);

}
