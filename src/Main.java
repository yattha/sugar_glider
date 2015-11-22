import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
	static final String DEFAULT_TXT = "./src/WarAndPeace.txt";
	//static StringBuilder text;
	static CodingTree huffTree;
	
	public static void main(String[] args) {
		//testHashTable();
		readDefaultText();
		huffTree.frequencies.stats();
		System.out.println(huffTree.frequencies.get(" "));
		outputCodes();

	}

	private static void readDefaultText() {	
		try {
			huffTree = new CodingTree(readFile(DEFAULT_TXT, Charset.defaultCharset()));
		} catch (IOException e) {			
			e.printStackTrace();		}
	}

	private static void testHashTable() {
		MyHashTable<String, String> test = new MyHashTable<String, String>(6);
		//System.out.println(test);
		test.put("0", "a");System.out.println(test);
		test.put("1", "b");System.out.println(test);
		
		test.put("2", "c");System.out.println(test);test.put("2", "d");System.out.println(test);
		test.stats();
		System.out.println(test.get("2"));
		System.out.println(test.get("1"));
		System.out.println(test.get("8"));
		
		
		
	}
	
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	static void outputCodes() {
		PrintWriter out;
		try {
			out = new PrintWriter("./codes.txt");
			out.print(huffTree.codes.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
