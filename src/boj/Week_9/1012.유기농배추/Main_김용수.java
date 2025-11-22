import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][M];
			visited = new boolean[N][M];
			List<int[]> start = new ArrayList<>();

			for (int k = 0; k < K; k++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[y][x] = 1;
				start.add(new int[] { y, x });
			}
			int cnt = 0;
			for (int[] s : start) {
				int y = s[0];
				int x = s[1];
				if (!visited[y][x]) {
					bfs(x, y);
					cnt++;
				}
			}

			System.out.println(cnt);
		}

	}// main

	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];

			for (int k = 0; k < 4; k++) {
				int nx = cx + dx[k];
				int ny = cy + dy[k];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;

				if (!visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					q.add(new int[] { nx, ny });
				}
			}

		}

	}
}
