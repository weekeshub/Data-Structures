package homework3;

//Linked List implementation of Stack ADT
public class LLStackADT {

	public LLNode top;
	public int size;

	public LLStackADT() {
		top = null;
		size = 0;
	}

	public boolean empty() {
		return (top == null);
	}

	public void push(char c) {
		LLNode newLLNode = new LLNode();
		newLLNode.setData(c);
		newLLNode.setNext(top);
		top = newLLNode;
		size++;
	}
	
	public void push(int n) {
		LLNode newLLNode = new LLNode();
		newLLNode.setData(n);
		newLLNode.setNext(top);
		top = newLLNode;
		size++;
	}

	public char pop() {
		char i;
		i = top.getData();
		top = top.getNext();
		size--;
		return i;
	}
	
	public int popInt() {
		int i;
		i = top.getIntData();
		top = top.getNext();
		size--;
		return i;
	}

	public char ontop() {
		char i = pop();
		push(i);
		return i;
	}
	
	public int ontopInt() {
		int i = popInt();
		push(i);
		return i;
	}

	public int size() {
		return size;
	}

}
