package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}

}
