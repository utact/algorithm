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

	static int N, M;
	static int[][] arr;
	static int maxArea = Integer.MIN_VALUE; // 안전 영역의 최대 넓이
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		buildWall(0, 0, 0);

		System.out.println(maxArea);
	}// main

	// 벽 세우기 (백트래킹)
	static void buildWall(int r, int c, int n) {
		if (n == 3) {
			spreadVirus();
			return;
		}

		for (int i = r; i < N; i++) {
			int startCol = 0;
			if (i == r)
				startCol = c;
			for (int j = startCol; j < M; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					buildWall(i, j, n + 1);
					arr[i][j] = 0;
				}
			}
		}

	}

	// 바이러스 퍼트리기 - bfs
	static void spreadVirus() {
		int[][] copied = copyArr();
		Queue<Pos> q = new LinkedList<>();

		// 바이러스 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copied[i][j] == 2)
					q.add(new Pos(i, j));
			}
		}

		// bfs로 바이러스 퍼트리기
		while (!q.isEmpty()) {
			Pos curr = q.poll();

			for (int k = 0; k < 4; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || copied[nr][nc] != 0) {
					continue;
				}

				copied[nr][nc] = 2;
				q.add(new Pos(nr, nc));
			}
		} // while

		updateMax(copied);
	}// bfs

	// 안전영역 값 갱신
	static void updateMax(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}

		maxArea = Math.max(maxArea, cnt);
	}// updateMax

	static int[][] copyArr() {
		int[][] copied = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copied[i][j] = arr[i][j];
			}
		}

		return copied;
	}// copyArr
}
