import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

	final static int dx[] = {-1, 0, 1, 0};
	final static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(scan.readLine());
		int[][] area = new int[size][size];
		Set<Integer> heightSet = new HashSet<>();
		
		for(int i = 0; i < size; i++) {
			String[] line = scan.readLine().split(" ");
			for(int j = 0; j < size; j++) {
				int height = Integer.parseInt(line[j]);
				area[i][j] = height;
				heightSet.add(height);
			}
		}
		
		Iterator<Integer> iter = heightSet.iterator();
		int max = 0;
		while(iter.hasNext()) {
			int height = iter.next();
			max = Math.max(max, findNumOfGroup(height, area));
		}
		
		System.out.print(max == 0 ? 1 : max);
		
		scan.close();
	}
	
	private static int findNumOfGroup(int waterHeight, int[][] area) {
		int numOfGroup = 0;
		boolean[][] isVisited = new boolean[area.length][area.length];
		
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area.length; j++) {
				if(area[i][j] > waterHeight) {
					if(area[i][j] > waterHeight && !isVisited[i][j]) {
						numOfGroup++;
						findAreaInGroup(waterHeight, area, isVisited, i, j);
					}
				}
			}
		}
		return numOfGroup;
	}
	
	private static void findAreaInGroup(int waterHeight, int[][] area, boolean[][] isVisited, int x, int y) {
		
		isVisited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int near_x = x + dx[i];
			int near_y = y + dy[i];
			try {
				if(area[near_x][near_y] > waterHeight && !isVisited[near_x][near_y]) {
					findAreaInGroup(waterHeight, area, isVisited, near_x, near_y);
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
	}
}