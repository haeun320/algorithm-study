import java.io.*;
import java.util.*;

public class Main {
	static int paper[][], cnt[] = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		for (int i = 0; i < n; i++) { // 입력.
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());
		}

		cut(0, 0, n);

		System.out.println(cnt[0]);
		System.out.println(cnt[1]);
		System.out.println(cnt[2]);
	}

	static void cut(int r, int c, int size) {
		if (size == 1) { // 더 이상 자를 수 없는 경우.
			cnt[paper[r][c] + 1]++;
			return;
		}

		int num = paper[r][c];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (paper[r + i][c + j] != num) { // 잘라야 하는 경우.
					size /= 3; // 9분할.
					for (int x = 0; x < 3; x++) {
						for (int y = 0; y < 3; y++) {
							cut(r + x * size, c + y * size, size);
						}
					}
					return;
				}
			}
		}

		// 모두 같은 숫자면 자르지 않아도 됨. 하나로 결합.
		cnt[num + 1]++;
	}

}