// 못 품

package week1.숫자야구;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class 김유정_숫자야구 {
    public List<int[]> candidateList = new ArrayList<>();

    public int solution(String[] args) {
        // 동일한 자리 동일한 숫자 -> 스트라이크
        // 다른 자리 동일한 숫자 -> 볼
        List<Answer> answerList = new ArrayList<>();
        List<Integer> exceptNum = new ArrayList<>();
        Map<String, Integer> candidateMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String[] answerInfo = scanner.nextLine().split(" ");
            answerList.add(new Answer(answerInfo));
        }
        // 나올 수 있는 경우의 수 -> 0볼, 1볼, 2볼, 3볼, 0스, 1스, 2스, 3스, 1스 1볼
        for (Answer answer : answerList) {
            List<int[]> newCandidateList = new ArrayList<>();
            if (answer.getStrikeNum() == 3) {
                return 1;
            } else if (answer.getStrikeNum() == 2) {
                for (int i = 0; i < 3; i++) {
                    newCandidateList.add(new int[]{
                            (i == 2 ? -1 : 1) * answer.getGuessFirstNum(),
                            (i == 1 ? -1 : 1) * answer.getGuessSecondNum(),
                            (i == 0 ? -1 : 1) * answer.getGuessThirdNum()
                    });
                }
            } else if (answer.getStrikeNum() == 1) {
                for (int i = 0; i < 3; i++) {
                    newCandidateList.add(new int[]{
                            (i == 0 ? 1 : -1) * answer.getGuessFirstNum(),
                            (i == 1 ? 1 : -1) * answer.getGuessSecondNum(),
                            (i == 2 ? 1 : -1) * answer.getGuessThirdNum()
                    });
                }
            } else if (answer.getStrikeNum() == 0 && answer.getBallNum() == 0) {
                for (int i = 0; i < 3; i++) {
                    exceptNum.add(answer.getGuessNum().charAt(0) - '0');
                }
            } // 조건 미완성
        }

        return candidateList.size();
    }

    public void updateCandidateList(List<int[]> candidateList, List<int[]> compareCandidateList) {
        List<int[]> updateCandidateList = new ArrayList<>();
        // 겹치는 게 하나라도 있으면 후보
        for (int[] candidate : candidateList) {
            for (int[] compareCandidate : compareCandidateList) {
                boolean isSame = true;
                for (int i = 0; i < 3; i++) {
                    if (candidate[i] > 0 && compareCandidate[i] > 0 && candidate[i] == compareCandidate[i]) {
                    } else if (candidate[i] < 0 && compareCandidate[i] < 0) {
                        // TO DO
                    }
                }
                if (isSame) {
                    updateCandidateList.add(candidate);
                }
            }
        }
        candidateList = updateCandidateList;
    }

    class Answer {
        String guessNum;
        int strikeNum;
        int ballNum;

        Answer(String[] answer) {
            this.guessNum = answer[0];
            this.strikeNum = Integer.parseInt(answer[1]);
            this.ballNum = Integer.parseInt(answer[2]);
        }

        String getGuessNum() {
            return guessNum;
        }

        int getStrikeNum() {
            return strikeNum;
        }

        int getBallNum() {
            return ballNum;
        }

        int getGuessFirstNum() {
            return guessNum.charAt(0) - '0';
        }

        int getGuessSecondNum() {
            return guessNum.charAt(1) - '0';
        }

        int getGuessThirdNum() {
            return guessNum.charAt(2) - '0';
        }
    }
}
