package algorithm_challenge.programmers;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class 게임_맵_최단거리 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int result = s.solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        });
        System.out.println(result);
    }

    static class Solution {

        static int[] moveX = {1, -1, 0, 0};
        static int[] moveY = {0, 0, 1, -1};

        public int solution(int[][] maps) {

            /**
             * 1. 최단거리를 구하는 문제이기 때문에 bfs를 이용한다.
             * 2. 이때 거리를 기준으로 오름차순 정렬하는 우선순위 큐를 사용한다.
             */

            int bfs = bfs(maps);

            return bfs;
        }

        private int bfs(int[][] maps) {
            // 우선순위 큐 사용
            Queue<ROR> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.path, o2.path));
            queue.offer(new ROR(0, 0, 1));
            boolean[][] visited = new boolean[maps.length][maps[0].length];
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                ROR poll = queue.poll();
                int x = poll.x;
                int y = poll.y;
                int path = poll.path;

                // 도착점에 도착했을때
                if(x == maps.length - 1 && y == maps[0].length - 1) return path;

                for (int i = 0; i < 4; i++) {
                    int newX = x + moveX[i];
                    int newY = y + moveY[i];

                    // 범위 체크
                    if (newX < 0 || newY < 0 || newX >= maps.length || newY >= maps[0].length) continue;

                    // 방문 이력이 없고, 벽이 아니면
                    if (!visited[newX][newY] && maps[newX][newY] != 0) {
                        visited[newX][newY] = true;
                        queue.offer(new ROR(newX, newY, path + 1));
                    }
                }
            }

            return -1;
        }

        static class ROR {
            private int x;
            private int y;
            private int path;

            public ROR(int x, int y, int path) {
                this.x = x;
                this.y = y;
                this.path = path;
            }
        }
    }
}
