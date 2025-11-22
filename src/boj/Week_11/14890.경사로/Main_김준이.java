import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int N, L;
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝

		ans = 0;

		// 가로줄 세로줄 검사
		for (int i = 0; i < N; i++) {
			int[] tmp = new int[N];

			for (int j = 0; j < N; j++) {
				tmp[j] = map[j][i];
			}

			boolean isvalidRow = check(map[i]);
			boolean isvalidCol = check(tmp);

			if (isvalidRow) {
				ans++;
			}
			if (isvalidCol) {
				ans++;
			}
		}

		System.out.println(ans);
	}// main

	static boolean check(int[] arr) {

		int cnt = 1; // 연속한 같은 값 체크
		boolean[] visited = new boolean[N]; // 경사로 쌓기 중복 방지

		// 커지는 경우 경사로 쌓기
		for (int i = 0; i < N - 1; i++) {
			// 높이차 체크
			int curr = arr[i];
			int next = arr[i + 1];

			if (Math.abs(curr - next) > 1) {
				return false;
			}

			// 경사로 L칸 이상인지 체크 + 경사로 놓기
			if (curr == next) {
				cnt++;
			} else if (curr < next) {
				if (cnt >= L && !visited[i]) {
					for (int j = i; j > i - L; j--) {
						if (visited[j]) {
							return false;
						}
						visited[j] = true;
					}
					cnt = 1;
				} else {
					return false;
				}
			}
		}

		cnt = 1;

		// 작아지는 경우 경사로 쌓기
		for (int i = N - 1; i > 0; i--) {
			int curr = arr[i];
			int prev = arr[i - 1];

			if (Math.abs(curr - prev) > 1) {
				return false;
			}

			if (curr == prev) {
				cnt++;
			} else if (curr < prev) {
				if (cnt >= L) {
					for (int j = i; j < i + L; j++) {
						if (visited[j]) {
							return false;
						}
						visited[j] = true;
					}
					cnt = 1;
				} else {
					return false;
				}
			}

		}

		return true;
	}

}