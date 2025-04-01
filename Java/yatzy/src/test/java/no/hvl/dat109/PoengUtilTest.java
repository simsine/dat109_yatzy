package no.hvl.dat109;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.util.PoengUtil;

/**
 * Test for poengutil klassen
 * 
 * @author larsloege
 */
public class PoengUtilTest {
	@Test
	public void testEnere() {
		assertEquals(5,PoengUtil.enere(List.of(1,1,1,1,1)));
		assertEquals(0,PoengUtil.enere(List.of(2,3,4,5,6)));
		assertEquals(3,PoengUtil.enere(List.of(1,1,1,2,6)));
	}

	@Test
	public void testToere() {
		assertEquals(10,PoengUtil.toere(List.of(2,2,2,2,2)));
		assertEquals(6,PoengUtil.toere(List.of(2,2,2,6,5)));
		assertEquals(2,PoengUtil.toere(List.of(2,3,3,1,6)));
		assertEquals(0,PoengUtil.toere(List.of(1,1,1,1,1)));
	}
	
	@Test
	public void testTreere() {
		assertEquals(3,PoengUtil.treere(List.of(3,1,1,1,1)));
		assertEquals(9,PoengUtil.treere(List.of(3,3,3,1,1)));
		assertEquals(15,PoengUtil.treere(List.of(3,3,3,3,3)));
		assertEquals(0,PoengUtil.treere(List.of(1,1,1,1,1)));
	}
	
	@Test
	public void testFirere() {
		assertEquals(0,PoengUtil.firere(List.of(1,1,1,1,1)));
		assertEquals(12,PoengUtil.firere(List.of(4,4,4,1,1)));
		assertEquals(20,PoengUtil.firere(List.of(4,4,4,4,4)));
	}
	
	@Test
	public void testFemmere() {
		assertEquals(0,PoengUtil.femmere(List.of(1,1,1,1,1)));
		assertEquals(10,PoengUtil.femmere(List.of(5,5,1,1,1)));
		assertEquals(20,PoengUtil.femmere(List.of(5,5,5,5,1)));
		assertEquals(25,PoengUtil.femmere(List.of(5,5,5,5,5)));
	}
	
	@Test
	public void testSeksere() {
		assertEquals(0,PoengUtil.seksere(List.of(1,1,1,1,1)));
		assertEquals(18,PoengUtil.seksere(List.of(6,6,6,1,1)));
		assertEquals(6,PoengUtil.seksere(List.of(1,1,1,6,1)));
	}
	
	@Test
	public void testEttPar() {
		//Vi tar den største verdien når det er flere par
		assertEquals(10,PoengUtil.ettPar(List.of(1,1,5,5,3)));
		assertEquals(6,PoengUtil.ettPar(List.of(3,3,4,5,3)));
		assertEquals(12,PoengUtil.ettPar(List.of(1,6,5,6,3)));
		assertEquals(2,PoengUtil.ettPar(List.of(1,1,5,4,3)));
	}
	
	@Test
	public void testToPar() {
		assertEquals(10+4,PoengUtil.ToPar(List.of(2,2,6,5,5)));
		assertEquals(6+4,PoengUtil.ToPar(List.of(2,2,3,3,3)));
		assertEquals(12+10,PoengUtil.ToPar(List.of(6,5,6,5,5)));
		assertEquals(0,PoengUtil.ToPar(List.of(4,2,6,5,5)));
		assertEquals(12,PoengUtil.ToPar(List.of(5,5,6,1,1)));
		assertEquals(12+10,PoengUtil.ToPar(List.of(5, 6, 6, 5, 4)));
	}
	
	@Test
	public void testTreLike() {
		assertEquals(3,PoengUtil.treLike(List.of(1,2,1,6,1)));
		assertEquals(6,PoengUtil.treLike(List.of(2,2,1,6,2)));
		assertEquals(9,PoengUtil.treLike(List.of(1,3,3,6,3)));
		assertEquals(15,PoengUtil.treLike(List.of(1,5,1,5,5)));
		assertEquals(18,PoengUtil.treLike(List.of(6,6,1,6,6)));
		assertEquals(0,PoengUtil.treLike(List.of(1,2,3,5,5)));
	}
	
	@Test
	public void testFireLike() {
		assertEquals(0,PoengUtil.fireLike(List.of(1,2,3,4,5)));
		assertEquals(20,PoengUtil.fireLike(List.of(5,2,5,5,5)));
		assertEquals(8,PoengUtil.fireLike(List.of(2,2,3,2,2)));
		assertEquals(4,PoengUtil.fireLike(List.of(1,1,1,1,1)));
		
	}
	
	@Test
	public void testLitenStraight() {
		assertEquals(15,PoengUtil.litenStraight(List.of(1,2,3,4,5)));
		assertEquals(15,PoengUtil.litenStraight(List.of(2,1,5,4,3)));
		assertEquals(0,PoengUtil.litenStraight(List.of(1,2,3,4,6)));
		assertEquals(0,PoengUtil.litenStraight(List.of(1,2,2,4,1)));
	}
	
	@Test
	public void testStorStraight() {
		assertEquals(20,PoengUtil.storStraight(List.of(2,3,4,5,6)));
		assertEquals(20,PoengUtil.storStraight(List.of(5,6,2,3,4)));
		assertEquals(0,PoengUtil.storStraight(List.of(1,3,4,5,6)));
	}
	
	@Test
	public void testHus() {
		assertEquals(19,PoengUtil.hus(List.of(2,2,5,5,5)));
		assertEquals(26,PoengUtil.hus(List.of(4,4,6,6,6)));
		assertEquals(0,PoengUtil.hus(List.of(1,6,6,6,6)));
		assertEquals(7,PoengUtil.hus(List.of(1,1,1,2,2)));		
	}
	
	@Test
	public void testSjanse() {
		assertEquals(1+2+3+4+5,PoengUtil.sjanse(List.of(1,2,3,4,5)));
		assertEquals(2+2+3+6+5,PoengUtil.sjanse(List.of(2,2,3,6,5)));
		assertEquals(5,PoengUtil.sjanse(List.of(1,1,1,1,1)));
	}
	
	@Test
	public void testYatzy() {
		assertEquals(50,PoengUtil.yatzy(List.of(2,2,2,2,2)));
		assertEquals(0,PoengUtil.yatzy(List.of(2,2,2,1,2)));
	}
}
