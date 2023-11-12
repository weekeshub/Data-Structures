//Node class to be used for building Linked Lists
public class LLNode {

	public char data;
	public int intData;
	public LLNode next;

	public LLNode() {
		this('?', null);
	}

	public LLNode(char d) {
		data = d;
	}
	
	public LLNode(int n) {
		intData = n;
	}

	public LLNode(char d, LLNode n) {
		data = d;
		next = n;
	}
	
	public LLNode(int n, LLNode x) {
		intData = n;
		next = x;
	}

	public void setData(char newData) {
		data = newData;
	}
	
	public void setData(int newData) {
		intData = newData;
	}

	public void setNext(LLNode newNext) {
		next = newNext;
	}

	public char getData() {
		return data;
	}
	
	public int getIntData() {
		return intData;
	}

	public LLNode getNext() {
		return next;
	}

	public void displayNode() {
		System.out.print(data);
	}
	
}
