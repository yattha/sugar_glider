import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MyHashTable <K, V> {
	private List<V> data;
	private int capacity;
	private int numEnts;
	
	
	public MyHashTable(final int c) {
		capacity = c;
		data = new ArrayList<V>(capacity);
		populateEmptyArray();
		numEnts = 0;
	}
	
	

	public void put(K searchKey, V newValue) {		
		int index = hash(searchKey);
		while((data.size()>index) && Objects.nonNull(data.get(index))) {
			index++;
			index%=capacity - 1;
		}
		data.set(index, newValue);
		numEnts++;
	}
	
	V get(K searchKey) {
		V result = null;
		int index = hash(searchKey);
		while(Objects.nonNull(result = data.get(index))) {
			
		}
		
		return data.get(hash(searchKey));
	}
	
	public boolean contains(K searchKey) {
		return Objects.nonNull(get(searchKey));
	}
	
	public void stats() {
		StringBuilder result = new StringBuilder();
		result.append("Hash Table Stats\n= = = = = = = = = = = = = = = = = = = =\nNumber of Entries: ");
		result.append(numEnts + "\nNumber of Buckets: " + capacity +"\nHistogram of Probes: ");
		result.append(toString() + "\nFill Percentage: " + (numEnts/(double)capacity)*100 + "%");
		result.append("\nMax Linear Prob: " + "\nAverage Linear Prob: ");
		System.out.println(result.toString());
	}

	private int hash(K searchKey) {		
		return (searchKey.hashCode()&0x7FFF)%capacity;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append('[');
		for(int i = 0; i < capacity; i++)result.append(data.get(i) + ", ");				
		return result.toString().substring(0, result.length() -2 ) +']';
	}
	
	private void populateEmptyArray() {
		for(int i = 0; i < capacity; i++)data.add(null);
			
		
	}
}
