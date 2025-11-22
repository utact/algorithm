import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static class Pos {
		int r, c, height, time;

		public Pos(int r, int c, int height, int time) {
			this.r = r;
			this.c = c;
			this.height = height;
			this.time = time;
		}

	}

	static int N, M, B;
	static int[][] map;
	static int max, min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝

		int target = 256;
		int ansTime = Integer.MAX_VALUE; // 최소 시간
		int ansHeight = Integer.MIN_VALUE; // 최소 시간의 최대 높이

		while (target >= 0) {
			int inv = B;
			int time = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int curr = map[i][j]; // 현재 땅의 값
					// 이걸 이제 target값으로 맞춰야 함
					if (target == curr) {
						continue;
					} else if (target > curr) {
						// 땅을 쌓아야 함
						int tmp = target - curr; // 내가 쌓을 땅 수
						inv -= tmp;
						time += tmp;
					} else {
						// 땅을 파야 함
						int tmp = curr - target;
						inv += tmp;
						time += tmp * 2;
					}
				}
			} // 땅 고르기 끝

			// 인벤토리 값이 음수면 안 됨
			if (inv < 0) {
				target--; // 이 부분 빼먹었다..
				continue;
			}

			if (time < ansTime) {
				ansTime = time;
				ansHeight = target; // 땅 높이가 높은 것부터 내려오기 때문에 최소시간 값이 최초 갱신될때만 땅 높이 바꿔주면 됨
			}

			target--;
		} // while

		System.out.println(ansTime + " " + ansHeight);

	}// main

}