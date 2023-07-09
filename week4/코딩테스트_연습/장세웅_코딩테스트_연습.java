public class 코딩_테스트_연습 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //int result = solution.solution2(10, 10, new int[][]{{10,15,2,1,2},{20,20,3,3,4}});
        int result = solution.solution(10, 10, new int[][]{{10,15,2,1,2},{20,20,3,3,4}});
        //int result = solution.solution(0, 0, new int[][]{{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}});
        System.out.println(result);
    }

    static class Solution {
        public int solution(int alp, int cop, int[][] problems) {

            /**
             * dp 배열
             * index alp: 현재 알고력
             * index cop: 현재 코딩력
             * dp[index_alp][index_cop]: 현재 알고력, 코딩력에 도달하기 까지 소요시간
             *
             * 1. 알고력 증가
             * 2. 코딩력 증가
             * 3. 문제 풀기
             * 4. 문제의 최대 알고력, 코딩력에 도달했을 때의 최소시간
             */
            int answer = 0;

            int maxAlp = 0;
            int maxCop = 0;
            // dp 사이즈를 구하기 위해 최대 알고력, 코딩력을 구함
            for(int i = 0; i < problems.length; i++) {
                maxAlp = Math.max(problems[i][0], maxAlp);
                maxCop = Math.max(problems[i][1], maxCop);
            }

            // 현재 알고력, 코딩력이 최대 알고력, 코딩력보다 크거나 같을 경우 0을 반환(문제를 안풀어도 되기때문)
            if(alp >= maxAlp && cop >= maxCop) return 0;

            // 현재 알고력이 최대 알고력보다 크거나 같을 경우
            if(alp >= maxAlp) alp = maxAlp;
            // 현재 코딩력이 최대 코딩력보다 큰 경우
            if(cop >= maxCop) cop = maxCop;

            // dp 초기화
            int[][] dp = new int[maxAlp+2][maxCop+2];
            for(int i = alp; i <= maxAlp; i++) {
                for(int j = cop; j <= maxCop; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            dp[alp][cop] = 0;

            for(int i = alp; i <= maxAlp; i++) {
                for(int j = cop; j <= maxCop; j++) {

                    // 알고력 +1, 시간 +1
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                    // 코딩력 +1, 시간 +1
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);

                    for(int p = 0; p < problems.length; p++) {

                        int alpReq = problems[p][0];
                        int copReq = problems[p][1];
                        int alpRwd = problems[p][2];
                        int copRwd = problems[p][3];
                        int cost = problems[p][4];

                        // 이번 문제 풀이가 가능할 경우
                        if (i >= alpReq && j >= copReq) {
                            int nextAlp = Math.min(maxAlp, i + alpRwd);
                            int nextCop = Math.min(maxCop, j + copRwd);
                            // 풀었을 때 알고력, 코딩력을 소유할 때 최소시간 계산
                            dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                        }
                    }
                }
            }

            answer = dp[maxAlp][maxCop];

            return answer;
        }
    }

}