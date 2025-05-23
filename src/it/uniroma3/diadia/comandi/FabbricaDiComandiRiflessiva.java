package it.uniroma3.diadia.comandi;

import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	@Override
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if(scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next();
		}
		if(scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next();
		}
		try {
		StringBuilder nomeClasse = new StringBuilder("it.uaniroma3.diadia.comandi.Comando");
		nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
		nomeClasse.append(nomeComando.substring(1));
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		comando.setParametro(parametro);
		} catch (InstantiationException e) {
			comando = new ComandoNonValido();
			parametro = "possibile causa: lo sviluppatore si è dimenticato di aggiun-\r\n"
					+ "gere un costruttore no-args in una sottoclasse di Comando";
			comando.setParametro(parametro);
		} catch (IllegalAccessException e) {
			comando = new ComandoNonValido();
			parametro = "possibile causa: lo sviluppatore si è dimenticato di rendere\r\n"
					+ "pubblico il costruttore no-args di un sottoclasse di Comando";
			comando.setParametro(parametro);
		} catch (ClassNotFoundException e) {
			comando = new ComandoNonValido();
			parametro = "possibile causa: comando ignoto – errore digitazione utente";
			comando.setParametro(parametro);
			}
		
		return comando;
	}

}
