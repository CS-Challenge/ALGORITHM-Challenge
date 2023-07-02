package algorithm_challenge.programmers;

import java.util.HashMap;
import java.util.Map;

public class 풍선_터트리기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.solution(new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33});
        System.out.println(result);
    }

    static class Solution {

        /**
         * 1. 임의의 인접한 두 풍선을 고른 뒤, 두 풍선 중 하나를 터트린다.
         * 2. 터진 풍선들 사이에 빈 공간이 생기면, 빈공간이 없도록 풍선들을 중앙으로 밀착
         * 3. 인접한 두 풍선 중에서 번호가 더 작은 풍선을 터트리는 행위는 최대 1번
         * 4. 최후까지 남기는 것이 가능한 풍선들의 개수를 return
         *
         * 9, -1, -5
         * 9 | [-1, -5] 가 소거 가능한지?
         * -1 | [9, -5] 가 소거 가능한지?
         * -5 | [9, -1] 가 소거 가능한지?
         */
        public int solution(int[] a) {
            int answer = 0;

            /**
             * target을 기준으로 왼쪽 최솟값, 오른쪽 최솟값을 비교하여
             * target이 둘다 크면 최후까지 남는 풍선이 아니다.
             */

            int[] leftMinArr = new int[a.length];
            int[] rightMinArr = new int[a.length];
            int leftMin = a[0];
            int rightMin = a[a.length - 1];
            for(int i = 0; i < a.length; i++) {
                // 왼쪽 최솟값
                leftMin = Math.min(a[i], leftMin);
                // 오른쪽 최솟값
                rightMin = Math.min(a[a.length - i - 1], rightMin);

                // leftMinArr[i]는 a[i]일 때 왼쪽 최솟값을 나타낸다.
                leftMinArr[i] = leftMin;
                // rightMinArr[i]는 a[i]일 때 오른쪽 최솟값을 나타낸다.
                rightMinArr[a.length - i - 1] = rightMin;
            }

            int count = 0;
            for(int i = 0; i < a.length; i++) {
                int target = a[i];
                // 최후로 남는 풍선이 아닌경우
                if(target > leftMinArr[i] && target > rightMinArr[i]) {
                    count++;
                }
            }

            answer = a.length - count;

            return answer;
        }
    }
}
