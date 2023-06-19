package algorithm_challenge.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 빙고 {
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, Bingo> bingoMap = new HashMap<>();
        map = new int[5][5];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                int number = Integer.parseInt(st.nextToken());
                map[i][j] = number;
                bingoMap.put(number, new Bingo(i, j));
            }
        }

        int count = 0;
        int bingoCount = 0;
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                count++;
                int number = Integer.parseInt(st.nextToken());
                Bingo bingo = bingoMap.get(number);
                map[bingo.x][bingo.y] = 0;

                // 가로
                if(findXBingo(bingo.x, bingo.y) == 5) {
                    bingoCount++;
                }
                // 세로
                if(findYBingo(bingo.x, bingo.y) == 5) {
                    bingoCount++;
                }
                // 대각선 /
                if(findDBingo1(bingo.x, bingo.y) == 5) {
                    bingoCount++;
                }
                // 대각선 \
                if(findDBingo2(bingo.x, bingo.y) == 5) {
                    bingoCount++;
                }

                // 3빙고 이상이면
                if(bingoCount >= 3) {
                    System.out.print(count);
                    br.close();
                    return;
                }
            }
        }

    }

    private static int findXBingo(int startX, int startY) {
        Queue<Bingo> queue = new ArrayDeque<>();
        visited = new boolean[5][5];
        queue.offer(new Bingo(startX, startY));
        visited[startX][startY] = true;

        int count = 1;
        int[] move = new int[]{1, -1};
        while (!queue.isEmpty()) {
            Bingo poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 2; i++) {
                int newX = x + move[i];

                if(newX < 0 || newX >= 5) continue;

                if(!visited[newX][y] && map[newX][y] == 0) {
                    visited[newX][y] = true;
                    queue.offer(new Bingo(newX, y));
                    count++;
                }
            }
        }

        return count;
    }

    private static int findYBingo(int startX, int startY) {
        Queue<Bingo> queue = new ArrayDeque<>();
        visited = new boolean[5][5];
        queue.offer(new Bingo(startX, startY));
        visited[startX][startY] = true;

        int count = 1;
        int[] move = new int[]{1, -1};
        while (!queue.isEmpty()) {
            Bingo poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 2; i++) {
                int newY = y + move[i];

                if(newY < 0 || newY >= 5) continue;

                if(!visited[x][newY] && map[x][newY] == 0) {
                    visited[x][newY] = true;
                    queue.offer(new Bingo(x, newY));
                    count++;
                }
            }
        }

        return count;
    }

    private static int findDBingo1(int startX, int startY) {
        Queue<Bingo> queue = new ArrayDeque<>();
        visited = new boolean[5][5];
        queue.offer(new Bingo(startX, startY));
        visited[startX][startY] = true;

        int count = 1;
        int[] moveX = new int[]{1, -1};
        int[] moveY = new int[]{-1, 1};
        while (!queue.isEmpty()) {
            Bingo poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 2; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if(newX < 0 || newX >= 5 || newY < 0 || newY >= 5) continue;

                if(!visited[newX][newY] && map[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    queue.offer(new Bingo(newX, newY));
                    count++;
                }
            }
        }

        return count;
    }

    private static int findDBingo2(int startX, int startY) {
        Queue<Bingo> queue = new ArrayDeque<>();
        visited = new boolean[5][5];
        queue.offer(new Bingo(startX, startY));
        visited[startX][startY] = true;

        int count = 1;
        int[] moveX = new int[]{1, -1};
        int[] moveY = new int[]{1, -1};
        while (!queue.isEmpty()) {
            Bingo poll = queue.poll();
            int x = poll.x;
            int y = poll.y;

            for(int i = 0; i < 2; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];

                if(newX < 0 || newX >= 5 || newY < 0 || newY >= 5) continue;

                if(!visited[newX][newY] && map[newX][newY] == 0) {
                    visited[newX][newY] = true;
                    queue.offer(new Bingo(newX, newY));
                    count++;
                }
            }
        }

        return count;
    }

    static class Bingo {
        private int x;
        private int y;

        public Bingo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
