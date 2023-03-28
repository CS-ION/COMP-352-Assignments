public class ADFPQ<T> {
	
	private class Node<T> {
		private T data;
		private int key;
		private int index;
		
		public Node(T data, int key, int index) {
			this.data = data;
			this.key = key;
			this.index = index;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}

		public String toString() {
			return "Node [data=" + data + ", key=" + key + ", index=" + index + "]";
		}
		
		
	}
	
	private Node[] array;
	private int size;
	boolean isMinHeap;
	
	
	public ADFPQ(boolean isMinHeap) {
		this.isMinHeap = isMinHeap;
		this.array = new Node[10];
	}
	
	public Node[] expand(){
		
		Node[] new_array = new Node[array.length*2];
		
		for (int i = 0; i < array.length; i++) {
			new_array[i] = array[i];
		}
		
		return new_array;
		
	}
	
	public Node getNode(int index) {
		try{return array[index];}
		catch(IndexOutOfBoundsException e) {return null;}
	}
	
	public String state() {
		if (this.isMinHeap) {
			return "Min-Heap";
		}
		return "Max-Heap";
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//Time Complexity = O(logn)
	public Node removeTop(){
		if(size==0) {return null;}
		
		Node temp = array[0];
		array[0] = array[size-1];
		array[size-1] = null;
		size--;
		Heapify(0, size, isMinHeap); 
		return temp;
	}
	
	public Node top(){
		return array[0];
	}
	
	//Time Complexity = O(logn)
	public void insert(T value, int key) {
		Node<T> newNode = new Node<T>(value, key, size);
		try {array[size] = newNode;}
		catch(ArrayIndexOutOfBoundsException exception) {
			array = this.expand();
			array[size] = newNode;}
		size++;
		
		
		bottomUp(size-1);
	}
	
	public void bottomUp(int i) {
		while (i > 0 && ((isMinHeap && array[i].getKey() < array[(i-1)/2].getKey()) || (!isMinHeap && array[i].getKey() > array[(i-1)/2].getKey()))) {
			Node temp = array[i];
			array[i] = array[(i-1)/2];
			array[(i-1)/2] = temp;
				        
			array[i].setIndex(i);
			array[(i-1)/2].setIndex((i-1)/2);
			temp = null;
				        
			i = (i-1)/2;
		}
	}
	
	//Time Complexity = O(logn)
	public Node remove(Node e) {
	    int index = e.getIndex();
	    
	    Node removedNode = array[index];
	    Node lastNode = array[size-1];
	    array[index] = lastNode;
	    array[size-1] = null;
	    size--;
	    if (index < size) {
	        array[index].setIndex(index);
	        Heapify(index, size, isMinHeap);
	    }
	    return removedNode;
	}
	
	//Time Complexity = O(logn)
	public int replaceKey(Node<T> e, int k) {
	    int oldKey = e.getKey();
	    e.setKey(k);
	    if ((isMinHeap && k>array[(e.getIndex()-1)/2].key) || (!isMinHeap && k<array[(e.getIndex()-1)/2].key)){
	    	Heapify(e.getIndex(), size, isMinHeap);
	    }
	    else if ((isMinHeap && k<array[(e.getIndex()-1)/2].key) || (!isMinHeap && k>array[(e.getIndex()-1)/2].key)) {
	    	bottomUp(e.getIndex());
	    }
	    else if (e.getIndex()==0) {
	    	Heapify(e.getIndex(), size, isMinHeap);
	    }
	    return oldKey;
	}
	
	//Time Complexity = O(1)
	public T replaceValue(Node<T> e, T v) {
	    T oldValue = e.getData();
	    e.setData(v);
	    return oldValue;
	}
	
	//Time Complexity = O(n)
	public void toggle() {      
		isMinHeap = !isMinHeap;
		this.BuildHeap();
	}
	
	public void Heapify(int i, int n, boolean isMinHeap) {
	    int left = 2*i + 1;
	    int right = 2*i + 2;
	    int largestOrSmallest = i;

	    if (isMinHeap) {
	        if (left < n && array[left].key < array[i].key) {
	            largestOrSmallest = left;
	        }
	        if (right < n && array[right].key < array[largestOrSmallest].key) {
	            largestOrSmallest = right;
	        }
	    } else {
	        if (left < n && array[left].key > array[i].key) {
	            largestOrSmallest = left;
	        }
	        if (right < n && array[right].key > array[largestOrSmallest].key) {
	            largestOrSmallest = right;
	        }
	    }

	    if (largestOrSmallest != i) { 
	        Node temp = array[i];
	        array[i] = array[largestOrSmallest];
	        array[largestOrSmallest] = temp;
	        
	        array[largestOrSmallest].setIndex(largestOrSmallest);
	        array[i].setIndex(i);
	        temp = null;
	        
	        Heapify(largestOrSmallest, n, isMinHeap);
	    } 
	}

	public void BuildHeap() {
	    int startIndex = (size/2) - 1;

	    // Heapify all non-leaf nodes
	    for (int i = startIndex; i >= 0; i--) {
	        this.Heapify(i, size, isMinHeap);
	    }

	}
	
	public String display(boolean withVal) {
		String heap = "[";
		for(Node element:array) {
			if (element==null) {break;}
			if (!heap.equals("[")) {heap += ",";}
			if (withVal) {heap += "(" + element.key + "," + element.data + ")";}
			else {heap += element.key;}
		}heap += "]";
		return heap;
	}
	
}

