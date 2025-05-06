package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {

	@Override
	public void esegui(Partita partita, IO io) {
		
		final String[] ELENCO_COMANDI = {"vai", "aiuto", "prendi", "posa", "guarda", "fine"};
		
		for(String comando : ELENCO_COMANDI)
			io.mostraMessaggio(comando);
		io.mostraMessaggio("\n");
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
