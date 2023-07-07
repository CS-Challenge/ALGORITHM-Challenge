package week4.코딩_테스트_공부;

import java.util.*;

public class 김유정_코딩_테스트_공부 {
    
}

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int currAlp = alp;
        int currCop = cop;
        List<int[]> solveProblems = new ArrayList<>();
        for (int[] problem : problems) {
            System.out.printf("problem: %s, currAlp: %d, currCop: %d, \n", Arrays.toString(problem), currAlp, currCop);
            if (currAlp < problem[0] && currCop < problem[1]) {
                int[] ability = enhanceAbility(currAlp, currCop, problem, solveProblems);
                currAlp += ability[0];
                currCop += ability[1];
                answer += ability[2];
                // System.out.printf("    최종 | minTime: %d, enhanceAlp: %d, enhanceCop: %d, \n", minTime, enhanceAlp, enhanceCop);
                System.out.printf("    향상된 능력: %s\n", Arrays.toString(ability));
                
            }            
            currAlp += problem[2];
            currCop += problem[3];
            answer += problem[4];
            solveProblems.add(problem);
            System.out.println(solveProblems.toString());
        }
        return answer;
    }
    
    // 향상된 알고력, 향상된 코딩력, 필요한 최소 시간
    private int[] enhanceAbility(int currAlp, int currCop, int[] problem, List<int[]> solveProblems) {
        int needAlp = problem[0] - currAlp;
        int needCop = problem[1] - currCop;
        int minTime = needAlp + needCop;
        // System.out.printf("    problem: %s\n", Arrays.toString(problem));
        int resultAlp = needAlp;
        int resultCop = needCop;

        for (int[] solveProblem : solveProblems) {
            int enhanceAlpCnt = (solveProblem[2] == 0) ? 0 : (int) Math.ceil(needAlp / solveProblem[2]);
            int enhanceCopCnt = (solveProblem[3] == 0) ? 0 : (int) Math.ceil(needCop / solveProblem[3]);
            int needCnt = Math.max(enhanceAlpCnt, enhanceCopCnt);
            int enhanceAlp = problem[2] * needCnt;
            int enhanceCop = problem[3] * needCnt;
            System.out.printf("    solveProblem: %s\n", Arrays.toString(solveProblem));
            System.out.printf("    needAlp: %d, needCop: %d, enhanceAlpCnt: %d, enhanceCopCnt: %d\n", needAlp, needCop, enhanceAlpCnt, enhanceCopCnt);
            if (needCnt * problem[4] < minTime) {
                minTime = needCnt * problem[4];
                resultAlp = enhanceAlp;
                resultCop = enhanceCop;
            }
        }
        return new int[]{resultAlp, resultCop, minTime};
    }
}
