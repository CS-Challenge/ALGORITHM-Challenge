import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨_배달 {

    static int n;
    static int m;
    static int[][] map;
    static List<Path> home = new ArrayList<>();
    static List<Path> bbq = new ArrayList<>();
    static boolean[] open;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        /**
         * 도시의 치킨 거리가 가장 작게 될지?
         * dfs
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 집
                if(map[i][j] == 1) {
                    home.add(new Path(i, j));
                }
                // 치킨집
                else if (map[i][j] == 2) {
                    bbq.add(new Path(i, j));
                }
            }
        }

        open = new boolean[bbq.size()];
        dfs(0, 0);

        System.out.println(result);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if(depth == m) {
            int sum = 0;
            for(int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for(int j = 0; j < bbq.size(); j++) {
                    if(open[j]) {
                        int distance = Math.abs(home.get(i).x - bbq.get(j).x) + Math.abs(home.get(i).y - bbq.get(j).y);
                        temp = Math.min(distance, temp);
                    }
                }
                sum += temp;
            }
            result = Math.min(sum, result);
            return;
        }

        for(int i = start; i < bbq.size(); i++) {
            open[i] = true;
            dfs(i+1, depth+1);
            open[i] = false;
        }
    }


    static class Path {
        private int x;
        private int y;

        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
