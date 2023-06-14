import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자_야구 {

    /**
     * 1. 123 ~ 987 까지 입력된 숫자의 strike, ball을 이용하여 비교한다.
     */
    static boolean[] checks = new boolean[988];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Game> games = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            games.add(new Game(number, strike, ball));
            checks[number] = true;
        }

        for(Game g : games) {
            int number = g.number;
            int strike = g.strike;
            int ball = g.ball;

            int digit1 = number % 100 % 10;
            int digit10 = number % 100 / 10;
            int digit100 = number / 100;

            for(int i = 123; i < checks.length; i++) {
                if(checks[i]) continue;
                // 1의 자리, 10의 자리, 100의 자리
                int tempDigit1 = i % 100 % 10;
                int tempDigit10 = i % 100 / 10;
                int tempDigit100 = i / 100;
                int tempStrike = 0;
                int tempBall = 0;

                // 0을 포함하거나, 숫자가 중복이면
                if(tempDigit1 == 0 || tempDigit10 == 0
                        || tempDigit1 == tempDigit10 || tempDigit10 == tempDigit100 || tempDigit100 == tempDigit1)
                {
                    checks[i] = true;
                    continue;
                }

                // 추측한 숫자와 위치가 동일하면 strike
                if(digit1 == tempDigit1) tempStrike++;
                if(digit10 == tempDigit10) tempStrike++;
                if(digit100 == tempDigit100) tempStrike++;

                // 추측한 숫자만 동일하면 ball
                if(digit1 == tempDigit10 || digit1 == tempDigit100) tempBall++;
                if(digit10 == tempDigit1 || digit10 == tempDigit100) tempBall++;
                if(digit100 == tempDigit1 || digit100 == tempDigit10) tempBall++;

                // 추측한 숫자의 strike와 ball이 다르면
                if(strike != tempStrike || ball != tempBall) checks[i] = true;
            }
        }

        int count = 0;
        for(int i = 123; i < checks.length; i++) {
            if(!checks[i]) count++;
        }

        System.out.print(count);
        br.close();
    }


    static class Game {
        private int number;
        private int strike;
        private int ball;

        public Game(int number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }
    }
}