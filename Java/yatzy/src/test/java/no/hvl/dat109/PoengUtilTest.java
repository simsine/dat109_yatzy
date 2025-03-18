package no.hvl.dat109;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import util.PoengUtil;
/**
 * Test for poengutil klassen
 * @author larsloege
 */
public class PoengUtilTest {
	@Mock
	Kopp kopp;
	
	@InjectMocks
	PoengUtil poengUtil;
	@BeforeEach
	void setup() {
		kopp = new Kopp();
		poengUtil = new PoengUtil();
	}
	
	public void testEnere() {
		when(kopp.trill()).thenReturn(List.of(1,1,1,1,1,1));
		
		int poeng = poengUtil.enere(kopp.trill());
		
		assertEquals(poeng,5);
	}
}
