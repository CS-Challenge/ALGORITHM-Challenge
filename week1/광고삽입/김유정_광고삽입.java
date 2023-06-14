// 통과 X

package week1.광고삽입;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 김유정_광고삽입 {
    public String solution(String playTime, String advTime, String[] logs) {
        int answerIdx = 0;
        int maxPrefix = 0;
        String answer = "";
        int playTimeSeconds = calTime(playTime);
        int advTimeSeconds = calTime(advTime);
        int[] timeStatus = new int[playTimeSeconds + 1];
        
        for (String log : logs) {
            String[] time = log.split("-");
            timeStatus[calTime(time[0])] += 1;
            timeStatus[calTime(time[1])] -= 1;
        }

        for (int i = 1; i < playTimeSeconds + 1; i++) {
            timeStatus[i] = timeStatus[i - 1] + timeStatus[i];
        }

        for (int start = 0; start < (playTimeSeconds - advTimeSeconds + 1); start++) {
            int prefix = IntStream.range(start, start + advTimeSeconds)
                                .reduce(0, (a, b) -> a + timeStatus[b]);
            if (prefix > maxPrefix) {
                answerIdx = start;
            }
        }

        return answer; // 누적 재생 시간이 많은 곳 중 가장 빠른 시작 시간
    }
    
    public int calTime(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) * 3600 + Integer.parseInt(splitTime[1]) * 60 + Integer.parseInt(splitTime[2]);
    }
}
