package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	private static final String STANZE_BUIE_MARKER = "Stanze buie:";

	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:";

	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	private static final String PERSONAGGI_MARKER = "Personaggi:";



	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	//	private Map<String, Stanza> nome2stanza;
	//	private Stanza stanzaIniziale;
	//	private Stanza stanzaVincente;

	private LabirintoBuilder builder;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		//this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEImpostaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
			//Stanza stanza = new Stanza(nomeStanza);			
			//this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String nomiStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String stanzaBuia : separaStringheAlleVirgole(nomiStanzeBuie)) {
			String nomeStanza = null; 
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(stanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo che illumina la stanza "+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
			}
			this.builder.addStanzaBuia(nomeStanza, nomeAttrezzo);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanzeMagiche)) {
			this.builder.addStanzaMagica(nomeStanza);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String nomiStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String stanzaBloccata : separaStringheAlleVirgole(nomiStanzeBloccate)) {
			String nomeStanza = null; 
			String direzione = null;
			String nomeAttrezzo = null;
			try (Scanner scannerLinea = new Scanner(stanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata: "+direzione+"."));
				direzione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo che sblocca la direzione: "+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
			}
			this.builder.addStanzaBloccata(nomeStanza, direzione , nomeAttrezzo);
		}
	}


	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.builder.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.builder.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		this.builder.addStanzaVincente(nomeStanzaVincente);
		//		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		//		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(this.builder.isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
			//Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			//this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	//	private boolean this.builder.isStanzaValida(String nomeStanza) {
	//		return this.nome2stanza.containsKey(nomeStanza);
	//	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	
	private void leggiEImpostaPersonaggi() throws FormatoFileNonValidoException, InstantiationException, IllegalAccessException, ClassNotFoundException  {
		String nomiPersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String personaggio : separaStringheAlleVirgole(nomiPersonaggi)) {
			String nomePersonaggio = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio da aggiungere"));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo."));
				nomeStanza = scannerLinea.next();
				
			}
			this.builder.addPersonaggio(nomePersonaggio, nomeStanza);
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(this.builder.isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+ stanzaDa);
		check(this.builder.isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ nomeA);
		//Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		//Stanza arrivoA = this.nome2stanza.get(nomeA);
		//partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	//	public Stanza getStanzaIniziale() {
	//		return this.stanzaIniziale;
	//	}
	//
	//	public Stanza getStanzaVincente() {
	//		return this.stanzaVincente;
	//	}
}