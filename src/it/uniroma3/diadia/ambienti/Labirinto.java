package it.uniroma3.diadia.ambienti;

public class Labirinto {
	private Stanza entrata;
	private Stanza uscita;

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
