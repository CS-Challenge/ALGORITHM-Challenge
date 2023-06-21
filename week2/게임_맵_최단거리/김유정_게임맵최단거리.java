package week2.게임_맵_최단거리;

import java.util.*;

public class 김유정_게임맵최단거리 {
    class Solution {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        final int EMPTY = 1;
        final int WALL = 0;
        
        public int solution(int[][] maps) {
            final int W = maps[0].length;
            final int H = maps.length;

            int[][] distanceMap = new int[H][W];
            distanceMap[0][0] = 1;
            
            Queue<int[]> que = new LinkedList<>();
            que.add(new int[]{0, 0});
            while (!que.isEmpty()) {
                int[] curr = que.poll();
                int currX = curr[0];
                int currY = curr[1];
                int currDistance = distanceMap[currX][currY];
                for (int i = 0; i < 4; i++) {
                    int nextX = currX + dx[i];
                    int nextY = currY + dy[i];
                    if (nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) {
                        continue;
                    }
                    if (nextX == H - 1 && nextY == W - 1) {
                        return currDistance + 1;
                    }
                    if (maps[nextX][nextY] == EMPTY && distanceMap[nextX][nextY] == 0) {
                        distanceMap[nextX][nextY] = currDistance + 1;
                        que.add(new int[]{nextX, nextY});
                    }
                }
            }
            return -1; // 도착할 수 없다면 -1 
        }
    }
}
