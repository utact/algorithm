import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
테트로미노_14500

DFS(깊이 우선 탐색)으로 4칸을 연결해 가능한 모든 모양 탐색
단, ㅗ 모양(보라색)은 DFS로 만들 수 없으므로
두 번째 칸(count==2)에서 한 번 더 가지치기(분기)를 수행하여 예외 처리
탐색 중 최대합(max)을 계속 갱신한다

*/
public class Main_나유경 {

	static int N, M; // 세로, 가로
	static int[][] arr;
	static boolean[][] visit;
	static int max = Integer.MIN_VALUE;

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 세로(행)
		M = Integer.parseInt(st.nextToken()); // 가로(열)
		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, arr[i][j], 1);
				visit[i][j] = false;
			}
		}
		System.out.println(max);
	}

	private static void dfs(int row, int col, int sum, int count) {
		// 테트로미노 완성 시 수들의 합 계산
		if (count == 4) {
			max = Math.max(max, sum);
			return;
		}

		// 상하좌우탐색
		for (int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];

			// 범위 벗어나면 예외처리
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				continue;
			}

			// 아직 방문하지 않은 곳이라면
			if (!visit[nr][nc]) {

				// 보라색(ㅗ) 테트로미노 만들기 위해 2번째 칸에서 탐색 한번 더 진행
				if (count == 2) {
					visit[nr][nc] = true;
					dfs(row, col, sum + arr[nr][nc], count + 1);
					visit[nr][nc] = false;
				}

				visit[nr][nc] = true;
				dfs(nr, nc, sum + arr[nr][nc], count + 1);
				visit[nr][nc] = false;

			}
		}
	}
}
