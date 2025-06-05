package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;

public class Labirinto {
	private Stanza entrata;
	private Stanza uscita;

//	public Labirinto(String nomeFile) throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, FormatoFileNonValidoException {
//		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
//		c.carica();
//	}
	
	public void setEntrata(Stanza entrata) {
		this.entrata = entrata;
	}
	
	public void setUscita(Stanza uscita) {
		this.uscita = uscita;
	}
	
	public Stanza getEntrata() {
		return this.entrata;
	}

	public Stanza getUscita() {
		return this.uscita;
	}


}
