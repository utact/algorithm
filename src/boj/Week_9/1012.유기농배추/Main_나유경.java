import java.io.*;
import java.util.*;

// 최소의 배추흰지렁이 마리 수
// BFS
public class Main {

	static int n, m, k; // 가로 세로 배추심어진개수
	static int ans; // 흰 지렁이 수
	static int[][] form;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 가로세로 와 배추심어진 개수 입력받음
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 배추밭을 꾸리자
			// 배추심어진 위치를 넣자
			form = new int[m][n];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				form[y][x] = 1;
			}

			// 밭 전체탐색
			// 배추발견 시
			// 연결된 배추 전부방문처리
			// 지렁이개수증가
			ans = 0;
			for (int i = 0; i <m; i++) {
				for (int j = 0; j < n; j++) {
					if (form[i][j] == 1) {
						bfs(i, j);
						ans++;
					}

				}

			}

			System.out.println(ans);

		}

	}

	// bfs
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { i, j });
		form[i][j] = 0; // 방문처리
		// 현재값을 넣고
		// 상하좌우로 이동하자
		while (!q.isEmpty()) {

			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 이웃한곳에 방문을 안 했거나 1이면
				// 큐에 위치를 넣는다.
				if (nr >= 0 && nc >= 0 && nr < m && nc < n && form[nr][nc] == 1) {
					form[nr][nc] = 0;
					q.add(new int[] { nr, nc });
				}

			}
		}

	}
}