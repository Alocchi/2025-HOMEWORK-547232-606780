package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String oggettoChiave;

	public StanzaBloccata(String nome, String direzione, String chiave) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.oggettoChiave = chiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(dir.equals(direzioneBloccata)) {
			if(this.hasAttrezzo(oggettoChiave)) {
				return super.getStanzaAdiacente(dir);
			}
			else {
				return this;
			}
		}
		return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getDescrizione());
		risultato.append("\nla direzione " + direzioneBloccata + " è bloccata");
		if(this.hasAttrezzo(oggettoChiave)) {
			risultato.append("\nma l'attrezzo " + this.getAttrezzo(oggettoChiave).getNome() + " presente nella stanza la ha sbloccata");
		}
		return risultato.toString();
	}
	
	@Override
	public boolean isBloccata() {
		return true;
	}
	
}
