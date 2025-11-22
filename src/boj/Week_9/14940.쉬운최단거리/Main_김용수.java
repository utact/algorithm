import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int n, m;
	static int er, ec;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map, dist;
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		dist = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				dist[i][j]= -1;
				if (map[i][j] == 2) {
					er = i;
					ec = j;
				}
				else if(map[i][j] ==0)
					dist[i][j]=0;
			}
		}
		visited = new boolean[n][m];
		bfs(er, ec);

		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < m; j++) {
				sb.append(dist[i][j]).append(' ');
			}
			System.out.println(sb.toString());
		}

	}

	static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;
		dist[r][c] = 0;

		while (!q.isEmpty()) {
			int curr[] = q.poll();
			int cr = curr[0];
			int cc = curr[1];

			for (int k = 0; k < 4; k++) {
				int nr = cr + dr[k];
				int nc = cc + dc[k];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m)
					continue;
				if (!visited[nr][nc] && map[nr][nc] ==1) {
					visited[nr][nc] = true;
					dist[nr][nc] = dist[cr][cc] + 1;
					q.add(new int[] { nr, nc });
				}
			}

		}
	}
}