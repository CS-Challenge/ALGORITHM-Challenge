package week1.전력망 둘로 나누기;

import java.util.*;

public class 김유정_전력망_둘로_나누기 {
    private Map<Integer, List<Integer>> connMap = new HashMap<>();
    
    public int solution(int n, int[][] wires) {
        int answer = n;
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            List<Integer> aValue = connMap.getOrDefault(a, new ArrayList<Integer>());
            List<Integer> bValue = connMap.getOrDefault(b, new ArrayList<Integer>());
            aValue.add(b);
            bValue.add(a);
            connMap.putIfAbsent(a, aValue);
            connMap.putIfAbsent(b, bValue);
        }
        for (int[] wire : wires) {
            boolean[] isVisitArr = new boolean[n + 1];
            int a = wire[0];
            int b = wire[1];
            isVisitArr[a] = true;
            isVisitArr[b] = true;
            int aResult = connectNode(a, 1, isVisitArr);
            int bResult = connectNode(b, 1, isVisitArr);
            answer = Math.min(answer, Math.abs(aResult - bResult));
        }
        
        return answer;
    }
    
    private int connectNode(int start, int cnt, boolean[] isVisitArr) {
        // System.out.printf("start: %d, cnt: %d, isVisitArr: %s\n", start, cnt, Arrays.toString(isVisitArr));
        for (int next : connMap.get(start)) {
            if (!isVisitArr[next]) {
                isVisitArr[next] = true;
                cnt = connectNode(next, cnt + 1, isVisitArr);
            }
        }
        return cnt;
    }
}
