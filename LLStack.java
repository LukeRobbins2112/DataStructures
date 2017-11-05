import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item>{

	private int N;
	private Node<Item> first;

	public LinkedListStack(){
		this.N = 0;
		this.first = null;
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

	public void push(Item i){
		if (i == null)
			throw InvalidParameterException();
		Node<Item> oldFirst = this.first;
		this.first = new Node<Item>();
		this.first.item = i;
		this.first.next = oldFirst;
		this.N++;
	}

	public Item pop(){
		if (isEmpty())
			throw new NoSuchElementException();
		Item i = this.first.item;
		this.first = this.first.next;
		this.N--;
		return i;
	}

	public Item peek(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}
		return this.first.item;
	}

	public Iterator<Item> ListIterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>{

		private Node<Item> current = first;


		public boolean hasNext(){
			return !(this.current == null);
		}

		public Item next(){
			if (!hasNext())
				throw new NoSuchElementException();
			Item i = this.current.item;
			this.current = this.current.next;
			return i;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

	}

}