import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class FibonacciCalculators {
	
	public static long binFib(long n) {
		if(n == 0) {return 0;}
	    else if(n == 1) {return 1;}
	    else {return binFib(n - 1) + binFib(n - 2);}
	}
	
	public static long[] linFib(long n){
		
		if (n <= 1) {
			long[ ] answer = {n, 0};
			return answer;
		}else {
			long[ ] temp = linFib(n-1); 
			long[ ] answer = {temp[0] + temp[1], temp[0]}; 
			return answer;
		}
		
	}
	
	public static long tailFib(long n, long a, long b) {
		if (n == 0)
            return a;
        if (n == 1)
            return b;
        return tailFib(n - 1, b, a + b);
			
	}
	
	/*
	 * Note that I have used long instead of integer
	 * as fib values of higher n values go beyond
	 * the range of integer data type.
	 */

	public static void main(String[] args) {
		
		PrintWriter pw1 = null;
		PrintWriter pw2 = null;
		PrintWriter pw3 = null;
		
		try{
			pw1 = new PrintWriter(new FileOutputStream("OutLinFib.txt"));
			pw2 = new PrintWriter(new FileOutputStream("OutTailFib.txt"));
			pw3 = new PrintWriter(new FileOutputStream("OutBinFib.txt"));
		}catch(FileNotFoundException e) {	
			System.out.println("Could not open/create the files to write to. "
					+ " Please try again later.");
			System.out.print("Program will terminate.");
			System.exit(0);			   		   
		}
		
		long fibNo = 0;
		long startTime = 0;
		long duration = 0;
		
		//LINEAR FIB [O(n)]
		//values after F92 are negative as long overflow occours (max limit of long is surpassed)
		//Hence only till F90 is calculated
		
		pw1.println("OUTPUT RUNTIMES FOR LINEAR RECURSIVE FIBONACCI ALGORITHM\n");
		pw1.println(String.format("%-5s \t %20s \t %20s\n","n","nth Fibonacci Number","Run-time(nanoseconds)"));
		
		for (int i = 0; i <= 90; i+=5) {
			startTime = System.nanoTime();
			fibNo = linFib(i)[0];
			duration = System.nanoTime() - startTime;
			pw1.println(String.format("%-5s \t %-20s \t %-20s\n",i,fibNo,duration));
		}
		
		//TAIL FIB [O(n)]
		//values after F92 are negative as long overflow occours (max limit of long is surpassed)
		//Hence only till F90 is calculated
		
		pw2.println("OUTPUT RUNTIMES FOR TAIL RECURSIVE FIBONACCI ALGORITHM\n");
		pw2.println(String.format("%-5s \t %20s \t %20s\n","n","nth Fibonacci Number","Run-time(nanoseconds)"));
		
		for (int i = 0; i <= 90; i+=5) {
			startTime = System.nanoTime();
			fibNo = tailFib(i,0,1);
			duration = System.nanoTime() - startTime;
			pw2.println(String.format("%-5s \t %-20s \t %-20s\n",i,fibNo,duration));
		}
		
		//BINARY FIB [O(2^n)]
		//stopped calculating at 45 as my local machine takes lot more time beyond 50
		
		pw3.println("OUTPUT RUNTIMES FOR BINARY RECURSIVE FIBONACCI ALGORITHM\n");
		pw3.println(String.format("%-5s \t %20s \t %20s\n","n","nth Fibonacci Number","Run-time(nanoseconds)"));
		
		for (int i = 0; i <= 45; i+=5) {
			startTime = System.nanoTime();
			fibNo = binFib(i);
			duration = System.nanoTime() - startTime;
			pw3.println(String.format("%-5s \t %-20s \t %-20s\n",i,fibNo,duration));
		}
		
		System.out.println("DATA SUCCESSFULLY COPIED INTO FILES");
		
		pw1.close();
		pw2.close();
		pw3.close();

	}

}
