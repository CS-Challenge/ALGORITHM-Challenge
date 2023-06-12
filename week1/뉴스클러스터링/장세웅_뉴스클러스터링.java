package algorithm_challenge.programmers;

import java.util.*;

public class 뉴스_클러스터링 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution("E=M*C^2", "e=m*c^2");
        System.out.println(solution1);
    }


    /**
     * "블라인드 전형", "코딩 테스트"
     *
     * 자카드 유사도
     * 두 집합의 교집합 크기를 합집합 크기로 나눈 값
     * A={1,2,3}, B={2,3,4}  -> 2/4 = 0.5
     * A,B 모두 공집합일 경우 1로 정의
     *
     * A={1,1,1}, B={1,1,1,1,1}
     * 다중집합 A = {1, 1, 2, 2, 3}, 다중집합 B = {1, 2, 2, 4, 5}라고 하면,
     * 교집합 A ∩ B = {1, 2, 2}, 합집합 A ∪ B = {1, 1, 2, 2, 3, 4, 5}가 되므로, 자카드 유사도 J(A, B) = 3/7, 약 0.42가 된다.
     *
     * FRANCE, FRENCH
     * A= {FR, RA, AN, NC, CE}, B={FR, RE, EN, NC, CH}
     * 교집합={FR, NC}, 합집합={FR, RA, AN, NC, CE, RE, EN, CH} 2/8 = 0.25
     */
    static class Solution {
        public int solution(String str1, String str2) {
            int answer = 0;

            // 영문자로 된 글자쌍만 유효
            // 공백, 숫자, 특수문자 제외
            // ab+ -> ab
            // 정답에 65536을 곱하고 정수부만 출력

            /**
             * 1. 문자열을 집합으로 변환
             * 2. 교집합, 합집합을 구한다.
             * 3. 자카드검사를 실행한다.
             */

            String s1 = str1.toUpperCase();
            String s2 = str2.toUpperCase();

            // 문자열 집합 생성
            List<String> arr1 = getArr(s1);
            List<String> arr2 = getArr(s2);

            // 순차적으로 비교를 위해 정렬
            Collections.sort(arr1);
            Collections.sort(arr2);

            // 교집합, 합집합
            List<String> same = new ArrayList<>();
            List<String> sum = new ArrayList<>();
            for(String s : arr1) {
                // 교집합은 같은것에 대해서 min
                if(arr2.remove(s)) {
                    same.add(s);
                }
                // 합집합은 같은것에 대해서 max
                sum.add(s);
            }
            // 합집합
            for(String s : arr2) {
                sum.add(s);
            }

            if(sum.size() == 0)  return  65536;

            float result = (float) same.size() / sum.size() * 65536;

            return (int) result;
        }

        private List<String> getArr(String s1) {
            List<String> arr = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            char[] chars1 = s1.toCharArray();

            for(int i = 0; i < chars1.length; i++) {
                // 알파벳만 찾는다.
                if(Character.isAlphabetic(chars1[i])) {
                    sb.append(chars1[i]);
                    // 문자열의 길이
                    int length = sb.toString().length();
                    // 문자열의 길이가 짝수이면
                    if (length % 2 == 0) {
                        arr.add(sb.toString());
                        sb.setLength(0);
                        i--;
                    }
                }
                // 알파벳이 아니면
                else {
                    sb.setLength(0);
                }
            }

            return arr;
        }
    }
}
