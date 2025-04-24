package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private String oggettoChiave;
	
	public StanzaBuia(String nome, String oggetto) {
		super(nome);
		this.oggettoChiave = oggetto;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(oggettoChiave)) {
			return super.getDescrizione();
		}
		else {
			return("qui c'è buio pesto");
		}
	}

}
