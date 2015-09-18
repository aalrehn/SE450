package myhw1;
import static org.junit.Assert.*;
import org.junit.Test;

// TODO: complete the tests
public class VideoTEST {
	@Test
	public void testConstructorAndAttributes() {
		String title1 = "XX";
		String director1 = "XY";
		String title2 = " XX ";
		String director2 = " XY ";
		int year = 2002;

		VideoObj v1 = new VideoObj(title1, year, director1);
		assertSame(title1, v1.title());
		assertEquals(year, v1.year());
		assertSame(director1, v1.director());

		VideoObj v2 = new VideoObj(title2, year, director2);
		assertEquals(title1, v2.title());
		assertEquals(director1, v2.director());
	}

	@Test
	public void testConstructorExceptionYear() {
		try {
			new VideoObj("X", 1800, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 5000, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 1801, "Y");
			new VideoObj("X", 4999, "Y");
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testConstructorExceptionTitle() {
		try {
			new VideoObj(null, 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj(" ", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void testConstructorExceptionDirector() {
		// TODO
		
		try {
			new VideoObj("title", 2002, null);
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("title", 2002, "");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("title", 2002, " ");
			fail();
		} catch (IllegalArgumentException e) { }
		
	}

	@Test
	public void testHashCode() {
		assertEquals
		(-875826552,
				new VideoObj("None", 2009, "Zebra").hashCode());
		assertEquals
		(-1391078111,
				new VideoObj("Blah", 1954, "Cante").hashCode());
	}

	@Test
	public void testEquals() {
		
		VideoObj v1 = new VideoObj("something", 2005, "me");
		VideoObj v2 = new VideoObj("something else", 2001, "you");
		VideoObj v3	= new VideoObj("something", 2005, "me");
		VideoObj v4 = new VideoObj("lalala", 2005, "Carly");
		VideoObj v5 = new VideoObj("the Best Movie Ever", 2015, "M. Night shalamadingdong");
		VideoObj v6 = new VideoObj("the Next Best Movie Ever", 2015, "M. Night shalamadingdong");
		VideoObj v7 = new VideoObj("lalala", 2005, "Carly");
		
		
		assertTrue(v1.equals(v3));
		assertTrue(v3.equals(v1));
		assertTrue(v7.equals(v4));
		assertFalse(v1.equals(v2));
		assertFalse(v7.equals(v6));
		assertFalse(v5.equals(v7));
		assertFalse(v3.equals(v6));
	}

	@Test
	public void testCompareTo() {
		// TODO
		VideoObj v1 = new VideoObj("something", 2005, "me");
		VideoObj v2 = new VideoObj("something", 2005, "me");
		VideoObj v3 = new VideoObj("aaaaa", 2000, "aaaaa");
		VideoObj v4 = new VideoObj("zzzzz", 2050, "zzzzz");
		VideoObj v5 = new VideoObj("aaaaa", 1999, "aaaaa");
		VideoObj v6 = new VideoObj("aaaaa", 2000, "aaaab");
		
		assertEquals(v1.compareTo(v2), 0);
		assertEquals(v3.compareTo(v4), -1);
		assertEquals(v4.compareTo(v3), 1);
		assertEquals(v3.compareTo(v5), 1);
		assertEquals(v3.compareTo(v6), -1);
	}

	@Test
	public void testToString() {
		// TODO
		//"title (year) : director"
		
		VideoObj v1 = new VideoObj("something", 2005, "me");
		VideoObj v2 = new VideoObj("something else", 2001, "you");
		
		String v1String = "\"something (2005) : me\"";
		String v2String = "\"something else (2001) : you\"";
		
		assertEquals(v1.toString(), v1String);
		assertEquals(v2.toString(), v2String);
	}
}
