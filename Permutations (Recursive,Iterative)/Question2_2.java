import java.util.Queue;
import java.util.LinkedList;

public class Question2_2 {
	
	//Iterative Permutations
	// Time Complexity = O(n*n!)
    public static void permutations(int n) {
        Queue<String> q = new LinkedList<String>();
        for (int i = 1; i <= n; i++) {
            q.add(Integer.toString(i));
        }
        while (!q.isEmpty()) {
            String perm = q.peek();
            if (perm.length() == n) {
                System.out.println(q.remove());
            } else {
                    perm = q.remove();
                    for (int j = 1; j <= n; j++) {
                        if (!perm.contains(Integer.toString(j))) {
                            q.add(perm + Integer.toString(j));    
                    }
                }
            }
        }

    }
    public static void main(String[] args) {
        int n = 4;
        permutations(n);
    }
}
