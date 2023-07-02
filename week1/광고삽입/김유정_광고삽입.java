// 통과 X

package week1.광고삽입;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 김유정_광고삽입 {
    public String solution(String playTime, String advTime, String[] logs) {
        int answerIdx = 0;
        int maxPrefix = 0;
        int playTimeSeconds = calTime(playTime);
        int advTimeSeconds = calTime(advTime);
        int[] timeStatus = new int[playTimeSeconds + 2];
        for (String log : logs) {
            String[] time = log.split("-");
            timeStatus[calTime(time[0])] += 1;
            timeStatus[calTime(time[1]) + 1] -= 1;
        }
        for (int i = 1; i < timeStatus.length; i++) {
            timeStatus[i] = timeStatus[i - 1] + timeStatus[i];
        }
        for (int start = 0; start < (playTimeSeconds - advTimeSeconds + 1); start++) {
            int prefix = IntStream.range(start, start + advTimeSeconds)
                                .reduce(0, (a, b) -> a + timeStatus[b]);
            if (prefix > maxPrefix) {
                answerIdx = start;
                maxPrefix = prefix;
            }
        }
        int hour = answerIdx / 3600;
        int min = (answerIdx - hour * 3600) / 60;
        int sec = answerIdx - (hour * 3600) - (min * 60);
        String hourStr = (hour < 10) ? "0" + hour : Integer.toString(hour);
        String minStr = (min < 10) ? "0" + min : Integer.toString(min);
        String secStr = (sec < 10) ? "0" + sec : Integer.toString(sec);
        return hourStr + ":" + minStr + ":" + secStr; // 누적 재생 시간이 많은 곳 중 가장 빠른 시작 시간
    }
    
    public int calTime(String time) {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]) * 3600 + Integer.parseInt(splitTime[1]) * 60 + Integer.parseInt(splitTime[2]);
    }
}
