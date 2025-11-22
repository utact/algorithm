import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int[] dr = { 1, -1, 0, 0 }; // 하, 상, 좌, 우
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int max = Integer.MIN_VALUE;
	static int maxCell = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxCell = Math.max(map[i][j], maxCell);
			}
		} // 배열 입력 끝

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
			}
		}

		System.out.println(max);
	}

	static void dfs(int r, int c, int n, int sum) {

		// 재귀 종료 조건 (4개까지)
		if (n == 4) {
			max = Math.max(sum, max);
			return;
		}

		// 가지치기 (max가 될 수 없는 경우)
		if (sum + (4 - n) * maxCell <= max) {
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) {
				continue;
			}

			// ㅗ 모양 처리를 위해서 2일 때만 한 번 더 탐색
			if (n == 2) {
				visited[nr][nc] = true;
				dfs(r, c, n + 1, sum + map[nr][nc]);
				visited[nr][nc] = false;
			}

			visited[nr][nc] = true;
			dfs(nr, nc, n + 1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}
	}

}
