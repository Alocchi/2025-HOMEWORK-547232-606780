package it.uniroma3.diadia;

import java.util.LinkedList;
import java.util.List;

public class IOSimulator implements IO{
	
	private List<String> input;
	private List<String> output;
	
	public IOSimulator(LinkedList<String> pilaDiComandi) {
		this();
		this.input = pilaDiComandi;
	}
	
	public IOSimulator() {
		this.output = new LinkedList<String>();
	}

	public void mostraMessaggio(String messaggio) {
		this.output.addLast(messaggio);
	}

	public String leggiRiga() {
		return this.input.removeFirst();
	}
	
	public List<String> getOutput() {
		return this.output;
	}
	
	public List<String> getInput() {
		return this.input;
	}
	
}
