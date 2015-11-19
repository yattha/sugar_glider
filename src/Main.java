
public class Main {

	public static void main(String[] args) {
		testHashTable();

	}

	private static void testHashTable() {
		MyHashTable<String, String> test = new MyHashTable<String, String>(12);
		//System.out.println(test);
		test.put("0", "asdf");
		test.put("1", "asdf");
		
		test.put("2", "asdf");test.put("3", "asdf");
		test.stats();
		//System.out.println(test);
		
		
	}

}
