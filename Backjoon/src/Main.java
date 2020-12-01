import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

   public static void main(String[] args) throws IOException {
      
      BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder results = new StringBuilder();
      
      int nCount = Integer.parseInt(scan.readLine());
      
      Node[] nodes = new Node[nCount+1];
      for(int i = 1; i < nCount+1; i++) {
    	  nodes[i] = new Node(i);
      }
      
      for(int i = 0; i < nCount-1; i++) {
    	  String[] parsedStr = scan.readLine().split(" ");
    	  int num1 = Integer.parseInt(parsedStr[0]);
    	  int num2 = Integer.parseInt(parsedStr[1]);
    	  
    	  nodes[num1].connect(nodes[num2]);
    	  nodes[num2].connect(nodes[num1]);
      }
      
      int[] parentOfNode = new int[nCount+1];
      boolean[] isVisited = new boolean[nCount+1];
      
      // root node부터 찾기 시작
      findAllParentOfNode(parentOfNode, isVisited, nodes[1]); 
      
      for(int i = 2; i < nCount+1; i++) {
    	  results.append(parentOfNode[i] + "\n");
      }
      
      System.out.print(results);
      
      scan.close();
   }
   
   private static void findAllParentOfNode(int[] parentOfNode, boolean[] isVisited, Node root) {
	   
	   LinkedList<Node> queue = new LinkedList<>();
	   isVisited[root.num] = true;
	   queue.add(root);
	   
	   while(!queue.isEmpty()) {
		   int size = queue.size();
		   for(int i = 0; i < size; i++) {
			   Node parent = queue.pop();   
			   for(int j = 0; j < parent.connects.size(); j++) {
				   Node child = parent.connects.get(j);
				   if(!isVisited[child.num]) {
					   isVisited[child.num] = true;
					   parentOfNode[child.num] = parent.num; 
					   queue.add(child);
				   }
			   }
		   }
	   }
   }
}

class Node {
	List<Node> connects;
	int num;
	
	public Node(int n) {
		this.connects = new ArrayList<>();
		this.num = n;
	}
	
	public void connect(Node other) {
		connects.add(other);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Node " + num + " : ");
		for(int i = 0; i < connects.size(); i++) {
			sb.append(connects.get(i).num + ", ");
		}
		
		return sb.toString();
	}
}