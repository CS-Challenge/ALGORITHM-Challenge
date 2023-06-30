package algorithm_challenge.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트럭 {
    public static void main(String[] args) throws IOException {
        /**
         * 1. 트럭의 순서는 변경이 불가
         * 2. 트럭의 무게는 서로 다를 수 있다.
         * 3. 다리에는 w대 트럭만 동시에 올라갈 수 있다.
         * 4. 트럭은 하나의 단위시간에 하나의 단위길이만 이동 가능
         * 5. 다리 위에 올라가 있는 트럭들의 무게 합은 다리의 최대하중인 L보다 작거나 같아야 한다.
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭 갯수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 최대하중

        st = new StringTokenizer(br.readLine());
        Queue<Integer> trucks = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            int weight = Integer.parseInt(st.nextToken());
            trucks.offer(weight);
        }

        Queue<Integer> bridge = new ArrayDeque<>();
        for(int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        int time = 0;
        int bridgeWeight = 0;
        while (!bridge.isEmpty()) {
            time++;
            bridgeWeight -= bridge.poll();

            if(!trucks.isEmpty()) {
                // 현재 다리의 무게에서 트럭의 무게를 더한 값이 최대하중 보다 작거나 같으면
                if(trucks.peek() + bridgeWeight <= L) {
                    Integer weight = trucks.poll();
                    bridge.offer(weight); // 다리에 트럭을 넣는다.
                    bridgeWeight += weight; // 현재 다리 무게 갱신
                } else {
                    bridge.offer(0); // 다리에 아무것도 없음
                }
            }
        }

        System.out.print(time);
        br.close();
    }
}
