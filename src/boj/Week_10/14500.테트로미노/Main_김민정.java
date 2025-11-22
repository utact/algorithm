import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백트래킹으로 구현 ㄱㄱ
 * 
 * 4 케이스는 상하좌우 맘대로 선택 -> 한 정점으로 부터 3번동안 수행
 * 1 케이스(ㅗ) 는 예외 케이스 
 */

public class Main_김민정 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	// 4 방향
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int ans;
	static List<int[]> neighbors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 배열 입력

		// 정답 변수 초기화
		ans = Integer.MIN_VALUE;

		// 모든 좌표에 대해 테트로미노 계산 수행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 1. 4 유형 테트로미노
				backtrack(i, j, 0, map[i][j]);

				// 2. ㅗ 테트로미노
				int min = Integer.MAX_VALUE;
				neighbors = new ArrayList<>();

				// 2-1. 상하좌우 탐색
				int tSum = map[i][j];
				
				for (int d = 0; d < 4; d++) {
					int nR = i + dr[d];
					int nC = j + dc[d];

					if (nR < 0 || nR >= N || nC < 0 || nC >= M)
						continue;

					min = Math.min(map[nR][nC], min);
					neighbors.add(new int[] { nR, nC });
					tSum += map[nR][nC];
				}

				// 2-2. 이웃 4개면 최소 이웃값 뺌
				if (neighbors.size() == 4)
					tSum -= min;

				// 3. 4 유형 테트로미노 최댓값과 ㅗ 테트로미노 최댓값 비교
				ans = Math.max(ans, tSum);
			}
		}

		System.out.println(ans);

	}// main

	// 4 유형 테트로미노에 대한 백트랙킹
	static void backtrack(int cR, int cC, int depth, int sum) {

		visited[cR][cC] = true; // 방문 처리

		// 종료 조건
		if (depth == 3) {
			ans = Math.max(ans, sum);
			visited[cR][cC] = false;
			return;
		}

		// 백트래킹
		for (int i = 0; i < 4; i++) {
			int nR = cR + dr[i];
			int nC = cC + dc[i];

			// 범위 체크
			if (nR < 0 || nR >= N || nC < 0 || nC >= M)
				continue;
			// 방문 체크
			if (visited[nR][nC])
				continue;

			// 방문 아직 안했으면
			sum += map[nR][nC];
			backtrack(nR, nC, depth + 1, sum);

			// 복구
			sum -= map[nR][nC];
		}

		visited[cR][cC] = false;

	}// 백트래킹 함수

}