package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo piombo;
	private Attrezzo ps;
	private Attrezzo piuma;
	private Attrezzo libro;
	
	@BeforeEach
	void setUp() throws Exception{
		this.borsa = new Borsa(100);
		this.piombo = new Attrezzo("piombo", 10);
		this.ps = new Attrezzo("ps", 5);
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 5);
		this.borsa.addAttrezzo(this.piombo);
		this.borsa.addAttrezzo(this.ps);
		this.borsa.addAttrezzo(this.piuma);
		this.borsa.addAttrezzo(this.libro);
	}
		
	@Test
	void testHasAttrezzo() {
		assertFalse(this.borsa.hasAttrezzo("spada"));
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("piombo"));
	}
	
	@Test
	void testPesoMassimo() {
		Attrezzo spada = new Attrezzo("spada", 9999);
		assertFalse(this.borsa.addAttrezzo(spada));
	}
	
	@Test
	void testGetAttrezzo(){
		assertEquals(piombo, this.borsa.getAttrezzo("piombo"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.borsa.removeAttrezzo("piombo"));
		assertFalse(this.borsa.hasAttrezzo("piombo"));
	}
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		this.borsa.removeAttrezzo("piombo");
		this.borsa.removeAttrezzo("ps");
		this.borsa.removeAttrezzo("piuma");
		this.borsa.removeAttrezzo("libro");
		assertFalse(this.borsa.removeAttrezzo("piombo"));
	}
	
	@Test
	void testContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = this.borsa.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = lista.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("libro", it.next().getNome());
		assertEquals("ps", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	void testContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> set = this.borsa.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = set.iterator();
		assertEquals("libro", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
		assertEquals("piuma", it.next().getNome());
		assertEquals("ps", it.next().getNome());
	}
	
	@Test
	void testContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		
		Set<Integer> chiavi = mappa.keySet();
		Iterator<Integer> it1 = chiavi.iterator();
		assertEquals(1, it1.next());
		assertEquals(5, it1.next());
		assertEquals(10, it1.next());
		assertFalse(it1.hasNext());
		
		Set<Attrezzo> peso1 = new TreeSet<Attrezzo>();
		Set<Attrezzo> peso5 = new TreeSet<Attrezzo>();
		Set<Attrezzo> peso10 = new TreeSet<Attrezzo>();
		peso1.add(this.piuma);
		peso5.add(libro);
		peso5.add(ps);
		peso10.add(piombo);
		
		Collection<Set<Attrezzo>> valori = mappa.values();
		Iterator<Set<Attrezzo>> it2 = valori.iterator();
		assertEquals(peso1, it2.next());
		assertEquals(peso5, it2.next());
		assertEquals(peso10, it2.next());
		assertFalse(it2.hasNext());
	}
	
}
