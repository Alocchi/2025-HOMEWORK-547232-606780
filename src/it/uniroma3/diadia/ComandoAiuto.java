package it.uniroma3.diadia;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {

	@Override
	public void esegui(Partita partita, IOConsole console) {
		
		final String[] ELENCO_COMANDI = {"vai", "aiuto", "prendi", "posa", "fine"};
		
		for(String comando : ELENCO_COMANDI)
			console.mostraMessaggio(comando);
		console.mostraMessaggio("\n");
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}

}
