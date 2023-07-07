import java.util.*;

public class 호텔_방_배정 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long[] s = solution.solution(10, new long[]{1, 3, 4, 1, 3, 1});
        Arrays.stream(s).forEach(System.out::println);
    }

    /**
     * 1. 한 번에 한명씩 신청한 순서대로 방 배정(큐)
     * 2. 고객은 원하는 방번호 제출
     * 3. 방이 비어있으면 즉시 배정
     * 4. 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 번호가 가장 작은 방 배정
     */
    static class Solution {

        static List<Long> result = new ArrayList<>();
        static Map<Long, Long> roomMap = new HashMap<>();

        public long[] solution(long k, long[] room_number) {

            for(int i = 0; i < room_number.length; i++) {
                result.add(findEmptyRoom(room_number[i]));
            }

            return result.stream().mapToLong(Long::longValue).toArray();
        }

        private long findEmptyRoom(long roomNumber) {

            // 방이 비어있는 경우
            if(!roomMap.containsKey(roomNumber)) {
                roomMap.put(roomNumber, roomNumber + 1);
                return roomNumber;
            }

            // 방이 비어있지 않은 경우
            long nextRoomNumber = roomMap.get(roomNumber);
            long emptyRoomNumber = findEmptyRoom(nextRoomNumber);
            roomMap.put(roomNumber, emptyRoomNumber); // 빈방 번호를 넣어준다.

            return emptyRoomNumber;
        }
    }
}