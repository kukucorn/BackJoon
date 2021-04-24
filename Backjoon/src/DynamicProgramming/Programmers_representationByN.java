package DynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Programmers_representationByN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 2;
		int number = 11;
		System.out.println(new Solution().solution(N, number));
	}
}

/*
 * N���� ǥ���� �� �ִ� ����
 * N�� 1���� ����� ���, N
 * N�� 2���� ����� ���, NN, (N�� 1���� ����� ���) +-/* (N�� 1���� ����� ���)
 * N�� 3���� ����� ���, NNN, (N�� 1��) ��Ģ���� (N�� 2��), (N�� 2��) ��Ģ���� (N�� 1��)
 * ...
 * */

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        Set<Integer>[] sets = new Set[9];
        
        for(int i = 1; i < 9; i++) {
        	sets[i] = new HashSet<Integer>();
        }
        
        for(int i = 1; i < 9; i++) {
        	int repeatNum = getRepeatNum(N, i);
        	sets[i].add(repeatNum);
        	map.putIfAbsent(repeatNum, i);
        	for(int j = 1; j < i; j++) {
        		Integer[] leftArr = sets[j].toArray(new Integer[sets[j].size()]);
        		Integer[] rightArr = sets[i-j].toArray(new Integer[sets[i-j].size()]);
        		for(Integer num1 : leftArr) {
        			for(Integer num2 : rightArr) {
        				sets[i].add(num1 + num2);
        				map.putIfAbsent(num1 + num2, i);
        				sets[i].add(num1 - num2);
        				map.putIfAbsent(num1 - num2, i);
        				sets[i].add(num1 * num2);
        				map.putIfAbsent(num1 * num2, i);
        				if(num2 != 0) {
        					sets[i].add(num1 / num2);
        					map.putIfAbsent(num1 / num2, i);
        				}
        			}
        		}	
        	}

    		if(map.containsKey(number)) {
    			answer = map.get(number);
    			break;
    		}
        }
        
        return answer;
    }
    
    private int getRepeatNum(int num, int repeat) {
    	String strNum = "";
    	for(int i = 0; i < repeat; i++) {
    		strNum += Integer.toString(num);
    	}
    	return Integer.parseInt(strNum);
    }
}