import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

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
		while(iter.hasNext()) {
			int height = iter.next();
			
		}
		
		System.out.print("");
		
		scan.close();
	}
	
	private int findNumOfGroup(int waterHeight, int[][] area) {
		int numOfGroup = 0;
		boolean[][] isVisited = new boolean[area.length][area.length];
		
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area.length; j++) {
				if(area[i][j] > waterHeight) {
					if(area[i][j] > waterHeight && !isVisited[i][j]) {
						numOfGroup++;
						findAreaInGroup(waterHeight, area, isVisited);
					}
				}
			}
		}
		return numOfGroup;
	}
	
	private void findAreaInGroup(int waterHeight, int[][] area, boolean[][] isVisited) {
		
	}
}