package it.uniroma3.diadia.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComandoVaiTest {

	private Comando comando;

	@BeforeEach
	void setUp() throws Exception{
		this.comando = new ComandoVai();
	}
	
	@Test
	void testVaiSud() {
		comando.setParametro("sud");
		//da completare una volta implementata IOSimulator
	}

}
