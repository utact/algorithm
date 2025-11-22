import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김용수 {
	static int oB;
	static int N, M, cnt, min, max, height;
	static int[] h = new int[257];
	static int[] map;
	static boolean isOk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oB = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				h[v]++;
				if (v < min)
					min = v;
				if (v > max)
					max = v;
			}
		} // 입력

		// 1. map[i][j]-- -> B++, cnt+=2
		// 2. B-- -> map[i][j]++, cnt++
		// 0 <= map[i][j] <=256

		// 채울수 있는게 채우는게 좋음
		// 채우는거 두번 = 제거하는것

		// 어디로 모일까? -> 평균, 최빈값,
		// 못정할거같음

		// 그럼 어차피 최소값과 최대값 사이에서 수렴하는 값이 결정되고
		// 정답 값을 목표로해서 완전탐색을 해보자

		height = 0;
		int minCnt = Integer.MAX_VALUE;
		for (int target = min; target <= max; target++) {
			isOk = true;
			int B = oB;
			cnt = 0;

			for (int i = 0; i <= 256; i++) {
				if (h[i] > 0) {
					if (i > target) { // 제거
						cnt += (i - target) * 2 * h[i];
						B += (i - target) * h[i];
					}

				}
			}
			for (int i = 0; i <= 256; i++) {
				if (h[i] > 0) {
					if (i < target) {
						if ((target - i) * h[i] > B) {
							isOk = false;
							break;
						}
						cnt += (target - i) * h[i]; // 쌓기
						B -= (target - i) * h[i];
					}
				}
			}

			if (isOk) {
				minCnt = Math.min(minCnt, cnt);
				if (minCnt == cnt) {
					height = target;
				}
			}

		}

		System.out.println(minCnt + " " + height);
	}// main
}
