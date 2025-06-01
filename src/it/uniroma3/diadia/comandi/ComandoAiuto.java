package it.uniroma3.diadia.comandi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {

	@Override
	public void esegui(Partita partita, IO io) {

		String pacchetto = "it.uniroma3.diadia.comandi";
		String prefisso = "Comando";

		String percorso = "bin/" + pacchetto.replace('.', '/');
		File directory = new File(percorso);

		List<String> comandi = new ArrayList<>();

		if (directory.exists()) {
			for (String file : directory.list()) {
				if (file.endsWith(".class")) {
					String comando = file.substring(0, file.length() - 6);
					if (comando.startsWith(prefisso)) {
						comandi.add(comando);
					}
				}
			}
		}

		for(String comando : comandi) {
			if(!comando.equals("Comando")) {
				if(!comando.contains("Test")) {
					String messaggio = comando.replace("Comando", "");
					io.mostraMessaggio(messaggio);
				}
			}
		}
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
