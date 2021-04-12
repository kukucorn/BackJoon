package shortestPathProblem.dijkstra;

public class ConvertingWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin, target, words));
	}

	final static int MAX = 1000;
    
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        // target�� ���� word ã��
        int targetPos = -1;
        boolean isExist = false;
        
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(target)) {
                isExist = true;
                targetPos = i; 
            }
        }
        
        if(!isExist) {
            return 0;
        }
        
        String[] newWords = new String[words.length+1];
        newWords[0] = begin;
        
        // begin�� words�� ��ġ��
        System.arraycopy(words, 0, newWords, 1, words.length);
        
        
        // ���� ���ϱ�
        int[][] weight = new int[newWords.length][newWords.length];
        
        for (int i = 0; i < newWords.length; i++) {
        	weight[i][i] = 0;
        	for (int j = i+1; j < newWords.length; j++) {
        		int incorrectCnt = 0;
        		for (int k = 0; k < newWords[i].length(); k++) {
        			if(newWords[i].charAt(k) != newWords[j].charAt(k)) {
        				incorrectCnt++;
        				if(incorrectCnt > 1) {
        					break;
        				}
        			}
        		}
        		if(incorrectCnt <= 1) {
        			weight[i][j] = 1;
        			weight[j][i] = 1;
        		} else {
        			weight[i][j] = MAX;
        			weight[j][i] = MAX;
        		}
        	}
        }
        
        int[] distance = new int[newWords.length];
        boolean[] check = new boolean[newWords.length];
        
        for(int i = 0; i < newWords.length; i++) {
        	distance[i] = MAX;
        }
        
        dijkstra(0, distance, check, weight);
        
        if(distance[targetPos+1] == MAX) {
        	return 0;
        }
        
        return distance[targetPos+1];
    }
	
	public static void dijkstra(int start, int[] distance, boolean[] check, int[][] weight) {
		distance[start] = 0;
		check[start] = true;
		
		for(int i = 0; i < distance.length; i++) {
			if(!check[i] && weight[start][i] != 0) {
				distance[i] = weight[start][i];
			}
		}
		 
		int n = distance.length;
        for(int a=0;a<n-1;a++){
            //������ ��� ��尡 true�ɶ����� �ε�
            //��尡 n�� ���� �� ���ͽ�Ʈ�� ���ؼ� �ݺ����� n-1���̸� �ȴ�.
            //������ ������ ������ ��尡 ��� true���� Ȯ���ϴ� ������ �����ص� �ȴ�.
            int min=MAX;
            int min_index=-1;
             
            //�ּҰ� ã��
            for(int i=0;i<n;i++){
                if(!check[i] && distance[i]!=MAX){
                    if(distance[i] < min){
                        min=distance[i];
                        min_index = i;
                    }
                }
            }
            
            if(min_index != -1) {
            	check[min_index] = true;
                for(int i = 0; i < n; i++){
                    if(!check[i] && weight[min_index][i]!=0){
                        if(distance[i] > distance[min_index] + weight[min_index][i]){
                            distance[i] = distance[min_index] + weight[min_index][i];
                        }
                    }
                }
            }
            
        }
	}
}