package it.uniroma3.diadia;

import java.io.*;
import java.util.Properties;

public class ConfiguratoreProperties {
	public static void main(String[] args)
			throws IOException {
		Properties prop = new Properties();
		prop.setProperty("cfu_iniziali", "20");
		prop.setProperty("peso_max_borsa", "10");
		prop.store(new FileWriter("diadia.properties"),
				"Configurazione del gioco DIADIA");
		prop.storeToXML(new FileOutputStream("diadia.xml"),
				"Configurazione del gioco DIADIA");
	}
}