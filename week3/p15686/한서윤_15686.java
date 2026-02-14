import java.io.*;
import java.util.*;

public class Main {
	private static int m, pick[], homeCnt, chickenCnt, minVal;
	private static ArrayList<int[]> home, chicken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		String[] map = new String[n];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		for (int i = 0; i < n; i++) { // 입력.
			map[i] = br.readLine();
			for (int j = 0; j < n * 2; j += 2) {
				if (map[i].charAt(j) == '1')
					home.add(new int[] { i, j / 2 });
				else if (map[i].charAt(j) == '2')
					chicken.add(new int[] { i, j / 2 });
			}
		}
		pick = new int[m];
		homeCnt = home.size();
		chickenCnt = chicken.size();
		minVal = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(minVal);
	}

	static void combi(int start, int cnt) { // 치킨집 고르기.
		if (cnt == m) {
			int sum = 0;
			for (int i = 0; i < homeCnt; i++) {
				int cal = Integer.MAX_VALUE, r = home.get(i)[0], c = home.get(i)[1];
				for (int j = 0; j < m; j++) {
					int dis = Math.abs(r - chicken.get(pick[j])[0]) + Math.abs(c - chicken.get(pick[j])[1]);
					cal = Math.min(cal, dis);
				}
				sum += cal;
			}
			minVal = Math.min(minVal, sum);
			return;
		}
		for (int i = start; i < chickenCnt; i++) {
			pick[cnt] = i;
			combi(i + 1, cnt + 1);
		}
	}
}