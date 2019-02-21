package com.pos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPOS {

	private POS pos;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		pos = new POS();
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testForTwoProduct() {
		System.setIn(new ByteArrayInputStream("1\ny\n2\nn".getBytes()));
		pos.main(null);
		Assert.assertTrue(outContent.toString().contains("350.00"));
		
	}

	@Test
	public void testForZeroProduct() {
		System.setIn(new ByteArrayInputStream("3\nn".getBytes()));
		pos.main(null);
		Assert.assertTrue(outContent.toString().contains("300.00"));
		
	}
	
	@Test
	public void testForIdAndNameProduct() {
		System.setIn(new ByteArrayInputStream("1\ny\nPen\ny\nEraser\nn".getBytes()));
		pos.main(null);
		Assert.assertTrue(outContent.toString().contains("520.00"));
		
	}
	
	@Test
	public void testInvalidProduct() {
		System.setIn(new ByteArrayInputStream("dymmy\nn".getBytes()));
		pos.main(null);
		Assert.assertTrue(outContent.toString().contains("Invalid selection please select product from product list"));
		
	}
}
