package it.uniroma3.diadia;

/**
 * Comando "Fine".
 */
public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita, IOConsole console) {
		partita.setFinita();
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}

}
