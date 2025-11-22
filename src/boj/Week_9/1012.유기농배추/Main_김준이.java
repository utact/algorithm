import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김준이 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] fibo;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 배추밭 가로길이
			int N = Integer.parseInt(st.nextToken()); // 배추밭 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추 개수

			int[][] map = new int[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()); // x좌표 = column
				int r = Integer.parseInt(st.nextToken()); // y좌표 = row
				map[r][c] = 1;
			} // 배추 심기 끝

			boolean[][] visited = new boolean[N][M]; // bfs 방문 배열

			Queue<Pos> q = new LinkedList<>();

			int[] dr = { 1, -1, 0, 0 };
			int[] dc = { 0, 0, 1, -1 };

			int ans = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 배추가 아니거나 방문한곳이면 패스
					if (map[i][j] == 0 || visited[i][j])
						continue;

					// 방문하지 않은 배추면 bfs 시작
					q.add(new Pos(i, j));
					visited[i][j] = true;

					while (!q.isEmpty()) {
						Pos curr = q.poll();

						for (int k = 0; k < 4; k++) {
							int nr = curr.r + dr[k];
							int nc = curr.c + dc[k];

							// 유효인덱스가 아니거나 or 방문한 곳이거나 or 배추가 아니면 패스
							if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0)
								continue;

							q.add(new Pos(nr, nc));
							visited[nr][nc] = true;
						}
					} // end while

					ans++;
				}
			}

			System.out.println(ans);

		} // test case loop
	}// main

}
