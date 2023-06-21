package algorithm_challenge.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class 합승_택시_요금 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(7, 3, 4, 1, new int[][]{
                {5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}
        }));
    }

    static List<Path>[] paths;
    // 지점 갯수 n은 3이상 200이하인 자연수
    // 요금은 1 이상 100_000 이하인 자연수
    static int INF = 100_000 * 200 + 1;
    static class Solution {

        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = INF;

            /**
             * 다익스트라 알고리즘을 사용
             * 1. 출발점 -> x
             * 2. x -> a
             * 3. x -> b
             */

            // 인접리스트 초기화
            paths = new ArrayList[n + 1];
            for(int i = 1; i < paths.length; i++) {
                paths[i] = new ArrayList<>();
            }

            for(int i = 0; i < fares.length; i++) {
                int start = fares[i][0];
                int end = fares[i][1];
                int value = fares[i][2];

                // 양방향
                paths[start].add(new Path(end, value));
                paths[end].add(new Path(start, value));
            }

            int[] dijkstra1 = dijkstra(n, s); // s에서 모든 위치까지 최단거리 생성
            int[] dijkstra2 = dijkstra(n, a); // a에서 모든 위치까지 최단거리 생성
            int[] dijkstra3 = dijkstra(n, b); // b에서 모든 위치까지 최단거리 생성

            // 시작점(s, a, b) 에서 모든 위치까지 최단거리를 각각 구한다.
            for(int i = 1; i < n+1; i++) {
                // 합승할 수 있는 지점에서 최단거리를 구한다.
                answer = Math.min(dijkstra1[i] + dijkstra2[i] + dijkstra3[i], answer);
            }

            return answer;
        }

        private int[] dijkstra(int n, int start) {
            boolean[] visited = new boolean[n+1];
            // 최단거리 배열 초기화
            int[] shortest = new int[n + 1];
            for(int i = 1; i < shortest.length; i++) {
                shortest[i] = INF;
            }

            // 가중치를 기준으로 오름차순 정렬
            Queue<Path> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.value, o2.value));
            shortest[start] = 0;
            pq.offer(new Path(start, 0));

            while (!pq.isEmpty()) {
                Path poll = pq.poll();
                int node = poll.node;

                if(!visited[node]) {
                    visited[node] = true;
                    for (Path p : paths[node]) {
                        int newNode = p.node;
                        int newValue = p.value;

                        shortest[newNode] = Math.min(shortest[node] + newValue, shortest[newNode]);
                        pq.offer(new Path(newNode, shortest[newNode]));
                    }
                }
            }

            return shortest;
        }
    }

    static class Path {
        private int node;
        private int value;

        public Path(int node, int value) {
            this.node = node;
            this.value = value;
        }
    }
}
