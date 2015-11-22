import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;


public class CodingTree {

	private static final int MAP_SIZE = 32768;

	String text;
	public MyHashTable<String, String> codes;
	public MyHashTable<String, Integer> frequencies;
	public String bits;
	Node finishedTree;

	public CodingTree(String fullText) {
		text = fullText;
		codes = new MyHashTable<String, String>(MAP_SIZE);
		frequencies = new MyHashTable<String, Integer>(MAP_SIZE);
		doStuff();
	}

	private void doStuff() {
		countWordFrequency();
		generateTree();
		generateCode();
		encode();		
	}

	private void countWordFrequency() {
		StringBuilder temp = new StringBuilder();
		frequencies.put("--", 1);
		for(char c : text.toCharArray()) {		  
			//&& temp.length()>0 && temp.charAt(temp.length()-1) != '-'
			if((c + "").matches("[a-zA-Z0-9]") || c == '\'' || (c == '-' ))  {
				temp.append(c);				
			} else {
				

				//if(temp.length()>0 &&temp.charAt(temp.length()-1) != '-') temp.delete(temp.length()-1, temp.length());
				if (!frequencies.contains(temp.toString()))frequencies.put(temp.toString(), 1);
				else frequencies.put(temp.toString(), frequencies.get(temp.toString()) + 1);

				if(!frequencies.contains("" + c)) frequencies.put("" + c, 0); 
				else frequencies.put(""+c, (frequencies.get(""+c) + 1));
				temp.delete(0, temp.length());

			}
		}
		if (!frequencies.contains(temp.toString()))frequencies.put(temp.toString(), 1);
		else frequencies.put(temp.toString(), frequencies.get(temp.toString()) + 1);

	}
	
	private void generateTree() {
		ArrayList<MyEntry> freqList = frequencies.toList();
		PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
		while(!freqList.isEmpty()) {
			Node temp = new Node(freqList.remove(0));
			nodeQueue.add(temp);
		}
		Node leftNode, rightNode, parentNode;

		while(nodeQueue.size() > 1) {
			parentNode = new Node(null);			
			leftNode = nodeQueue.poll();
			rightNode = nodeQueue.poll();
			parentNode.addleft(leftNode);
			parentNode.addRight(rightNode);
			nodeQueue.add(parentNode);
		}
		finishedTree = nodeQueue.poll();
	
	}
	
	private void generateCode() {
		traverseTree(finishedTree, "");
	}


	private void traverseTree(Node n, String s) {				
		if(n.isLeaf()) {
			codes.put(n.data.key, s);
		}else {
		traverseTree(n.left, s += "0");		
		traverseTree(n.right, s = s.substring(0, s.length()-1) + "1");
		}
	}
	private void encode() {
		// TODO Auto-generated method stub

	}
	
	

	

private class Node implements Comparable<Node>{
		
		Node left;
		Node right;
		MyEntry<String, Integer> data;
		int weight;

		Node(MyEntry<String, Integer> d){
			data = d;
			left = null;
			right = null;
			if(d==null) weight = 0;
			else weight = ((Integer)data.value);
		}

		void addleft(Node n) {
			this.left = n;
			weight += n.weight;
		}

		void addRight(Node n) {
			this.right = n;
			weight += n.weight;
		}

		boolean isLeaf() {
			return Objects.isNull(left) && Objects.isNull(right);
		}

		public int compareTo(Node other) {
			return weight - other.weight;
		}

		public String toString() {
			return "" + weight;
		}
	}





}
