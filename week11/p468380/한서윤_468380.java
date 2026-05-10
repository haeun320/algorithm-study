import java.io.*;
import java.util.*;

class Solution {
	public long[] solution(int[] arr, long l, long r) {
		long[] answer = new long[2];
		int n = arr.length;
        
        long[] idx = new long[n]; // idx[i]: arr[i-1]인 시작 인덱스 저장.
		long[] sum = new long[n]; // sum[i]: 값이 arr[i-1]인 인덱스까지의 누적합.
		for (int i = 1; i < n; i++) {
            idx[i] = idx[i-1] + arr[i-1];
            // 이거 캐스팅 안 해서 계속 틀림 ...
			sum[i] = sum[i-1] + (long) arr[i-1] * arr[i-1];
        }
        
        // l번째 원소부터 r번째 원소까지의 합 = r번째 원소까지의 합 - (l-1)번째 원소까지의 합
        answer[0] = getSum(r, arr, idx, sum) - getSum(l-1, arr, idx, sum);

        // 옆으로 밀면서 만족하는지 확인.
        long len = r - l + 1, size = idx[n-1] + arr[n-1], i = 0;
		while (i <= size - len) {
            int startBlock = findBlock(i, arr, idx, sum);
            int endBlock = findBlock(i + len, arr, idx, sum);
            long curSum = getSum(i + len, arr, idx, sum) - getSum(i, arr, idx, sum); // 현재 구간의 합.
            
            long startDis = idx[startBlock] + (long) arr[startBlock] - i - 1;
            long endDis = idx[endBlock] + (long) arr[endBlock] - (i + len);
            long dis = Math.min(startDis, endDis); // dis만큼 밀 때 까지 결과가 같음.
            
            long diff = arr[endBlock] - arr[startBlock]; // 나가는 값과 들어오는 값의 차이.
            
            if(diff == 0) { // 값이 일정한 경우.
                if (curSum == answer[0]) // 현재 합이 정답인 경우.
                    answer[1] += dis + 1;
            }
            else { // 합이 일정하게 변하는 경우.
                // curSum + diff * k = answer[0]을 만족시키는 k 값 찾기.
                long target = answer[0] - curSum; // 목표를 맞추기 위해 필요한 값.
                if(target % diff == 0) {
                    target /= diff;
                    if(target >= 0 && target <= dis)
                        answer[1]++;
                }
            }
            i += dis+1;
	    }
		return answer;
    }
    
    // findBlock(): target이 포함된 블록. (0 ~ n-1).
    int findBlock(long target, int[] arr, long[] idx, long[] sum) {
        // idx[i] < target 을 만족하는 최대 i 찾기.
        int start = 0, end = arr.length-1, find = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(idx[mid] <= target) {
                find = mid;
                start = mid + 1;
            }
            else 
                end = mid - 1;
        }
        return find;
    }
    
    // getSum(): target번째 원소 까지의 합.
    long getSum(long target, int[] arr, long[] idx, long[] sum) {
        int block = findBlock(target, arr, idx, sum);
        return sum[block] + (long) arr[block] * (target - idx[block]);
    }
}