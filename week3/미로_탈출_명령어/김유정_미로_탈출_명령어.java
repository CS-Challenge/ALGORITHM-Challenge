package week3.미로_탈출_명령어;

import java.util.*;

public class 김유정_미로_탈출_명령어 {
    public static void main(String[] args) {
        System.out.println(new 미로탈출명령어_Solution().solution(3, 4, 2, 3, 3, 1, 5));
    }
}

class 미로탈출명령어_Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (isImpossible(x, y, r, c, k)) {
            return "impossible";
        }
        Map<Character, int[]> dirMap = new HashMap<>();
        dirMap.put('u', new int[]{-1, 0});
        dirMap.put('r', new int[]{0, 1});
        dirMap.put('d', new int[]{1, 0});
        dirMap.put('l', new int[]{0, -1});
        PriorityQueue<Candidate> candidateQueue = new PriorityQueue<>();
        candidateQueue.add(new Candidate("", x, y, k));
        while (!candidateQueue.isEmpty()) {
            Candidate candidate = candidateQueue.poll();
            System.out.printf("경로: %s, x: %d, y: %d, 남은 횟수: %d\n", candidate.getRoute(), candidate.getX(), candidate.getY(), candidate.getRemainCnt());
            if (candidate.getX() == r && candidate.getY() == c && candidate.getRemainCnt() == 0) {
                return candidate.getRoute();
            }
            for (char dir : new char[]{'d', 'l', 'r', 'u'}) {
                int nextX = candidate.getX() + dirMap.get(dir)[0];
                int nextY = candidate.getY() + dirMap.get(dir)[1];
                if (nextX < 1 || nextX > n || nextY < 1 || nextY > m) {
                    continue;
                }
                if (!isImpossible(nextX, nextY, r, c, candidate.getRemainCnt() - 1)) {
                    candidateQueue.add(new Candidate(candidate.getRoute() + dir, nextX, nextY, candidate.getRemainCnt() - 1));

                }
            }
            System.out.printf("    업데이트된 후보 목록: %s\n", candidateQueue.toString());
        }
        return "impossible";
    }

    private boolean isImpossible(int currX, int currY, int endX, int endY, int remainCnt) {
        int distance = Math.abs(endX - currX) + Math.abs(endY - currY);
        if (distance > remainCnt || (remainCnt - distance) % 2 != 0) {
            System.out.printf("currX: %d, currY: %d, remainCnt: %d, distance: %d\n", currX, currY, remainCnt, distance);
            return true;
        }
        return false;
    }
}

class Candidate implements Comparable<Candidate> {
    String route;
    int x;
    int y;
    int remainCnt;

    public Candidate(String route, int x, int y, int remainCnt) {
        this.route = route;
        this.x = x;
        this.y = y;
        this.remainCnt = remainCnt;
    }

    String getRoute() {
        return route;
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    int getRemainCnt() {
        return remainCnt;
    }

    @Override
    public int compareTo(Candidate o) {
        return this.route.compareTo(o.getRoute());
    }

    @Override
    public String toString() {
        return "Candadate{" + route + ", " + x + ", " + y + ", " + remainCnt + "}";
    }
}

