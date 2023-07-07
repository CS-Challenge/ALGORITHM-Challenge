package week4.치킨_배달;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.LinkedList;

public class 김유정_치킨_배달 {
    public static void main(String[] args) {
        new 김유정_치킨_배달_Solution().solution();
    }
}


class 김유정_치킨_배달_Solution {
    final int EMPTY = 0;
    final int HOUSE = 1;
    final int CHICKEN = 2;
    List<int[]> houseList = new ArrayList<>();
    List<int[]> chickenList = new ArrayList<>();
    Map<int[], PriorityQueue<ChickenDistance>> chickenDistanceMap = new HashMap<>(); // 각 집에서 모든 치킨 집까지의 거리가 담긴 Map(거리 순으로 정렬)

    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] board = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                board[r][c] = scanner.nextInt();
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == HOUSE) {
                    houseList.add(new int[]{r, c});
                } else if (board[r][c] == CHICKEN) {
                    chickenList.add(new int[]{r, c});
                }
            }
        }

        for (int[] house : houseList) {
            PriorityQueue<ChickenDistance> chickenDistanceQueue = new PriorityQueue<>();
            for (int[] chicken : chickenList) {
                chickenDistanceQueue.add(new ChickenDistance(chicken, house));
            }
            chickenDistanceMap.put(house, chickenDistanceQueue);
        }
        System.out.println(chickenDistanceMap.toString());

        boolean[] chickenRemoveStatus = new boolean[chickenList.size()];
    }

    private void removeChicken(boolean chickenRemoveStatus, int removeCnt, int targetCnt) {
        int distanceSum = 0;
        if (removeCnt == targetCnt) {
            for (PriorityQueue<ChickenDistance> value : chickenDistanceMap.values()) {
                distanceSum += value.poll().getDistance();
            }
        } else { // 목표하는 제거 개수가 될 때까지 치킨집을 제거해야함.
            for (int i = 0; i < chickenList.size(); i++) {
                if (chickenRemoveStatus[i]) {
                    continue;
                }
                chickenRemoveStatus[i] = true;
                removeCnt(chickenRemoveStatus, removeCnt + 1, targetCnt);
                chickenRemoveStatus[i] = false;
            }
        }
    }
}

class ChickenDistance implements Comparable<ChickenDistance> {
    int[] chickenPos;
    int distance;

    ChickenDistance(int[] chickenPos, int[] housePos) {
        this.chickenPos = chickenPos;
        this.distance = Math.abs(chickenPos[0] - housePos[0]) + Math.abs(chickenPos[1] - housePos[1]);
    }

    int[] getChickenPost() {
        return this.chickenPos;
    }
    
    int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(ChickenDistance o) {
        // TODO Auto-generated method stub
        return distance - o.getDistance();
    }

    @Override
    public String toString() {
        return "ChickenDistance(" + Arrays.toString(chickenPos) + ", " + distance + ")";

    }
}
