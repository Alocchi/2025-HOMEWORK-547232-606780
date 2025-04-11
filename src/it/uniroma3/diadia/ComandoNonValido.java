package it.uniroma3.diadia;

public class ComandoNonValido implements Comando {

	@Override
	public void esegui(Partita partita, IOConsole console) {
		console.mostraMessaggio("Comando non valido");
	}

	@Override
	public void setParametro(String parametro) {
		//questo comando non ha parametri
	}

}
