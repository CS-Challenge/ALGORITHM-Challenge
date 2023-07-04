package algorithm_challenge.programmers;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://acisliver.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%91%9C%ED%98%84-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%9D%B4%EC%A7%84%ED%8A%B8%EB%A6%AC-JAVA#%ED%8F%AC%ED%99%94%20%EC%9D%B4%EC%A7%84%20%ED%8A%B8%EB%A6%AC-1">참고</a>
 */
public class 표현_가능한_이진트리 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(new long[]{15, 5});
        System.out.println(result);
    }

    /**
     * 1. 이진수를 저장할 빈 문자열 생성
     * 2. 주어진 이진트리에 더미노드 추가(루트 노드 유지)
     * 3. 가장 왼쪽부터 오른쪽 까지 왼쪽에 있는 순서대로(중위 순회)
     * 4. 살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가 더미 노드가 아니면 1 추가
     * 5. 2진수를 10진수로 변환
     *
     *
     * e.g)
     * [7, 42, 5]
     * 7    -> 111          -> 1
     * 42   -> 0101010      -> 1
     * 5    -> 101 X        -> 0
     *
     * 10   -> 1010
     * 11
     */
    static class Solution {
        public int[] solution(long[] numbers) {
            List<Integer> answer = new ArrayList<>();

            /**
             * 1. 포화 이진트리로 만든다.
             * 2. 0을 제거했을 때 2진 트리가 되는지 확인
             */
            for(int i = 0; i < numbers.length; i++) {
                // 2진수로 변환
                String binaryString = Long.toBinaryString(numbers[i]);
                // 포화 이진트리 생성
                String fullBinary = createFullBinary(binaryString);

                if(isBinaryTree(fullBinary)) {
                    answer.add(1);
                } else {
                    answer.add(0);
                }
            }

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        // 포화 이진트리 생성
        private String createFullBinary(String binaryString) {
            int length = binaryString.length(); // 42 -> 101010
            int nodeCount = 1;
            int level = 1;

            while (length > nodeCount) {
                level *= 2;
                nodeCount += level;
            }

            int addZeroCount = nodeCount - length;
            StringBuilder sb = new StringBuilder(binaryString);
            for(int i = 0; i < addZeroCount; i++) {
                sb.insert(0, "0");
            }
            return sb.toString();
        }

        private boolean isBinaryTree(String binaryString) {
            int len = binaryString.length();
            // 리프노드인 경우
            if(binaryString.length() == 0) return true;

            int root = len / 2;
            String left = binaryString.substring(0, root);
            String right = binaryString.substring(root + 1);

            // 루트 노드가 0이라면 서브 트리도 모두 0
            if(binaryString.charAt(root) == '0') {
                return isZeroTree(binaryString.substring(0, root)) && isZeroTree(binaryString.substring(root + 1));
            }

            // 루트 노드가 1이라면 서브 트리 검사
            return isBinaryTree(left) && isBinaryTree(right);
        }

        private boolean isZeroTree(String binaryString) {
            int len = binaryString.length();
            if(binaryString.length() == 0) return true;

            int root = len / 2;
            String left = binaryString.substring(0, root);
            String right = binaryString.substring(root + 1);

            if(binaryString.charAt(root) == '1') {
                return false;
            }

            return isZeroTree(left) && isZeroTree(right);
        }
    }
}
