package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private String messaggio;

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Comando non valido\n" + messaggio);
	}

	@Override
	public void setParametro(String parametro) {
		this.messaggio = parametro;
	}

	@Override
	public String getNome() {
		return "non valido";
	}

	@Override
	public String getParametro() {
		return messaggio;
	}

}
