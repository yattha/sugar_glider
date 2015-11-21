
public class Main {

	public static void main(String[] args) {
		testHashTable();

	}

	private static void testHashTable() {
		MyHashTable<String, String> test = new MyHashTable<String, String>(6);
		//System.out.println(test);
		test.put("0", "a");
		test.put("1", "b");
		
		test.put("2", "c");test.put("3", "d");
		test.stats();
		System.out.println(test.get("2"));
		System.out.println(test.get("1"));
		System.out.println(test.get("8"));
		//System.out.println(test);
		
		
	}

}
