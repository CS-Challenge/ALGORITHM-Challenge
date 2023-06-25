package algorithm_challenge.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class 보석_쇼핑 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}));
    }

    static class Solution {
        /**
         * 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
         * 선택한 보석은 연속적이어야 한다.
         * 앞에서부터 차례대로 선택한 보석을 포기할 때는 가장 먼저 선택한 보석부터 포기해야함.
         * 따라서 큐를 사용하면 차례대로 포기 가능
         *
         * 1. 보석의 종류를 set으로 표현
         * 2. 보석의 종류별 갯수를 map으로 표현
         * 3. 어피치가 선택한 보석 리스트를 Queue로 표현
         * 3. loop 순회하면서 구간을 구한다.
         */
        public int[] solution(String[] gems) {
            int[] answer = {};
            Set<String> gemSet = new HashSet<>(Arrays.asList(gems));
            Map<String, Integer> gemsMap = new HashMap<>();
            Queue<String> gemQueue = new ArrayDeque<>(); // 어피치가 선택한 보석 리스트

            int start = 0; // 선택한 보석 리스트의 시작 지점
            int end = Integer.MAX_VALUE; // 선택한 보석 리스트의 끝 지점
            // 최종적으로 값을 리턴할 때 사용하는 변수,
            // 선택한 보석들이 모든 종류의 보석을 포함하고,
            // selectGems의 값이 최솟값인 경우의 시작 지점을 저장
            int startPoint = 0;

            for(String gem : gems) {
                gemsMap.put(gem, gemsMap.getOrDefault(gem, 0) + 1);
                gemQueue.offer(gem);

                while (true) {
                    String targetGem = gemQueue.peek();
                    System.out.println(gemQueue);
                    System.out.println(gemsMap);

                    // 보석이 1개 이하이면 반드시 쇼핑백에 포함되어야 한다.
                    if(gemsMap.get(targetGem) <= 1) {
                        break;
                    }

                    // 보석이 2개 이상이면
                    gemQueue.poll(); // 보석 제거
                    start++; // 시작 지점 증가(제거 횟수가 보석 시작 위치)
                    gemsMap.put(targetGem, gemsMap.get(targetGem) - 1); // 보석 갯수 감소
                }

                // 모든 종류의 보석을 다 갖고 있고, 현재 보석리스트의 사이즈보다 end가 크면
                if(gemsMap.size() == gemSet.size() && end > gemQueue.size()) {
                    startPoint = start;
                    end = gemQueue.size(); // 큐 사이즈가 현재 보석 리스트의 끝점
                }

                System.out.println("start="+start+", startPoint="+startPoint+", end="+end);
                System.out.println("=====================================================");
            }


            return new int[]{startPoint + 1, startPoint + end};
        }
    }
}
