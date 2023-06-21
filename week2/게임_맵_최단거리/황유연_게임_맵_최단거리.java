import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 1});

        int[][] visited = new int[n][m];
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int y = curr[0];
            int x = curr[1];
            int d = curr[2];

            if (y == n - 1 && x == m - 1) {
                return d;
            }
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < n && 0 <= nx && nx < m && visited[ny][nx] != 1 && maps[ny][nx] == 1) {
                    visited[ny][nx] = 1;
                    q.add(new int[]{ny, nx, d + 1});
                }
            }

        }

        return -1;
    }


}
