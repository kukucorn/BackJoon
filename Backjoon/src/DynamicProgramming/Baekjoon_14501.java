//package DynamicProgramming;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//public class Baekjoon_14501 {
//
//	public static void main(String[] args) throws IOException {
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int N = Integer.parseInt(br.readLine());
//		
//		int[] times = new int[N + 1];
//		int[] prices = new int[N + 1];
//		
//		for(int i = 1; i < N + 1; i++) {
//			String[] inputData = br.readLine().split(" ");
//			times[i] = Integer.parseInt(inputData[0]);
//			prices[i] = Integer.parseInt(inputData[1]);
//		}
//		
//		int[][] table = new int[N+1][N+1];
//		
//		for(int i = 1; i < N + 1; i++) {
//			int expectedPrice = table[i-1][i-1] + prices[i];
//			for(int j = 0; j < N + 1; j++) {
//				if(j < i + times[i] - 1) {
//					table[i][j] = table[i-1][j];
//				} else {
//					table[i][j] = Math.max(table[i-1][j], expectedPrice);
//				}
//			}
//		}
//		
//		System.out.println(table[N][N]);
//	}
//}