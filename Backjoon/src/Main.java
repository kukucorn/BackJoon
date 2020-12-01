import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

   public static void main(String[] args) throws IOException {
      
      BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
      //StringBuilder results = new StringBuilder();
      
      String[] inputs = scan.readLine().split(" ");
      int width = Integer.parseInt(inputs[0]);
      int height = Integer.parseInt(inputs[1]);
      
      int[][] box = new int[height][width];
      for(int i = 0; i < height; i++) {
         String[] tomatos = scan.readLine().split(" ");
         for(int j = 0; j < width; j++) {
            box[i][j] = Integer.parseInt(tomatos[j]);
         }
      }
      
      int result = getMinimumDaysForTomatos(box, width, height);
      
      if(!isRipenedAll(box)) result = -1;
      
      System.out.print(result);
      
      scan.close();
   }
   
   private static int getMinimumDaysForTomatos(int[][] box, int width, int height) {
      
	   LinkedList<Position> queue = new LinkedList<>();
	   for(int i = 0; i < height; i++) {
		   for(int j = 0; j < width; j++) {
			   if(box[i][j] == 1) {
				   queue.add(new Position(i, j));
			   }
		   }
	   }
	   
	   int day = -1;
	   while(!queue.isEmpty()) {
		   day++;
		   int size = queue.size();
		   for(int i = 0; i < size; i++) {
			   Position pos = queue.pop();
			   int x = pos.x;
			   int y = pos.y;
			   
			   if(x > 0 && box[x-1][y] == 0) { // 위쪽
				   box[x-1][y] = 1;
				   queue.add(new Position(x-1, y));
			   }
			   if(x < height-1 && box[x+1][y] == 0) { // 아래쪽
				   box[x+1][y] = 1;
				   queue.add(new Position(x+1, y));
			   }
			   if(y > 0 && box[x][y-1] == 0) { // 왼쪽
				   box[x][y-1] = 1;
				   queue.add(new Position(x, y-1));
			   }
			   if(y < width-1 && box[x][y+1] == 0) { // 오른쪽
				   box[x][y+1] = 1;
				   queue.add(new Position(x, y+1));
			   }
		   }
	   }
	   
	   return day < 0 ? 0 : day;
   }
   
   private static boolean isRipenedAll(int[][] box) {
	   for(int i = 0; i < box.length; i++) {
		   for(int j = 0; j < box[i].length; j++) {
			   if(box[i][j] == 0) return false;
		   }
	   }
	   return true;
   }
}

class Position {
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}