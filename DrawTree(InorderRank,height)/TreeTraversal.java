class Node {
	
	String data;
	Node left;
	Node right;
	
	public Node(String data,Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

}
public class TreeTraversal {
	
	public static void DrawTree(Node v, int[] rankHeight) {
		
		if(v.left!=null) {
			if(rankHeight[1]!=0) {rankHeight[1]--;}
			DrawTree(v.left,rankHeight);
		}
		
		System.out.println(v.data + ": (" + (++rankHeight[0]) + "," + rankHeight[1] + ")");
		
		if(v.right!=null) {
			rankHeight[1]--;
			DrawTree(v.right,rankHeight);
		}
		
		rankHeight[1]++;
		
	}

	public static void main(String[] args) {
		
		Node N0 = new Node("H",null,null);         
		
		Node N1 = new Node("D",N0,null);
		Node N2 = new Node("E",null,null);
		Node N3 = new Node("F",null,null);
		Node N4 = new Node("G",null,null);
		
		Node N5 = new Node("B",N1,N2);
		Node N6 = new Node("C",N3,N4);
		
		Node N7 = new Node("A",N5,N6);
		
		int [] array = {0,0};
		DrawTree(N7,array);
		
		/*                              A              height = 3
		 *                            /   \           
		 *                           B     C           height = 2
		 *                          / \   / \
		 *                         D  E  F   G         height = 1
		 *                        /
		 *                       H                     height = 0
		 *                       
		 *    In-order Traversal: HDBEAFCG
		 *    Printing the tree in the format (In-order rank, Height)
		 */

	}

}
