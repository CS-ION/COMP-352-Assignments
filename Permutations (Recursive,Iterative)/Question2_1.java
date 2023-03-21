public class Question2_1 {
 
    // Recursive Permutations 
	// Time Complexity = O(n*n!)
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3};
        int n = arr.length;
        permute(arr, 0, n - 1);
    }
 
    private static void permute(int[] arr, int l, int r)
    {
        if (l == r) {
        	for (int element:arr) {
            System.out.print(element + " ");
        }System.out.println();}
        else {
            for (int i = l; i <= r; i++) {
                arr = swap(arr, l, i);
                permute(arr, l + 1, r);
                arr = swap(arr, l, i);
            }
        }
    }
 
    public static int[] swap(int[] arr, int i, int j)
    {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
}
