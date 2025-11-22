import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int N, M, max = 0;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static List<int[]> start = new ArrayList<>();
	static List<int[]> blank = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2)
					start.add(new int[] { i, j }); // 바이러스 시작점 저장
				else if (map[i][j] == 0)
					blank.add(new int[] { i, j }); // 초기 빈칸 저장
			}
		}

		for (int i = 0; i < blank.size() - 2; i++) {
			for (int j = i + 1; j < blank.size() - 1; j++) {
				for (int k = j + 1; k < blank.size(); k++) {
					makewall(i);
					makewall(j);
					makewall(k);
					visited = new boolean[N][M];
					bfs();

					resetwall(i); // 벽 초기화
					resetwall(j);
					resetwall(k);
				}
			}
		}
		
		System.out.println(max);
		
	}// main

	static void makewall(int idx) {
		int r = blank.get(idx)[0];
		int c = blank.get(idx)[1];
		map[r][c] = 1;
	}

	static void resetwall(int idx) {
		int r = blank.get(idx)[0];
		int c = blank.get(idx)[1];
		map[r][c] = 0;
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < start.size(); i++) {
			int r = start.get(i)[0];
			int c = start.get(i)[1];
			q.add(start.get(i));
			visited[r][c] = true; // 방문처리
		}
		
		int cnt = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			if(blank.size()-3-cnt<=max)
				break; // 현재 최대보다 안전영역 적어지면 그냥 종료
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				if (map[nr][nc] == 0&&!visited[nr][nc]) {
					q.add(new int[] { nr, nc });
					visited[nr][nc]=true;
					cnt++; // 바이러스 확장
				}
			}
		}
		
		int safe = blank.size()-3-cnt;
		max = Math.max(safe, max);
	}// bfs

}