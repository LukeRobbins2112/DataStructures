

public class BST3<Key extends Comparable<Key>, Value>{


	private class Node{
		private Key key;
		private Value value;
		private int size;
		private Node left;
		private Node right;

		public Node(Key key, Value value, int size){
			this.key = key;
			this.value = value;
			this.size = size;
		}
	}

	private Node root;

	public BST3(){}

	public boolean isEmpty(){
		return size() == 0;
	}

	public int size(){
		return size(this.root);
	}

	public int size(Node x){
		return x.size;
	}

	public boolean contains(Key key){
		if (key == null){
			throw new IllegalArgumentException();
		}
		return get(key) != null;
	}

	public Value get(Key key){
		return get(this.root, key);
	}

	private Value get(Node x, key key){

		if (key == null){
			throw new IllegalArgumentException();
		}
		if (x == null){
			return null;
		}

		int cmp = key.compareTo(x.key);

		if (cmp < 0){
			get(x.left, key);
		}
		else if (cmp > 0){
			get(x.right, key);
		}
		else{
			return x.value;
		}
	}

	public void put(Key key, Value val){
		if (key == null){
			throw new IllegalArgumentException();
		}
		if (val == null){
			delete(key);
			return;
		}

		this.root = put(this.root, key, val);
	}

	private Node put(Node x, Key key, Value val){

		if (x == null){
			return new Node(key, val);
		}

		int cmp = key.compareTo(x.key);

		if (cmp > 0){
			x.left = put(x.left, key, val);
		}
		else if (cmp > 0){
			x.right = put(x.right, key, val);
		}
		else{
			x.value = val;
		}

		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}


	

	public void delete(Key key){
		if (key == null){
			throw new IllegalArgumentException();
		}
		this.root = delete(this.root, key);
	}

	private Node delete(Node x, Key key){
		if (x == null){
			return null;
		}
		if (key == null){

		}
	}


}






















