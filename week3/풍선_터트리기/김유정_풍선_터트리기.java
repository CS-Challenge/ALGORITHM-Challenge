package week3.풍선_터트리기;

import java.util.*;

public class 김유정_풍선_터트리기 {
    public static void main(String[] args) {
        System.out.println("테스트1 정답: 3, 결과: " + new 김유정_풍선_터트리기_Solution().solution(new int[]{9, -1, -5}));
        System.out.println("테스트2 정답: 6, 결과: " + new 김유정_풍선_터트리기_Solution().solution(new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33}));
    }
}

class 김유정_풍선_터트리기_Solution {
    public int solution(int[] a) {
        int answer = 2;
        int[] leftMinArr = new int[a.length];
        int[] rightMinArr = new int[a.length];
        int leftMin = a[0];
        int rightMin = a[a.length - 1];
        for (int i = 0; i < a.length; i++) {
            leftMin = Math.min(leftMin, a[i]);
            rightMin = Math.min(rightMin, a[a.length - i - 1]);
            leftMinArr[i] = leftMin;
            rightMinArr[a.length - i - 1] = rightMin;
        }
        
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] < leftMinArr[i - 1] || a[i] < rightMinArr[i + 1]) {
                answer++;
            }
        }
        
        return answer;
    }
}
