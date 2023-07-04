package week4.표현_가능한_이진트리;

import java.util.*;
import java.util.stream.IntStream;

public class 김유정_표현_가능한_이진트리 {
    public static void main(String[] args) {
        System.out.println(new 김유정_표현_가능한_이진트리_Solution().solution(new long[]{7, 42, 5}));
    }
}

class 김유정_표현_가능한_이진트리_Solution {
    final int AVAILABLE = 1;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (isAvailable(toBinaryStr(numbers[i]))) {
                answer[i] = AVAILABLE;
            }
        }
        return answer;
    }
    
    private String toBinaryStr(long number) {
        // 0001010
        String binaryStr = Long.toBinaryString(number);
        int i = 1;
        while (binaryStr.length() > Math.pow(2, i) - 1) {
            i++;
        }
        return "0".repeat((int) Math.pow(2, i) - 1 - binaryStr.length()) + binaryStr;
    }
    
    private boolean isAvailable(String binaryStr) {
        // 홀스 인덱스는 부모 노드 -> 1
        // 양쪽 자식 노드가 모두 0이면 안됨
        int[] binaryNumArr = IntStream.range(0, binaryStr.length())
                                    .map(i -> binaryStr.charAt(i) - '0')
                                    .toArray();
        System.out.println(Arrays.toString(binaryNumArr));
        for (int i = 1; i < binaryStr.length(); i += 2) {
            if (binaryNumArr[i - 1] + binaryNumArr[i + 1] > 0 && binaryNumArr[i] == 0) {
                System.out.printf("i = %d, binaryNumArr[i] = %d\n", i, binaryNumArr[i]);
                return false;
            }
        }
        return true;
    }
}