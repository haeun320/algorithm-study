import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			list.add(new int[] { x, y });
		}
		list.sort((list1, list2) -> { // x 순으로 정렬.
			if (list1[0] == list2[0])
				return list1[1] - list2[1];
			return list1[0] - list2[0];
		});
		int left = list.get(0)[0], right = list.get(0)[1], rst = 0;
		for (int[] i : list) {
			if (left <= i[0] && i[0] <= right) // 오른쪽으로 확장.
				right = Math.max(right, i[1]);
			else if (right <= i[0]) { // 끊기면 길이 저장하고 범위 이동.
				rst += right - left;
				left = i[0];
				right = i[1];
			}
		}
		rst += right - left;
		System.out.println(rst);
	}
}