import java.util.Scanner;

public class Main_김용수 {
	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
			}
		}


		System.out.println(max);

	}

	static void dfs(int r, int c, int dist, int sum) {
		if (dist == 3) {
			max = Math.max(max, sum);
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr < 0 || nc < 0 || nr >= N || nc >= M)
				continue;
			if (!visited[nr][nc]) {
				int tmp = map[nr][nc];
				visited[nr][nc] = true;
				dfs(nr, nc, dist + 1, sum + map[nr][nc]);

				if (dist == 1)
					dfs(r, c, dist + 1, sum + map[nr][nc]);

				visited[nr][nc] = false;
			}
		}
	}

}
