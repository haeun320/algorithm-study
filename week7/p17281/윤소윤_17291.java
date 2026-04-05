package week7.p17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 윤소윤_17291 {
    static int N;
    static int [][] player; // N 번째 이닝에서 타자행동
    static boolean [] select;
    static int [] lineUp; // 타순
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        player = new int [N+1][10];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new boolean[10];
        lineUp = new int [10];

        // 4번타자 고정
        select[4] = true;
        lineUp[4] = 1;

        ans = 0;
        perm(2);

        System.out.println(ans);
    }

    // 순열
    public static void perm(int num) {
        if(num == 10) {
            Play();
            return;
        }
        for(int i = 1; i <= 9; i++) {
            if (select[i]) continue;
            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }

    // 야구경기
    public static void Play() {
        int score = 0;
        int startPlayer = 1; // 현재 타순 위치

        for (int i = 1; i <= N; i++) {
            int out = 0;
            boolean[] base = new boolean[4];

            while (out < 3) {
                int hit = player[i][lineUp[startPlayer]];

                switch (hit) {
                    case 0:
                        out++;
                        break;

                    case 1:
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k == 3) score++;
                                else base[k + 1] = true;
                                base[k] = false;
                            }
                        }
                        base[1] = true;
                        break;

                    case 2:
                        for (int k = 3; k >= 1; k--) {
                            if (base[k]) {
                                if (k >= 2) score++;
                                else base[k + 2] = true;
                                base[k] = false;
                            }
                        }
                        base[2] = true;
                        break;

                    case 3:
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) {
                                score++;
                                base[k] = false;
                            }
                        }
                        base[3] = true;
                        break;

                    case 4:
                        for (int k = 1; k <= 3; k++) {
                            if (base[k]) {
                                score++;
                                base[k] = false;
                            }
                        }
                        score++;
                        break;
                }

                startPlayer++;
                if (startPlayer == 10) startPlayer = 1;
            }
        }

        ans = Math.max(ans, score);
    }
}
