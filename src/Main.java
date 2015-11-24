import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Main {
	static final String DEFAULT_TXT = "./src/WarAndPeace.txt";
	//static StringBuilder text;
	static CodingTree huffTree;
	
	public static void main(String[] args) {
		//testHashTable();
		readDefaultText();
		huffTree.frequencies.stats();
		//System.out.println(huffTree.codes);
		outputCodes();
		outputCompressed();

	}

	private static void outputCompressed() {
		
		try {
			FileOutputStream fos = new FileOutputStream("./compressed");
			System.out.println(huffTree.bits.length);
			fos.write(huffTree.bits);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private static void readDefaultText() {		
			huffTree = new CodingTree(readFile(DEFAULT_TXT, Charset.defaultCharset()));		
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
	
	
	static String readFile(String path, Charset encoding) {			  
		String result = "";	
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			result = new String(encoded, encoding);
		} catch (IOException e) {				
			e.printStackTrace();
		}
		return result;
	}
	
	static void outputCodes() {
		PrintWriter out;
		try {
			out = new PrintWriter("./codes.txt");
			out.print(huffTree.codes.toString());
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
	}

}
