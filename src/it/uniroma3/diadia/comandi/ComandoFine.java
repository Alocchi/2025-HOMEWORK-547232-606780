package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */
public class ComandoFine extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		partita.setFinita();
		io.mostraMessaggio("Grazie di aver giocato!");
	}

	@Override
	public String getNome() {
		return "fine";
	}
}
