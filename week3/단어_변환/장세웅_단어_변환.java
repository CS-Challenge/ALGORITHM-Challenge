package algorithm_challenge.programmers;

import java.util.Arrays;
import java.util.Map;

public class 단어_변환 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    static class Solution {

        /**
         * 1. 한 번에 한 개의 알파벳만 바꿀 수 있다.
         * 2. words에 있는 단어로만 변환할 수 있다.
         *
         * e.g)
         * begin: "hit", target: "cog", words: "hot","dot","dog","lot","log","cog"
         * hit -> hot -> dot -> dog -> cog
         *
         * 최소 몇 단계 과정을 거치는지?
         */

        static int result = Integer.MAX_VALUE;
        static boolean[] visited;
        public int solution(String begin, String target, String[] words) {
            int answer = 0;

            // words에 target이 포함되었는지 확인
            if(!validationTarget(target, words)) return 0;

            visited = new boolean[words.length];
            dfs(begin, target, words, 0);

            return result;
        }

        private void dfs(String begin, String target, String[] words, int depth) {
            // 타겟 단어와 같으면
            if(begin.equals(target)) {
                // 최소 단계를 저장
                result = Math.min(result, depth);
                return;
            }

            for(int i = 0; i < words.length; i++) {
                String word = words[i];
                int diff = 0;
                for(int j = 0; j < begin.length(); j++) {
                    // 동일한 위치의 단어가 다른지 확인해야한다.
                    char b = begin.charAt(j);
                    char w = word.charAt(j);
                    if(b != w) diff++;
                }

                // 1개의 알파벳이 다르고, 해당 단어에 방문 이력이 없으면
                if(diff == 1 && !visited[i]) {
                    visited[i] = true;
                    dfs(word, target, words, depth + 1);
                    visited[i] = false; // 모든 단어에 방문해야하기 때문에 다시 방문이력을 삭제한다.
                }
            }
        }

        private static boolean validationTarget(String target, String[] words) {
            // words에 target이 포함되었는지 확인
            for(String word : words) {
                if(word.equals(target)) return true;
            }
            return false;
        }
    }
}
