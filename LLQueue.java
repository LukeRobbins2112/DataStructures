import java.util.Iterator;

public class LLQueue<Item> implements Iterable<Item>{

	private Node<Item> first;
	private Node<Item> last;
	private int N;

	public LLQueue(){
		this.first = null;
		this.last = null;
		this.N = 0;
	}

	private class Node<Item>{
		Item item;
		Node<Item> next;
	}

	public boolean isEmpty(){
		return this.first == null;
	}

	public boolean size(){
		return this.N;
	}

	public void enqueue(Item i){
		if (i == null){
			throw new InvalidParameterException();
		}
		
		Node<Item> oldLast = this.last;
		this.last = new Node<Item>();
		this.last.item = i;			
		this.last.next = null;
		if (isEmpty()){
			this.first = this.last;
		}
		else{
			oldLast.next = this.last;
		}
		
		this.N++;
	}

	public Item dequeue(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}

		Item i = this.first.item;
		this.first = this.first.next;
		this.N--;
		if (isEmpty()){
			this.last = null;
		}
		return i;
	}

	public Iterator<Item> ListIterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>{

		private Node<Item> current = first;


		public boolean hasNext(){
			return (! (this.current == null));
		}

		public Item next(){
			if (this.current == null){
				throw new NoSuchElementException();
			}
			Item i = this.current.item;
			this.current = this.current.next;
			return i;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

	}

}





