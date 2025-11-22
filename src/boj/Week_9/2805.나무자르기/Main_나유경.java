import java.io.*;
import java.util.*;

// 나무자르기
// 이진탐색 : 톱날길이(H) 찾기 
public class Main {

	static int N, M; // 나무 수, 나무 길이
	static int low, high, max, result; // 톱날 최소높이, 톱날 최대 높이, 가장 높은 나무높이 저장, 결과
	static int[] talls; // 나무 높이

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		max = 0;
		talls = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			talls[i] = Integer.parseInt(st.nextToken());
			if (talls[i] > max)
				max = talls[i];
		}

//		톱날 높이 H 를 정한다.
//		각 나무의 높이 tall 에서 H 보다 높은 부분만 잘라서 얻을 수 있는 길이를 더한다.
//		그 총합이 M 이상이면 → 톱날을 더 올려본다.

		low = 0;
		high = max;
		result = 0;

		// 이진 탐색
		while (low <= high) {
			int mid = (low + high) / 2;
			long sum = 0;

			for (int h : talls) {
				if (h > mid)
					sum += (h - mid);
			}

			if (sum >= M) {
				result = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(result);

	}
}
