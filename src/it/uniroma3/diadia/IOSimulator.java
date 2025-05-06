package it.uniroma3.diadia;

public class IOSimulator implements IO{
	
	private String[] input;
	private String[] output;
	private int numeroElementi;
	
	public IOSimulator(String[] pilaDiComandi) {
		this();
		this.input = pilaDiComandi;
	}
	
	public IOSimulator() {
		this.output = new String[100];
		this.numeroElementi = 0;
	}

	public void mostraMessaggio(String messaggio) {
		this.output[numeroElementi] = messaggio;
		this.numeroElementi++;
	}

	public String leggiRiga() {
		String ris = input[0];
		for(int i = 1; i < input.length; i++) {
			input[i-1] = input[i];
		}
		return ris;
	}
	
	public String[] getOutput() {
		return this.output;
	}
	
	public String[] getInput() {
		return this.input;
	}
	
	public int getNumeroElementiOutput() {
		return this.numeroElementi;
	}

}
