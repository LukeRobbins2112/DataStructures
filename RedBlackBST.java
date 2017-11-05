
import edu.princeton.cs.algs4;
import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value>{
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node{
		private Key key;
		private Value val;
		private int size;
		private boolean color;

		private Node left;
		private Node right;

		public Node(Key key, Value val, int size, boolean color){
			this.key = key;
			this.val = val;
			this.size = size;
			this.color = color;
		}
	}

	private Node root;

	public RedBlackBST(){

	}


	public int size(){
		return size(this.root);
	}

	private int size(Node h){
		if (h == null){
			return 0;
		}
		return h.size;
	}

	public boolean isEmpty(){
		return this.root == null;
	}

	public Value get(Key key){
		if (key == null){
			throw new IllegalArgumentException();
		}
		return get(this.root, key);
	}

	private Value get(Node h, Key key){

		while (h != null){
			int cmp = key.compareTo(h.key);

			if (cmp < 0){
				h = h.right;
			}
			else if (cmp > 0){
				h = h.left;
			}
			else{
				return h.val;
			}
		}

		return null;
	}

	public boolean contains(Key key){
		return get(key) != null;
	}

	public void put(Key key, Value val){
		if (key == null){
			throw new IllegalArgumentException();
		}
		if (val == null){
			delete(key);
		}

		this.root = put(this.root, key, val);
		this.root.color = BLACK;
	}

	private Node put(Node h, Key key, Value val){
		if (h == null){
			return new Node(Key key, Value val, 1, RED);
		}

		int cmp = key.compareTp(h.key);

		if (cmp < 0){
			h.left = put(h.left, key, val);
		}
		else if (cmp > 0){
			h.right = put(h.right, key, val);
		}
		else{
			h.val = val;
		}

			//correcting any imbalances

		if (!isRed(h.left) && isRed(h.right)){
			h = rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)){
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)){
			flipColors(h);
		}

		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	public void deleteMin(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}

		if (!isRed(this.root.left) && !isRed(this.root.right)){
			this.root.color = RED;
		}

		this.root = deleteMin(this.root);

		if (!isEmpty()){
			this.root.color = BLACK;
		}
	}

	private Node deleteMin(Node h){
		if (h.left == null){
			return null;
		}

		if (!isRed(h.left) && !isRed(h.left.left)){
			h = moveRedLeft(h);
		}

		h.left = deleteMin(h.left);
		return balance(h);
	}

	public void deleteMax(){
		if (isEmpty()){
			throw new NoSuchElementException();
		}

		if(!isRed(this.root.left) && !isRed(this.root.right)){
			this.root.color = RED;
		}

		this.root = deleteMax(this.root);

		if(!isEmpty()){
			this.root.color = BLACK;
		}
	}

	private Node deleteMax(Node h){

		if(isRed(h.left)){
			h = rotateRight(h);
		}
		if (h.right == null){
			return null;
		}

		if (!isRed(h.right) && !isRed(h.right.left)){
			h = moveRedRight(h);
		}

		h.right = deleteMax(h.right);

		return balance(h);
	}

	public void delete(Key key){
		if (key == null){
			throw new IllegalArgumentException();
		}
		if (!contains(key)){
			return;
		}

		if (!isRed(this.root.left) && !isRed(this.root.right)){
			this.root.color = RED;
		}

		this.root = delete(this.root, key);

		if (!isEmpty()){
			this.root.color = BLACK;
		}
	}

	private Node delete(Node h, Key key){
		if (key.compareTo(h.key) < 0){
			if (!isRed(h.left) && !isRed(h.left.left)){
				h = moveRedLeft(h);
			}
			h.left = delete(h.left, key);
		}
		else{
			if (isRed(h.left)){
				h = rotateRight(h);
			}
			if (key.compareTo(h.key) == 0 && (h.right == null)){
				return null;
			}
			if (!isRed(h.right) && !isRed(h.right.left)){
				h = moveRedRight(h);
			}
			if (key. compareTo(h.key) == 0){
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				h.right = deleteMin(h.right);
			}
			else{
				h.right = delete(h.right, key);
			}
		}
		
		return balance(h);
	}


	private boolean isRed(Node h){
		if (h == null){
			return false;
		}
		return h.color == RED;
	}

	private Node rotateLeft(Node h){

		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private void flipColors(Node h){
		h.left.color = BLACK;
		h.right.color = BlACK;
		h.color = RED:
	}

	private Node moveRedLeft(Node h){
		flipColors(h);
		if (isRed(h.right.left)){
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	private Node moveRedRight(Node h){
		flipColors(h);
		if (isRed(h.left.left)){
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	private Node balance(Node h){
		if (isRed(h.right)){
			h.rotateLeft(h);
		}
		if (isRed(h.left) && isRed(h.left.left)){
			h = rotateRight(h);
		}
		if (isRed(h.left) && isRed(h.right)){
			flipColors(h);
		}

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
}






