import java.util.*;

public class 전력망_둘로_나누기 {
    static boolean[] visited;
    static List<Integer>[] nodes;
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    static class Solution {
        /**
         * 전선을 끊어서 송전탑 갯수가 가능한 비슷하도록
         * 두 전력망이 갖고 있는 송전탑 갯수의 차이를 반환
         */
        public int solution(int n, int[][] wires) {
            // 인접리스트 생성
            nodes = new ArrayList[n+1];
            for(int i = 1; i < n+1; i++) {
                nodes[i] = new ArrayList<>();
            }

            for(int i = 0; i < wires.length; i++) {
                int node1 = wires[i][0];
                int node2 = wires[i][1];

                // 양방향
                nodes[node1].add(node2);
                nodes[node2].add(node1);
            }

            int min = Integer.MAX_VALUE;
            for(int i = 0; i < wires.length; i++) {
                int node1 = wires[i][0];
                int node2 = wires[i][1];

                // 양방향이라서 bfs를 2번 적용함
                // node1이 시작점이고, node1과 node2가 끊어졌을 때
                int bfs1 = bfs(n, node1, node2);
                // node2이 시작점이고, node1과 node2가 끊어졌을 때
                int bfs2 = bfs(n, node2, node1);

                // bfs1, bfs2의 차이가 가장 작은것이 정답
                min = Math.min(min, Math.abs(bfs1 - bfs2));
            }

            return min;
        }

        private int bfs(int n, int node1, int node2) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(node1);
            visited = new boolean[n+1];
            visited[node1] = true;
            int count = 0;

            while (!queue.isEmpty()) {
                Integer poll = queue.poll();

                for(Integer i : nodes[poll]) {
                    // 인접 노드가 끊어졌을 때
                    if(i != node2 && !visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                        count++;
                    }
                }
            }

            return count;
        }
    }
}
