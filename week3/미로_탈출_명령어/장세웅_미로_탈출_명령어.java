package algorithm_challenge.programmers;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <a href="https://velog.io/@juhwanheo/2023-Kakao-Blind-%EB%AF%B8%EB%A1%9C-%ED%83%88%EC%B6%9C-%EB%AA%85%EB%A0%B9%EC%96%B4-Java">참고</a>
 */
public class 미로_탈출_명령어 {


    public static void main(String[] args) {
        Solution s = new Solution();
        String result = s.solution(3, 4, 2, 3, 3, 1, 5);
        System.out.println(result);
    }

    static class Solution {

        /**
         * 1. 격자 밖 불가
         * 2. 이동거리가 k, 같은 격자 2번 이상 방문 가능
         * 3. 문자열이 사전 순으로 가장 빠른 경로로 탈출
         */

        static int[] moveX = {1, -1, 0, 0};
        static int[] moveY = {0, 0, 1, -1};
        static String[] move = {"d", "u", "r", "l"};
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            String bfs = bfs(n, m, x, y, r, c, k);

            return bfs;
        }

        private String bfs(int n, int m, int x, int y, int r, int c, int k) {
            Queue<Path> queue = new PriorityQueue<>((o1, o2) -> (o1.path.compareTo(o2.path)));
            queue.offer(new Path(x, y, new StringBuilder(), 0));
            boolean[][][] visited = new boolean[n+1][m+1][k+1];
            visited[x][y][0] = true;

            /**
             * k - (시작점 ~ 끝점의 최단거리)가 짝수가 아니면 애초에 불가능하다.
             * 그 이유는 중간에 다른곳을 들렸다가 되돌아와야 하기 때문이다.
             * 되돌아 오는것은 짝수만큼 소비된다.
             * k - (시작점 ~ 끝점의 최단거리) 식이 다른곳을 들렸다가 되돌아 오는 경로의 수
             */
            int shortestPath = Math.abs(x - r) + Math.abs(y - c);
            if(k < shortestPath || (k - shortestPath) % 2 == 1) return "impossible";

            while (!queue.isEmpty()) {
                Path poll = queue.poll();

                if(poll.depth == k && poll.x == r && poll.y == c) {
                    return poll.path.toString();
                }

                for(int i = 0; i < 4; i++) {
                    int newX = moveX[i] + poll.x;
                    int newY = moveY[i] + poll.y;

                    // 범위 체크
                    if(newX < 1 || newY < 1 || newX >= n+1 || newY >= m+1) continue;

                    if(poll.depth < k && !visited[newX][newY][poll.depth + 1]) {
                        visited[newX][newY][poll.depth + 1] = true;
                        queue.offer(new Path(newX, newY, new StringBuilder(poll.path).append(move[i]), poll.depth + 1));
                    }
                }
            }

            return "impossible";
        }

        static class Path {
            private int x;
            private int y;
            private StringBuilder path;
            private int depth;

            public Path(int x, int y, StringBuilder path, int depth) {
                this.x = x;
                this.y = y;
                this.path = path;
                this.depth = depth;
            }
        }
    }
}
