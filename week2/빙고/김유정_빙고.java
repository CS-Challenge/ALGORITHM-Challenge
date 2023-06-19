package week2.빙고;

import java.util.Scanner;

public class 김유정_빙고 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[5][5];
        boolean[][] removeStatus = new boolean[5][5];
        int[][] boardPos = new int[26][2];
        int[] removeNumArr = new int[25];

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                int num = scanner.nextInt();
                board[r][c] = num;
                boardPos[num] = new int[]{r, c};
            }
        }

        for (int i = 0; i < 25; i++) {
            int removeNum = scanner.nextInt();
            removeNumArr[i] = removeNum;
        }

        int answer = 0;
        for (int i = 0; i < 25; i++) {
            int removeNum = removeNumArr[i];
            int[] pos = boardPos[removeNum];
            removeStatus[pos[0]][pos[1]] = true;
            if (getBingoCnt(removeStatus) >= 3) {
                answer = i + 1;
                break;
            }
        }
        System.out.println(answer);
    }

    public static int getBingoCnt(boolean[][] removeStatus) {
        // 가로
        boolean isAllRemoved;
        int bingoCnt = 0;

        for (int r = 0; r < 5; r++) {
            isAllRemoved= true;
            for (int c = 0; c < 5; c++) {
                if (!removeStatus[r][c]) {
                    isAllRemoved = false;
                    break;
                }
            }
            if (isAllRemoved) {
                bingoCnt++;
            }
        }
        // 세로
        for (int c = 0; c < 5; c++) {
            isAllRemoved = true;
            for (int r = 0; r < 5; r++) {
                if (!removeStatus[r][c]) {
                    isAllRemoved = false;
                    break;
                }
            }
            if (isAllRemoved) {
                bingoCnt++;
            }
        }
        // 대각선
        isAllRemoved = true;
        for (int r = 0; r < 5; r++) {
            if (!removeStatus[r][r]) {
                isAllRemoved = false;
                break;
            }
        }
        if (isAllRemoved) {
            bingoCnt++;
        }
        isAllRemoved = true;
        for (int r = 0; r < 5; r++) {
            if (!removeStatus[4 - r][r]) {
                isAllRemoved = false;
                break;
            }
        }
        if (isAllRemoved) {
            bingoCnt++;
        }
        return bingoCnt;
    }
}
