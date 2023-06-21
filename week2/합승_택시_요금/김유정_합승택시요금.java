package week2.합승_택시_요금;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 김유정_합승택시요금 {
    List<String> aRouteList = new ArrayList<>();
    List<String> bRouteList = new ArrayList<>();
    Map<Integer, List<int[]>> connMap = new HashMap<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        for (int[] fare : fares) {
            List<int[]> firstValue = connMap.getOrDefault(fare[0], new ArrayList<>());
            List<int[]> secondValue = connMap.getOrDefault(fare[1], new ArrayList<>());
            firstValue.add(new int[]{fare[1], fare[2]});
            secondValue.add(new int[]{fare[0], fare[2]});
            connMap.putIfAbsent(fare[0], firstValue);
            connMap.putIfAbsent(fare[1], secondValue);
        }
        boolean[] visited = new boolean[n + 1];
        visited[s] = true;
        buildRoute(s, Integer.toString(s), visited, a, b);
        
        Collections.sort(aRouteList);
        Collections.sort(bRouteList);
        
        System.out.println("aRouteList: " + aRouteList.toString());
        System.out.println("bRouteList: " + bRouteList.toString());
        
        boolean isCommonRoute;
        int commonFee = 0;
        for (String aRoute : aRouteList) {
            isCommonRoute = true;
            for (String bRoute : bRouteList) {
                for (int i = 1; i < Math.min(aRouteList.size(), bRouteList.size()); i++) {
                    if (aRoute.charAt(i) == bRoute.charAt(i)) {
                        commonFee += aRoute.charAt(i) - '0';
                    }
                }
            }
        }
        return answer;
    }
    
    public void buildRoute(int startNode, String currRoute, boolean[] visited, int a, int b) {
        System.out.println("startNode: " + startNode);
        System.out.println("currRoute: " + currRoute.toString());
        
        if (startNode == a) {
            aRouteList.add(currRoute);
        } else if (startNode == b) {
            bRouteList.add(currRoute);
        } else {
            try {
                for (int[] next : connMap.get(startNode)) {
                    int nextNode = next[0];
                    int fee = next[1];
                    if (!visited[nextNode]) {
                        visited[nextNode] = true;
                        buildRoute(nextNode, currRoute + Integer.toString(nextNode), visited, a, b);
                        visited[nextNode] = false;
                    }
                }
            } catch(Exception e) {
                System.out.println(e);
            }
        }
        
    }
}
