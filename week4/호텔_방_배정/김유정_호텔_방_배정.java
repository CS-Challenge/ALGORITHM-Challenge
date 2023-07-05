package week4.호텔_방_배정;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class 김유정_호텔_방_배정 {
    public static void main(String[] args) {
        System.out.printf(Arrays.toString(new 김유정_호텔_방_배정_Solution().solution(10, new long[]{1, 3, 4, 1, 3, 1}))); // 결과: [1,3,4,2,5,6]
    }
}

class 김유정_호텔_방_배정_Solution {
    public long[] solution(long k, long[] roomNumber) {
        long[] answer = new long[roomNumber.length];
        Map<Long, Long> replaceRoomMap = new HashMap<>();
        for (int i = 0; i < roomNumber.length; i++) {
            long room = roomNumber[i];
            List<Long> visited = new ArrayList<>();
            while (replaceRoomMap.containsKey(room)) {
                visited.add(room);
                room = replaceRoomMap.get(room);
            }
            answer[i] = room;
            replaceRoomMap.put(room, room + 1);
            for (long visitedRoom : visited) {
                replaceRoomMap.replace(visitedRoom, room + 1);
            }
        }
        return answer;
    }
}
