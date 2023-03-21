
public class ArrayStack<E> { 
	
	public static final int CAPACITY = 4; 
	private E[] data;
	private int size = 0;
	
	public ArrayStack( ) { 
		this(CAPACITY);
	}
	
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity]; 
	}  
	
	public int size( ) { 
		return size; 
	}
	
	public boolean isEmpty() { 
		return (size==0); 
	} 
	
	public void push(E e) {
		try {data[size] = e;}
		catch(ArrayIndexOutOfBoundsException exception) {
			data = this.expand();
			data[size] = e;}
		size++;
	}
	
	public E[] expand(){
		
		E[] new_data = (E[]) new Object[data.length*2];
		
		for (int i = 0; i < data.length; i++) {
			new_data[i] = data[i];
		}
		
		return new_data;
		
	}
	
	public E pop() { 
		if (isEmpty( )) {return null;}
		E answer = data[size-1];
		data[size-1] = null;
		size--;
		return answer;
	}  
	
	public E top() { 
		return data[size-1];
	}  
	
	public String toString() {
		if (isEmpty( )) {System.out.print("{}");}
		else {
			System.out.print("{");
			for (int i = 0; i < size ; i++) {
				if (i!=size-1) {System.out.print(data[i]+",");}
				else {System.out.print(data[i]);}
			   }System.out.print("}");
			}return "";
	}
	
}