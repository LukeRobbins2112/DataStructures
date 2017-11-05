import java.util.Iterator;

public class RAQueue<Item> implements Iterable<Item>{

	private Item[] arr;
	private int N;
	private int first;
	private int last;


	public RAQueue(){
		this.arr = (Item[]) new Object[2];
		this.N = 0;
		this.first = 0;
		this.last = 0;
	}

	public boolean isEmpty(){
		return this.N == 0;
	}

	public int size(){
		return this.N;
	}

	public void enqueue(Item i){
		if (i == null){
			throw new InvalidParameterException();
		}

		if (this.N == this.arr.length){
			resize(this.arr.length * 2);
		}

		this.arr[this.last++] = i;

		if (this.last == this.arr.length){
			this.last = 0;
		}
		this.N++;
	}

	public Item dequeue(){

		if (isEmpty()){
			throw new NoSuchElementException();
		}

		Item i = this.arr[this.first];
		this.arr[this.first] = null;
		this.N--;
		this.first++;

		if (this.first == this.arr.length){
			this.first = 0;
		}

		if (this.N > 0 && this.N == this.arr.length / 4){
			resize(this.arr.length / 2);
		}

		return i;
	}

	public void resize(int newLength){
		Item[] copy = (Item[]) new Object[newLength];
		for (int i = 0; i < this.N; i++){
			copy[i] = this.arr[(this.first + i) % this.arr.length];
		}
		this.arr = copy;
		this.first = 0;
		this.last = this.N;
	}

	public Iterator<Item> ArrayIterator(){
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item>{

		private int i = 0;


		public boolean hasNext(){
			return this.i < N;
		}

		public Item next(){
			if (!hasNext()){
				throw new NoSuchElementException();
			}
			Item it = arr[(first + this.i) % arr.length];
			this.i++;
			return it;
		}

		public void remove(){
			throw new UnsupportedOperationException();
		}


	}
}






