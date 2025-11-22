import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김민정 {
	static int N, M; // 행 크기, 열 크기
	static int[][] map; // 지도
	static int[][] dist; // 거리 배열

	// 목표 지점 좌표
	static int tR;
	static int tC;
	// 4방향 탐색
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];

		tR = 0;
		tC = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 2) {
					tR = i;
					tC = j;
				}
				map[i][j] = n;
			}
		} // 지도 입력 완료

		BFS(tR, tC);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dist[i][j] == 0 && map[i][j] == 1) {
					sb.append(-1 + " ");
				}else {
					sb.append(dist[i][j] + " ");
				}
			}
			sb.append("\n");
		}//거리 출력

		System.out.println(sb);
	}// main

	static void BFS(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				// 범위 체크
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}

				// 방문 체크 및 갈 수 있는 지점인지 체크
				if (dist[nr][nc] > 0 || map[nr][nc] == 0 || map[nr][nc] == 2)
					continue;

				// 방문 가능한 지점 거리 계산 및 좌표 큐에 넣음
				q.add(new int[] { nr, nc });
				dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
			}

		}

	}

}
