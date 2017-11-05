
public class MaxPQ<Key implements Comparable<Key>>{

	private Key[] pq;
	private int N;

	public MaxPQ(){
		this.pq = (Key[]) new Object[2];
		this.N == 0;
	}


	public boolean isEmpty(){
		return this.N == 0;
	}

	public int size(){
		return this.N;
	}

	public void resize(int capacity){
		Key temp = (Key[]) new Object[capacity];

		for (int i = 0; i < this.N; i++){
			temp[i] = this.pq[i];
		}

		this.pq = temp;
	}

	public void insert(Key k){
		if (this.N == this.pq.length){
			resize(this.pq.length * 2);
		}

		this.pq[++this.N] = k;
		swim(this.N);
	}

	public Key delMax(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}

		Key max = this.pq[1];
		exchange(1, this.N--);
		sink(1);
		this.pq[this.N+1] = null;

		if (this.N > 0 && this.N == (this.pq.length - 1)/4){
			resize(this.pq.length/2);
		}

		return max;
	}

	public void sink(int k){
		while (2*k <= this.N){
			int j = 2*k;
			if (j < this.N && less(j, j+1)){
				j++;
			}
			if (!less(k, j)){
				break;
			}

			exchange(k, j);
			k = j;
		}
	}

	public void swim(int k){
		while (k > 1 && less(k/2, k)){
			exchange(k, k/2);
			k = k/2;
		}
	}

	public void less(int i, int j){
		return this.pq[i].compareTo(this.pq[j]) < 0;
	}

	public void exchange(int i, int j){
		Key temp = this.pq[i];
		this.pq[i] = this.pq[j];
		this.pq[j] = temp;
	}


}




