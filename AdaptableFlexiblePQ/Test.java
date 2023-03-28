import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		
		PrintWriter pw = null;
		
		try{
			pw = new PrintWriter(new FileOutputStream("TestHeaps.txt"));
		}catch(FileNotFoundException e) {	
			System.out.println("Could not open/create the files to write to. "
					+ " Please try again later.");
			System.out.print("Program will terminate.");
			System.exit(0);			   		   
		}
		
		boolean isMinHeap = false;
		
		int key = 1;
		int removal = 0;
		
		for (int i = 3; i < 23; i++) {
			
			int value = 31;
			
			ADFPQ<Character> temp = (ADFPQ<Character>) new ADFPQ<Character>(isMinHeap);
			
			pw.println("PQ IS EMPTY JUST AFTER DECLARATION : " + temp.isEmpty());
			pw.println("SIZE OF THE EMPTY PQ : " + temp.size());
			
			for (int j = 0; j < i; j++) {
				temp.insert((char)(++value + '0'),++key);
			}
			
			pw.println();
			
			if (isMinHeap) {pw.println("MIN HEAP CREATED USING INSERT : " + temp.display(false));}
			else {pw.println("MAX HEAP CREATED USING INSERT : " + temp.display(false));}
			
			pw.println();
			
			pw.println("PQ IS EMPTY AFTER INSERTION : " + temp.isEmpty());
			pw.println("SIZE OF THE NEW PQ : " + temp.size());
			pw.println("STATE OF THE HEAP : " + temp.state());
			
			pw.println();
			
			temp.toggle();
			pw.println("TOGGLING THE HEAP USING HEAPIFY : " + temp.display(false));
			pw.println("STATE OF THE HEAP : " + temp.state());
			
			pw.println();
			
			pw.println("PREVIOUS PQ WITHOUT REPLACE : " + temp.display(true));
			temp.replaceValue(temp.getNode(0), (char)(39 +'0'));
			pw.println("REPLACING VALUE AT INDEX 0 WITH 'W' : " + temp.display(true));
			
			pw.println();
			
			pw.println("PREVIOUS PQ WITHOUT REPLACE : " + temp.display(false));
			temp.replaceKey(temp.getNode(2), 1);
			pw.println("REPLACING KEY AT INDEX 2 WITH '1' : " + temp.display(false));
			
			pw.println();
			
			pw.println("ENTRY AT TOP OF HEAP : " + temp.top());
			pw.println("PREVIOUS PQ WITHOUT REMOVE TOP : " + temp.display(false));
			pw.println("REMOVING THE ENTRY AT THE TOP : " + temp.removeTop());
			pw.println("PQ AFTER REMOVE TOP : " + temp.display(false));
			
			pw.println();
			
			pw.println("PREVIOUS PQ WITHOUT REMOVAL : " + temp.display(false));
			pw.println("REMOVING THE ENTRY AT INDEX " + (++removal%5) + " : " + temp.remove(temp.getNode(removal%5)));
			pw.println("PQ AFTER REMOVING ENTRY AT INDEX " + (removal%5) + " : " + temp.display(false));
			
		    isMinHeap = !isMinHeap;
		    
		    pw.println();
		    pw.println("-----------------------------------------------------------------------------------------------");
		    pw.println();
		}
		
		pw.close();
		System.out.println("CHECK TestHeaps.txt FOR THE TESTING RESULTS");
		
		
	}
}