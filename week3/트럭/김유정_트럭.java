package week3.트럭;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class 김유정_트럭 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int l = scanner.nextInt();
        int[] truckArr = new int[n];
        for (int i = 0; i < n; i++) {
            truckArr[i] = scanner.nextInt();
        }
        System.out.println(new TruckSolution().solution(n, w, l, truckArr));
    }
}

class TruckSolution {
    int solution(int n, int w, int l, int[] truckArr) {
        int answer = 0;
        int currWeight = 0;
        Queue<Integer> bridgeStatus = new LinkedList<>();
        for (int truck : truckArr) {
            while (currWeight + truck > l) {
                bridgeStatus.add(0);
                answer++;
                if (bridgeStatus.size() == w) {
                    currWeight -= bridgeStatus.poll();
                }
            }
            bridgeStatus.add(truck);
            currWeight += truck;
            answer++;
            // 다리가 모두 찼다면, 다음 트럭을 위해 비워주기
            if (bridgeStatus.size() == w) {
                currWeight -= bridgeStatus.poll();
            }
        }
        return answer + w;
    }
}
