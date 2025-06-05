package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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

	private Borsa borsaUnOggetto;
	private Borsa borsaDueOggetti;
	private Borsa borsaQuattroOggetti;
	private Attrezzo piombo;
	private Attrezzo ps;
	private Attrezzo piuma;
	private Attrezzo libro;
	
	@BeforeEach
	void setUp() throws Exception{
		this.borsaUnOggetto = new Borsa();
		this.borsaDueOggetti = new Borsa(20);
		this.borsaQuattroOggetti = new Borsa(50);
		this.piombo = new Attrezzo("piombo", 10);
		this.ps = new Attrezzo("ps", 5);
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 5);
		this.borsaUnOggetto.addAttrezzo(this.piombo);
		this.borsaDueOggetti.addAttrezzo(this.piombo);
		this.borsaDueOggetti.addAttrezzo(this.piuma);
		this.borsaQuattroOggetti.addAttrezzo(this.piombo);
		this.borsaQuattroOggetti.addAttrezzo(this.ps);
		this.borsaQuattroOggetti.addAttrezzo(this.piuma);
		this.borsaQuattroOggetti.addAttrezzo(this.libro);
	}
	
	//Test il più possibile minimali
		
	@Test
	void testHasAttrezzo() {
		assertFalse(this.borsaUnOggetto.hasAttrezzo("spada"));
	}

	@Test
	void testAddAttrezzo() {
		assertTrue(this.borsaUnOggetto.hasAttrezzo("piombo"));
	}
	
	@Test
	void testPesoMassimo() {
		Attrezzo spada = new Attrezzo("spada", 9999);
		assertFalse(this.borsaUnOggetto.addAttrezzo(spada));
	}
	
	@Test
	void testGetAttrezzo(){
		assertEquals(piombo, this.borsaUnOggetto.getAttrezzo("piombo"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.borsaUnOggetto.removeAttrezzo("piombo"));
		assertFalse(this.borsaUnOggetto.hasAttrezzo("piombo"));
	}
	
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		this.borsaUnOggetto.removeAttrezzo("piombo");
		assertFalse(this.borsaUnOggetto.removeAttrezzo("piombo"));
	}
	
	@Test
	void  testContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = this.borsaDueOggetti.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = lista.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	void  testContenutoOrdinatoPerPesoBorsaVuota() throws FileNotFoundException, IOException {
		Borsa borsaVuota = new Borsa();
		List<Attrezzo> lista = borsaVuota.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = lista.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test
	void testContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> set = this.borsaDueOggetti.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = set.iterator();
		assertEquals("piombo", it.next().getNome());
		assertEquals("piuma", it.next().getNome());
	}
	
	@Test
	void testContenutoOrdinatoPerNomeBorsaVuota() throws FileNotFoundException, IOException {
		Borsa borsaVuota = new Borsa();
		SortedSet<Attrezzo> set = borsaVuota.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = set.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test
	void testContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> mappa = this.borsaDueOggetti.getContenutoRaggruppatoPerPeso();
		
		Set<Integer> chiavi = mappa.keySet();
		Iterator<Integer> it1 = chiavi.iterator();
		assertEquals(1, it1.next());
		assertEquals(10, it1.next());
		assertFalse(it1.hasNext());
		
		Set<Attrezzo> peso1 = new TreeSet<Attrezzo>();
		Set<Attrezzo> peso10 = new TreeSet<Attrezzo>();
		peso1.add(this.piuma);
		peso10.add(this.piombo);
		
		Collection<Set<Attrezzo>> valori = mappa.values();
		Iterator<Set<Attrezzo>> it2 = valori.iterator();
		assertEquals(peso1, it2.next());
		assertEquals(peso10, it2.next());
		assertFalse(it2.hasNext());
	}
	
	@Test
	void testContenutoRaggruppatoPerPesoBorsaVuota() throws FileNotFoundException, IOException {
		Borsa borsaVuota = new Borsa();
		Map<Integer,Set<Attrezzo>> mappa = borsaVuota.getContenutoRaggruppatoPerPeso();
		
		Set<Integer> chiavi = mappa.keySet();
		Iterator<Integer> it1 = chiavi.iterator();
		assertFalse(it1.hasNext());
		
		Collection<Set<Attrezzo>> valori = mappa.values();
		Iterator<Set<Attrezzo>> it2 = valori.iterator();
		assertFalse(it2.hasNext());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> set = this.borsaDueOggetti.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = set.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPesoBorsaVuota() throws FileNotFoundException, IOException {
		Borsa borsaVuota = new Borsa();
		SortedSet<Attrezzo> set = borsaVuota.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = set.iterator();
		assertFalse(it.hasNext());
	}
	
	//test non minimali
	
	@Test
	void testContenutoOrdinatoPerPesoCompleto() {
		List<Attrezzo> lista = this.borsaQuattroOggetti.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = lista.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("libro", it.next().getNome());
		assertEquals("ps", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
	@Test
	void testContenutoOrdinatoPerNomeCompleto() {
		SortedSet<Attrezzo> set = this.borsaQuattroOggetti.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = set.iterator();
		assertEquals("libro", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
		assertEquals("piuma", it.next().getNome());
		assertEquals("ps", it.next().getNome());
	}
	
	@Test
	void testContenutoRaggruppatoPerPesoCompleto() {
		Map<Integer,Set<Attrezzo>> mappa = this.borsaQuattroOggetti.getContenutoRaggruppatoPerPeso();
		
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
	
	@Test
	void testGetSortedSetOrdinatoPerPesoCompleto() {
		SortedSet<Attrezzo> set = this.borsaQuattroOggetti.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> it = set.iterator();
		assertEquals("piuma", it.next().getNome());
		assertEquals("libro", it.next().getNome());
		assertEquals("ps", it.next().getNome());
		assertEquals("piombo", it.next().getNome());
	}
	
}
