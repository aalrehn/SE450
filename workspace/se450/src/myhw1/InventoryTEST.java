package myhw1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
// TODO

// TODO: complete the tests
public class InventoryTEST {
	InventorySet s = new InventorySet();
	InventorySet emptySet = new InventorySet();
	final VideoObj v1 = new VideoObj( "A", 2000, "B" );
	final VideoObj v2 = new VideoObj( "B", 2000, "B" );
	final VideoObj v1copy = new VideoObj( "A", 2000, "B" );

	@Test
	public void testSize() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
		s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
		s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
		s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
		s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
		try { s.addNumOwned(v1, -3); fail(); } catch ( IllegalArgumentException e ) {}
		assertEquals( 0, s.size() );
	}

	@Test
	  public void testAddNumOwned() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1, 1);     
		assertEquals( v1, s.get(v1).video );
		assertEquals( 1, s.get(v1).numOwned );
		s.addNumOwned(v1, 2);     
		assertEquals( 3, s.get(v1).numOwned );
		s.addNumOwned(v1, -1);    
		assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v2, 1);     
		assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v1copy, 1); 
		assertEquals( 3, s.get(v1).numOwned );
		assertEquals( 2, s.size() );
		s.addNumOwned(v1, -3);
		assertEquals( 1, s.size() );
		try { s.addNumOwned(null, 1);   
			fail(); 
		} catch ( IllegalArgumentException e ) {}
	}

	@Test
	public void testCheckOutCheckIn() {
		s.addNumOwned(v1, 3);
		s.addNumOwned(v2, 6);
		s.checkOut(v1);
		assertEquals(1,s.get(v1).numOut);
		assertEquals(1,s.get(v1).numRentals);
		s.checkOut(v1);
		assertEquals(2,s.get(v1).numOut);
		assertEquals(2,s.get(v1).numRentals);
		s.checkIn(v1);
		assertEquals(1,s.get(v1).numOut);
		assertEquals(2,s.get(v1).numRentals);
		try { s.checkOut(null);   
			fail(); 
		} catch ( IllegalArgumentException e ) {}
		try { s.checkIn(null);   
			fail(); 
		} catch ( IllegalArgumentException e ) {}
		s.checkOut(v1);//2
		s.checkOut(v1);//3
		try { s.checkOut(v1);   
			fail(); 
		} catch ( IllegalArgumentException e ) {}
		try { s.checkIn(v2);   
			fail(); 
		} catch ( IllegalArgumentException e ) {}
	}


	@Test
	public void testClear() {
		s.addNumOwned(v1, 2);
		assertEquals(s.size(),1);
		s.clear();
		assertEquals(s.size(),0);
	}

	@Test
	public void testGet() {
		// Get should return a COPY of the records, not the records themselves.
		// TODO
		s.addNumOwned(v1, 5);
		s.addNumOwned(v2, 6);
		VideoObj v3 = new VideoObj( "c", 2003, "c" );
		
		
		Record copy1 = s.get(v1);
		Record copy2 = s.get(v1);
		
		assertTrue(copy1.numOut == copy2.numOut);
		assertTrue(copy1.numOwned == copy2.numOwned);
		assertTrue(copy1.numRentals == copy2.numRentals);
		assertTrue(copy1.video.equals(copy2.video));
		
		assertTrue( copy1 != copy2 );
		assertTrue( copy1.toString().equals(copy2.toString()) );
		
		copy1.numOwned += 1;
		copy1.numRentals += 1;
		copy1.numOut += 1;
		
		assertFalse(copy1.numOwned == copy2.numOwned);
		assertFalse(copy1.numRentals == copy2.numRentals);
		assertFalse(copy1.numOut == copy2.numOut);
		assertFalse(copy1.numOwned == s.get(v1).numOwned);
		
		assertEquals(s.get(v3), null);
		
		
	}

	@Test
	public void testToCollection() {
		// Be sure to test that changing records in the returned
		// collection does not change the original records in the
		// inventory.  ToCollection should return a COPY of the records,
		// not the records themselves.
		// TODO
		s.addNumOwned(v1, 1);
		ArrayList<Record> list = (ArrayList<Record>) s.toCollection();
		assertTrue(list.get(0).numOut == s.get(v1).numOut);
		assertTrue(list.get(0).numRentals == s.get(v1).numRentals);
		assertTrue(list.get(0).numOwned == s.get(v1).numOwned);
		
		assertTrue( list.get(0) != s.get(v1) );
		assertTrue( list.get(0).toString().equals(s.get(v1).toString()) );
		
		list.get(0).numOut += 1;
		list.get(0).numRentals += 1;
		list.get(0).numOwned += 1;
		
		assertFalse(list.get(0).numOut == s.get(v1).numOut);
		assertFalse(list.get(0).numRentals == s.get(v1).numRentals);
		assertFalse(list.get(0).numOwned == s.get(v1).numOwned);

		
	}
}
