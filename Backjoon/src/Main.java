import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

   public static void main(String[] args) throws IOException {
      
      BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
      //StringBuilder results = new StringBuilder();
      
      int nCount = Integer.parseInt(scan.readLine());
      Node[] nodes = new Node[nCount+1];
      for(int i = 1; i < nCount+1; i++) {
    	  nodes[i] = new Node(i);
      }
      
      String[] strNums = scan.readLine().split(" ");
      int person1 = Integer.parseInt(strNums[0]);
      int person2 = Integer.parseInt(strNums[1]);
      
      int eCount = Integer.parseInt(scan.readLine());
      for(int i = 0; i < eCount; i++) {
    	  String[] parsedEdge = scan.readLine().split(" ");
    	  int num1 = Integer.parseInt(parsedEdge[0]);
    	  int num2 = Integer.parseInt(parsedEdge[1]);
    	  
    	  nodes[num1].connect(nodes[num2]);
    	  nodes[num2].connect(nodes[num1]);
      }
      
      boolean[] isVisited = new boolean[nCount+1];
      
      // person1부터 person2까지의 촌수를 구한다.
      int degreeOfKinship = findKinshipFrom1To2(isVisited, nodes[person1], nodes[person2]); 
      
      System.out.print(degreeOfKinship);
      
      scan.close();
   }
   
   private static int findKinshipFrom1To2(boolean[] isVisited, Node startPerson, Node finishPerson) {
	   
	   LinkedList<Node> queue = new LinkedList<>();
	   isVisited[startPerson.num] = true;
	   queue.add(startPerson);
	   
	   int kinship = 0;
	   
	   while(!queue.isEmpty()) {
		   kinship++;
		   int size = queue.size();
		   for(int i = 0; i < size; i++) {
			   Node person = queue.pop();
			   for(int j = 0; j < person.connects.size(); j++) {
				   Node nearRelative = person.connects.get(j);
				   if(nearRelative.num == finishPerson.num) return kinship;
				   if(!isVisited[nearRelative.num]) {
					   isVisited[nearRelative.num] = true;
					   queue.add(nearRelative);
				   }
			   }
		   }
	   }
	   
	   return -1;
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