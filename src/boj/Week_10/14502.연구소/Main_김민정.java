import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 0: 빈 칸, 1: 벽, 2: 바이러스
 * 새로 세워야 하는 벽 3개
 * 
 * 안전 영역의 최대 크기
 * 뭔가 치킨 배달이랑 비슷한 것 같기도하고..
 */
class Point {
	int r, c;

	public Point() {
	}

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main_김민정 {
	static int N, M;
	static int[][] lab; // 기존 연구소 정보
	static int[][] copy; // 새로운 연구소 정보
	static boolean[][] visited; // 방문 정보

	static List<Point> blanks = new ArrayList<>(); // 빈칸 좌표 저장 리스트
	static List<List<Point>> combList = new ArrayList<>(); // 빈칸 3개 뽑은 조합 리스트

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		lab = new int[N][M];
		copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				lab[i][j] = n;

				if (n == 0) { // 빈칸이면 좌표 넣음
					blanks.add(new Point(i, j));
				}
			}
		} // 입력 끝

		// 조합 생성
		dfs(0, new ArrayList<Point>());

		// 조합 뽑아내면서 안전 영역 계산(BFS)
		for (List<Point> comb : combList) {

			// 원본 배열 깊은 복사
			for (int i = 0; i < N; i++) {
				copy[i] = Arrays.copyOf(lab[i], lab[i].length);
			}

			// 방문배열 초기화
			visited = new boolean[N][M];

			// 조합 케이스 뽑아냄
			for (Point p : comb) {
				copy[p.r][p.c] = 1;
			}

			// BFS 수행(바이러스 퍼트리기)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copy[i][j] == 2 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}

			// 안전 영역 계산 
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copy[i][j] == 0)
						cnt++;
				}
			}

			ans = Math.max(cnt, ans);
		}

		System.out.println(ans);
	}// main

	// dfs(조합 생성)
	static void dfs(int start, List<Point> path) {
		if (path.size() == 3) {
			combList.add(new ArrayList<>(path));
			return;
		}

		for (int i = start; i < blanks.size(); i++) {
			path.add(blanks.get(i));
			dfs(i + 1, path);
			path.remove(path.size() - 1);
		}
	}

	// bfs
	static void bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<Point>();
		q.add(new Point(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Point curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) // 1. 범위 체크
					continue;
				if (visited[nr][nc]) // 2. 방문 체크
					continue;
				if (copy[nr][nc] != 0) // 3. 빈칸 여부 체크
					continue;

				// 빈칸이면
				q.add(new Point(nr, nc));
				copy[nr][nc] = 2;
				visited[nr][nc] = true;
			}
		}
		return;
	}

}