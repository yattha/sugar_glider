import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MyHashTable <K, V> {
	private List<V> data;
	private int capacity;
	
	
	public MyHashTable(final int c) {
		capacity = c;
		data = new ArrayList<V>(capacity);
		populateEmptyArray();
	}
	
	

	public void put(K searchKey, V newValue) {
		System.out.println(data.size());
		int index = hash(searchKey);
		while((data.size()>index) && Objects.nonNull(data.get(index))) {
			index++;
			index%=capacity - 1;
		}
		data.add(index, newValue);
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
		
	}

	private int hash(K searchKey) {
		return Integer.parseInt((String)searchKey);
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
}
