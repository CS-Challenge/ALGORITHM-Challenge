package week1.숫자야구;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 김유정_숫자야구 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution());
    }
}

class Solution {
    public List<String> permutationList = new ArrayList<>();
    public int solution() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] answer = scanner.nextLine().split(" ");
            answerList.add(new Answer(answer));
        }

        permutation();
        int candidateCnt = 0;
        for (String candidate : permutationList) {
            boolean isCandidate = true;
            for (Answer answer : answerList) {
                // 후보 숫자와 질문 숫자 비교해서 스트라이크와 볼 개수 세기
                int sCnt = 0;
                int bCnt = 0;
                String[] candidateArr = candidate.split("");
                String[] guessNumArr = answer.getGuessNum().split("");
                for (int i = 0; i < 3; i++) {
                    if (candidateArr[i].equals(guessNumArr[i])) {
                        sCnt++;
                    } else if (answer.getGuessNum().contains(candidateArr[i])) {
                        bCnt++;
                    }
                }
                // 스트라이크와 볼 개수가 다르다면 정답이 될 수 없으므로 isCandidate를 false로 업데이트
                if (sCnt != answer.getStrikeNum() || bCnt != answer.getBallNum()) {
                    isCandidate = false;
                }
            }
            if (isCandidate) {
                candidateCnt++;
            }
        }
        return candidateCnt;
    }

    public void permutation() {
        for (int f = 1; f < 10; f++) {
            for (int s = 1; s < 10; s++) {
                if (s != f) {
                    for (int t = 1; t < 10; t++) {
                        if (t != f && t != s) {
                            permutationList.add(Integer.toString(f) + Integer.toString(s) + Integer.toString(t));
                        }
                    }
                }
            }
        }
    }

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
}
