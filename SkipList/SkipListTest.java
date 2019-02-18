import static org.junit.Assert.*;

import org.junit.Test;

public class SkipListTest {

	@Test
	/**
	 * Run the test
	 */
	public void test() {
		SkipList <String, Integer> skipList = new SkipList<String, Integer>();
		skipList.insert("f", 5);
		skipList.insert("c", 1);
		skipList.insert("d", 3);
		skipList.insert("e", 2);
		skipList.insert("a", 2);
		skipList.insert("g", 10);
		skipList.insert("z", 10);
		skipList.insert("k", 0);
		skipList.insert("h", 3);
		assertSame(skipList.search("f"), 5);
		assertSame(skipList.search("c"), 1);
		assertSame(skipList.search("d"), 3);
		assertSame(skipList.search("e"), 2);
		assertSame(skipList.search("a"), 2);
		assertSame(skipList.search("g"), 10);
		assertSame(skipList.search("z"), 10);
		assertSame(skipList.search("k"), 0);
		assertSame(skipList.search("h"), 3);
		try {
			skipList.search("b");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key b doesn't exist in the skip list");
		}
		try {
			skipList.search("j");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key j doesn't exist in the skip list");
		}
		skipList.delete("a");
		skipList.delete("d");
		skipList.delete("g");
		skipList.insert("z", 3); //update "z"'s value to 3
		skipList.insert("k", 5); //update "k"'s value to 5
		assertSame(skipList.search("f"), 5); 
		assertSame(skipList.search("c"), 1);
		assertSame(skipList.search("e"), 2);
		assertSame(skipList.search("z"), 3);
		assertSame(skipList.search("k"), 5);
		assertSame(skipList.search("h"), 3);
		try {
			skipList.search("a");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key a doesn't exist in the skip list");
		}
		try {
			skipList.search("d");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key d doesn't exist in the skip list");
		}
		try {
			skipList.search("b");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key b doesn't exist in the skip list");
		}
		try {
			skipList.search("j");
			fail("Expected an IllegalArgumentException to be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "The key j doesn't exist in the skip list");
		}
		skipList.printList();
	}

}
