import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{

	private int N;
	private Item[] a;

	public ResizingArrayStack(){
		this.N = 0;
		this.a = (Item[]) new Object[2];
	}

	public boolean isEmpty(){
		return this.N == 0;
	}

	public int size(){
		return this.N;
	}

	public void push(Item i){
		if (this.N == this.a.length){
			resize(a.length * 2);
		}
		this.a[this.N++] = i;
	}

	public Item pop(){
		if (isEmpty())
			throw new NoSuchElementException();

		Item i = this.a[--this.N ];
		this.a[this.N ] = null;
		if (this.N > 0 && this.N == this.a.length/4){
			resize(a.length/2);
		}
		return i;
	}

	public void resize(int newCap){
		Item[] copy = (Item[]) new Object[newCap];
		for (int i = 0; i < this.N; i++){
			copy[i] = a[i];
		}
		this.a = copy;
	}

	public Iterator<Item> ArrayIterator(){
		return new ArrayIterator();
	}

	private class ArrayIterator<Item> implements Iterator<Item>{

		private int i = N - 1;

		public boolean hasNext(){
			return i >= 0;
		}

		public Item next(){
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			return a[this.i--];
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}

	}
}







