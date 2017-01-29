package com.tonyvu.sc.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.tonyvu.sc.exception.ProductNotFoundException;
import com.tonyvu.sc.exception.QuantityOutOfRangeException;

public class CartTest {
	
	private Saleable product1;
	private Saleable product2;
	private Saleable product3;
	private Cart cart;

	@Before
	public void setUp() throws Exception {
		product1 = Mockito.mock(Saleable.class);
		when(product1.getName()).thenReturn("A");
		when(product1.getPrice()).thenReturn(new BigDecimal(10.5));
		
		product2 = Mockito.mock(Saleable.class);
		when(product2.getName()).thenReturn("B");
		when(product2.getPrice()).thenReturn(new BigDecimal(6.8));	
		
		product3 = Mockito.mock(Saleable.class);
		when(product3.getName()).thenReturn("C");
		when(product3.getPrice()).thenReturn(new BigDecimal(7.4));	
		
	    cart = new Cart();
	    cart.add(product1, 2);
		cart.add(product2, 1);
	}
	
	@Test
	public void testGetTotalQuantity() {
		assertEquals(cart.getTotalQuantity(), 3);
	}
	
	@Test
	public void testGetTotalPrice() {
		assertEquals(cart.getTotalPrice().doubleValue(), 2*10.5+6.8, 0);
	}
	
	@Test
	public void testGetItemWithQuantity() {
		assertTrue(cart.getItemWithQuantity().get(product1) == 2);
		assertTrue(cart.getItemWithQuantity().get(product2) == 1);
	}
	
	@Test
	public void testAdd() {
		cart.add(product1, 1);
		
		assertTrue(cart.getItemWithQuantity().get(product1) == 3);
		assertEquals(cart.getTotalQuantity(), 2+1+1);
		assertEquals(cart.getTotalPrice().doubleValue(), 3*10.5+6.8, 0); 
	}
	
	@Test
	public void testUpdate() {
		cart.update(product2, 3);
		
		assertTrue(cart.getItemWithQuantity().get(product2) == 3);
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void testUpdateProductNotFoundException() {
		cart.update(product3, 2);
	}
	
	@Test(expected=QuantityOutOfRangeException.class)
	public void testUpdateQuantityOutOfRangeException() {
		cart.update(product2, -2);
	}
	
	@Test
	public void testRemoveQuantity() {
        cart.remove(product1, 1);
        
		assertEquals(cart.getQuantity(product1), 1);
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void testRemoveQuantityProductNotFoundException() {
		cart.remove(product3);
	}
	
	@Test(expected=QuantityOutOfRangeException.class)
	public void testRemoveQuantityQuantityOutOfRangeException() {
		cart.remove(product1, 3);
	}
	
	@Test
	public void testRemove() {
		cart.remove(product2);
		
		assertFalse(cart.getItemWithQuantity().containsKey(product2));
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void testRemoveProductNotFoundException() {
		cart.remove(product3);
	}
	
	@Test
	public void testClear() {		
		cart.clear();
		
		assertTrue(cart.getItemWithQuantity().isEmpty());
		assertEquals(cart.getTotalQuantity(), 0);
	    assertEquals(cart.getTotalPrice().doubleValue(), 0, 0);			
	}

	@Test 
	public void testGetCost() {
		assertEquals(cart.getCost(product1).doubleValue(), 2*10.5, 0);
		assertEquals(cart.getCost(product2).doubleValue(), 6.8, 0);
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void testGetCostProductNotFoundException() {
		cart.getCost(product3);
	}
	

	@Test(expected=ProductNotFoundException.class)
	public void testGetQuantityProductNotFoundException() {
		cart.getQuantity(product3);
	}
	
	@Test
	public void testGetProducts() {
		assertEquals(cart.getProducts(), new HashSet<Saleable>(Arrays.asList(product1, product2)));
	}
	
	@Test
	public void testToString() {
		String expectedString = "Product: A, Unit Price: 10.50, Quantity: 2\n";
		expectedString += "Product: B, Unit Price: 6.80, Quantity: 1\n";
		expectedString += "Total Quantity: 3, Total Price: 27.80";
		assertEquals(cart.toString(), expectedString);
	}
}
