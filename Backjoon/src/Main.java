import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// 0시 부터 시계방향으로(위, 오른쪽, 아래, 왼쪽)
	final static int[] dx = {-1, 0, 1, 0};
	final static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		
		
		int size = Integer.parseInt(scan.readLine());
		char[][] colorMap = new char[size][size];
		for(int i = 0; i < size; i++) {
			String colorLine = scan.readLine();
			for(int j = 0; j < size; j++) {
				colorMap[i][j] = colorLine.charAt(j);
			}
		}
		
		int numOfGroupOfNormal = getNormalNum(colorMap, size);
		int numOfGroupOfWeaknessColor = getWeaknessNum(colorMap, size);
			
		System.out.print(numOfGroupOfNormal + " " + numOfGroupOfWeaknessColor );
		
		scan.close();
	}
	
	private static int getNormalNum(char[][] colorMap, int size) {
		
		boolean[][] isVisited = new boolean[size][size];
		int numOfGroup = 0;
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if(!isVisited[x][y]) {
					numOfGroup++;
					findNearGroupOfNormal(colorMap, isVisited, x, y);
				}
			}
		}
		return numOfGroup;
	}
	
	private static void findNearGroupOfNormal(char[][] colorMap, boolean[][] isVisited, int xIndex, int yIndex) {
		isVisited[xIndex][yIndex] = true;
		
		for(int i = 0; i < 4; i++) {
			int x = xIndex + dx[i];
			int y = yIndex + dy[i];
			
			try {
				if(colorMap[x][y] == colorMap[xIndex][yIndex]) {
					if(!isVisited[x][y]) findNearGroupOfNormal(colorMap, isVisited, x, y);
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		
		return;
	}
	
private static int getWeaknessNum(char[][] colorMap, int size) {
		
		boolean[][] isVisited = new boolean[size][size];
		int numOfGroup = 0;
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if(!isVisited[x][y]) {
					numOfGroup++;
					findNearGroupOfWeakness(colorMap, isVisited, x, y);
				}
			}
		}
		return numOfGroup;
	}
	
	private static void findNearGroupOfWeakness(char[][] colorMap, boolean[][] isVisited, int xIndex, int yIndex) {
		isVisited[xIndex][yIndex] = true;
		
		for(int i = 0; i < 4; i++) {
			int x = xIndex + dx[i];
			int y = yIndex + dy[i];
			
			try {
				if(!isVisited[x][y]) {
					if(colorMap[xIndex][yIndex] == 'B') {
						if(colorMap[x][y] == 'B') findNearGroupOfWeakness(colorMap, isVisited, x, y);
					} else {
						if(colorMap[x][y] == 'R' || colorMap[x][y] == 'G') findNearGroupOfWeakness(colorMap, isVisited, x, y);
					}
				}
				 
			} catch(ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		
		return;
	}
}
