import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = scan.readLine().split(" ");
		int height = Integer.parseInt(inputs[0]);
		int width = Integer.parseInt(inputs[1]);
		
		int[][] maze = new int[height+1][width+1];
		
		for(int i = 1; i < height+1; i++) {
			String line = scan.readLine();
			for(int j = 0; j < width; j++) {
				if(line.charAt(j) == '1') {
					maze[i][j+1] = Integer.MAX_VALUE;
				}
			}
		}
		
		findDistanceFromStart(maze, width, height);
		
		System.out.print(maze[height][width]);
		
		scan.close();
	}
	
	private static void findDistanceFromStart(int[][] maze, int width, int height) {	
		LinkedList<Pos> queue = new LinkedList<>();
		
		final int[] dx = {0, 1, 0, -1};
		final int[] dy = {1, 0, -1, 0};
		
		final int X_BOUNDARY = height+1;
		final int Y_BOUNDARY = width+1;
		
		queue.add(new Pos(1, 1));
		int distance = 1;
		maze[1][1] = distance;
		while(!queue.isEmpty()) {
			distance++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				Pos curPos = queue.pop();
				for(int j = 0; j < 4; j++) {
					int x = curPos.x + dx[j];
					int y = curPos.y + dy[j];
					if(x < X_BOUNDARY && y < Y_BOUNDARY && distance < maze[x][y]) {
						maze[x][y] = distance;
						queue.add(new Pos(x, y));
					}
				}
			}
		}
	}
}

class Pos {
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}