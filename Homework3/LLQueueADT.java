package homework3;

//Linked List implementation of Queue ADT
public class LLQueueADT {

	public int size;
	public LLNode front;
	public LLNode rear;

	public LLQueueADT() {
		size = 0;
		front = null;
		rear = null;
	}

	public boolean empty() {
		return (size == 0);
	}

	public void enqueue(char c) {
		LLNode newLLNode = new LLNode();
		newLLNode.setData(c);
		newLLNode.setNext(null);
		if (this.empty())
			front = newLLNode;
		else
			rear.setNext(newLLNode);
		rear = newLLNode;
		size++;
	}

	public void enqueue(int n) {
		LLNode newLLNode = new LLNode();
		newLLNode.setData(n);
		newLLNode.setNext(null);
		if (this.empty())
			front = newLLNode;
		else
			rear.setNext(newLLNode);
		rear = newLLNode;
		size++;
	}

	public char dequeue() {
		char i;
		i = front.getData();
		front = front.getNext();
		size--;
		if (this.empty())
			rear = null;
		return i;
	}

	public char front() {
		return front.getData();
	}

	public int size() {
		return size;
	}
}
