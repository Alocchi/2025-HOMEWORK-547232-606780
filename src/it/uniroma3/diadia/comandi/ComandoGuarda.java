package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio(partita.toString());
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}
	
}
