import java.io.*;
import java.util.*;

public class 윤소윤_12891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String DNA = br.readLine();
		int num = 0;
		
		// 부분 문자열 최소 개수 (A, C, G, T)
		int [] count = new int [4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i <= S-P; i++) {
			String mid = DNA.substring(i, i + P);
			if(isOk(mid, count)) num++;
		}
		System.out.println(num);
	}
	
	private static boolean isOk(String mid, int [] count) {
		int [] cnt= count.clone();
		for(int i = 0; i < mid.length(); i++) {
			char c = mid.charAt(i);
			
			if (c == 'A') cnt[0]--;
            else if (c == 'C') cnt[1]--;
            else if (c == 'G') cnt[2]--;
            else if (c == 'T') cnt[3]--;
		}
		
		for (int i = 0; i < 4; i++) {
            if (cnt[i] > 0) return false;
        }
        return true;
	}
}
