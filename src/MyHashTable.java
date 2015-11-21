import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MyHashTable <K, V> {
	private List<MyEntry> data;
	private int capacity, numEnts, maxProbe, numProbes;
	private int[] probeHGram;

	
	
	public MyHashTable(final int c) {
		capacity = c;
		data = new ArrayList<MyEntry>(capacity);
		populateEmptyArray();
		numEnts = 0;
		probeHGram = new int[capacity];
		maxProbe = 0;		
	}
	
	

	public void put(K searchKey, V newValue) {		
		int index = hash(searchKey);
		MyEntry temp = new MyEntry(searchKey, newValue);
		while(Objects.nonNull(data.get(index))) {
			data.get(index).probedOver();
			temp.probe();
			index++;
			index%=capacity - 1;			
		}
		probeHGram[temp.probeCount]++;
		data.set(index, temp);
		maxProbe = Math.max(temp.probeCount, maxProbe);
		numEnts++;
	}
	
	V get(K searchKey) {		
		int index = hash(searchKey);
		MyEntry result = data.get(index);
		while(result != null && result.probed && !searchKey.equals(result.key)) {
			index++;			
			index%=capacity - 1;
			result = data.get(index);
			System.out.println("asdfasdf");
		}
		if(!searchKey.equals(result.key)) result = null;
		
		
		
		
		V returnVal = null;
		if(Objects.nonNull(result)) returnVal = result.value;
		
		return returnVal;
	}
	
	public boolean contains(K searchKey) {
		int index = hash(searchKey);
		K checkedKey = data.get(index).key;
		while(Objects.nonNull(checkedKey) && data.get(index).probed && !searchKey.equals(data.get(index).key)) {
			index++;			
			index%=capacity - 1;
			checkedKey = data.get(index).key;
		}
		return checkedKey.equals(searchKey);
	}
	
	public void stats() {
		StringBuilder result = new StringBuilder();
		result.append("Hash Table Stats\n= = = = = = = = = = = = = = = = = = = =\nNumber of Entries: ");
		result.append(numEnts + "\nNumber of Buckets: " + capacity +"\nHistogram of Probes: ");
		result.append(histogramToString() + "\nFill Percentage: " + (numEnts/(double)capacity)*100 + "%");
		result.append("\nMax Linear Prob: " + maxProbe + "\nAverage Linear Prob: " + calcAvgProbe() );
		System.out.println(result.toString() + "\n\n\n" + this);
	}

	private double calcAvgProbe() {
		int result = 0;
		for(int i = 0; i < maxProbe; i++) {
			result+=i*probeHGram[i];
		}
		return result/(double)numEnts;
	}



	private String histogramToString() {
		StringBuilder result = new StringBuilder();
		result.append('[');
		for(int i = 0; i < maxProbe; i++)result.append(probeHGram[i] + ", ");
		if(maxProbe > 0) result.delete(result.length()-2, result.length()).append(']');
		else result.append(']');
		return result.toString();
	}



	private int hash(K searchKey) {
		return 1;
		//return (searchKey.hashCode()&0x7FFF)%capacity;
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
	
	private class MyEntry {
		K key;
		V value;
		boolean probed;
		int probeCount;		
		
		
		public MyEntry(K k, V v) {
			key = k;
			value = v;
			probed = false;
			probeCount = 0;
		}
		
		void probe(){			
			probeCount++;
		}
		
		public String toString() {			
			return "[" + key + ", "  +value + ", " + probed +", " + probeCount + "]";
		}
		
		void probedOver() {
			probed = true;
		}
	}
}
