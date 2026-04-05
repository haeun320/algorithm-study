package week7.p2503;

import java.io.*;
import java.util.*;

public class 윤소윤_2503 {
    static int N;
    static boolean [] check = new boolean[1000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());


        // 가능한 정답 넣기
        for(int i = 123; i < 1000; i++) {
            String str = Integer.toString(i);

            // 0이 포함된 경우 제외
            if(str.charAt(0)== '0' || str.charAt(1)=='0' || str.charAt(2)=='0') continue;

            // 모든 자릿수가 다르지 않은 경우
            if(str.charAt(0)==str.charAt(1) || str.charAt(0)==str.charAt(2) || str.charAt(1)==str.charAt(2)) continue;
            check[i] = true;
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int req = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int ans = 123; ans < 1000; ans++) {
                if(check[ans]) {
                    int ns = 0;
                    int nb = 0;

                    for(int first = 0; first < 3; first++) {
                        char req_split = Integer.toString(req).charAt(first);

                        for(int second = 0; second < 3; second++) {
                            char ans_split = Integer.toString(ans).charAt(second);

                            // 자릿수도 같고 값도 같다면 스트라이크
                            // 자릿수는 다른데 값은 같다면 볼
                            if(req_split==ans_split && first == second) ns++;
                            else if (req_split==ans_split && first != second) nb++;
                        }
                    }

                    // 입력값과 같다면 정답 가능성이 있음
                    if(ns == s && nb == b) check[ans] = true;
                    else check[ans] = false;
                }
            }
        }

        int ans = 0;
        for(int i = 123; i < 1000; i++) {
            if(check[i]) ans++;
        }
        System.out.println(ans);
    }
}
