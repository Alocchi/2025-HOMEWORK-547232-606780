package it.uniroma3.diadia.giocatore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Giocatore {

	Properties props = new Properties();
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(int pesoMax) {
		try(FileInputStream input = new FileInputStream("resources/diadia.properties")){
			props.load(input);
			String cfuIniziali = props.getProperty("cfu_iniziali");
			this.cfu = Integer.parseInt(cfuIniziali);
			this.borsa = new Borsa(pesoMax);
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Giocatore() throws FileNotFoundException, IOException {
		try(FileInputStream input = new FileInputStream("diadia.properties")){
			props.load(input);
			String cfuIniziali = props.getProperty("cfu_iniziali");
			this.cfu = Integer.parseInt(cfuIniziali);
			this.borsa = new Borsa();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}

	public int getCfu() {
		return cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

}
