import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), maxVal = 0;
		int[][] v = new int[n][m];

		for (int i = 0; i < n; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				v[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 직사각형 형태로 범위 정하고 범위 내에서 모양 만들기.
		for (int i = 0; i < n; i++) // 가로4 * 세로1.
			for (int j = 0; j < m - 3; j++)
				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i][j + 2] + v[i][j + 3]);

		for (int i = 0; i < n - 3; i++) // 가로1 * 세로4.
			for (int j = 0; j < m; j++)
				maxVal = Math.max(maxVal, v[i][j] + v[i + 1][j] + v[i + 2][j] + v[i + 3][j]);

		for (int i = 0; i < n - 1; i++) // 정사각형. 가로2 * 세로2.
			for (int j = 0; j < m - 1; j++)
				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i + 1][j] + v[i + 1][j + 1]);

		for (int i = 0; i < n - 2; i++) { // 가로2 * 세로3.
			for (int j = 0; j < m - 1; j++) {
				// 세로 3칸 고정하고 그 왼쪽/오른쪽 칸에서 위, 중간, 아래 순으로 선택.
				int left = v[i][j] + v[i + 1][j] + v[i + 2][j], right = v[i][j + 1] + v[i + 1][j + 1] + v[i + 2][j + 1];
				maxVal = Math.max(maxVal, left + v[i][j + 1]);
				maxVal = Math.max(maxVal, left + v[i + 1][j + 1]);
				maxVal = Math.max(maxVal, left + v[i + 2][j + 1]);
				maxVal = Math.max(maxVal, right + v[i][j]);
				maxVal = Math.max(maxVal, right + v[i + 1][j]);
				maxVal = Math.max(maxVal, right + v[i + 2][j]);
				// 번개 모양.
				maxVal = Math.max(maxVal, v[i][j] + v[i + 1][j] + v[i + 1][j + 1] + v[i + 2][j + 1]);
				maxVal = Math.max(maxVal, v[i][j + 1] + v[i + 1][j] + v[i + 1][j + 1] + v[i + 2][j]);
			}
		}

		for (int i = 0; i < n - 1; i++) { // 가로3 * 세로2.
			for (int j = 0; j < m - 2; j++) {
				// 가로 3칸 고정하고 그 위/아래 칸에서 왼쪽, 가운데, 오른쪽 순으로 선택.
				int up = v[i][j] + v[i][j + 1] + v[i][j + 2], down = v[i + 1][j] + v[i + 1][j + 1] + v[i + 1][j + 2];
				maxVal = Math.max(maxVal, up + v[i + 1][j]);
				maxVal = Math.max(maxVal, up + v[i + 1][j + 1]);
				maxVal = Math.max(maxVal, up + v[i + 1][j + 2]);
				maxVal = Math.max(maxVal, down + v[i][j]);
				maxVal = Math.max(maxVal, down + v[i][j + 1]);
				maxVal = Math.max(maxVal, down + v[i][j + 2]);
				// 번개 모양.
				maxVal = Math.max(maxVal, v[i][j] + v[i][j + 1] + v[i + 1][j + 1] + v[i + 1][j + 2]);
				maxVal = Math.max(maxVal, v[i][j + 1] + v[i][j + 2] + v[i + 1][j + 1] + v[i + 1][j]);
			}
		}
		System.out.println(maxVal);
	}
}