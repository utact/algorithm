import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main_김민정 {
	static int N; 				// 한 변 길이
	static boolean[][] visited;	//방문 체크 베열

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		char[][] grid1 = new char[N][N];	//일반인 그리드
		char[][] grid2 = new char[N][N];	//적록색약 그리드

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = input.charAt(j);
				grid1[i][j] = c;	//일반인은 그냥 넣음
				
				//적록색약은 G를 R로 저장
				if (c == 'G') {
					grid2[i][j] = 'R';
				} else {
					grid2[i][j] = c;
				}
			}
		} //그리드 입력 끝

		int cnt1 = 0; // 일반인이 보이는 구역 개수
		int cnt2 = 0; // 적록색약인이 보이는 구역 개수

		visited = new boolean[N][N]; // 방문 배열 선언
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {	//방문하지 않은 것만 BFS 수행
					bfs(i, j, grid1);
					cnt1++;
				}
			}
		} //BFS 수행

		visited = new boolean[N][N]; // 방문 배열 다시 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {	//방문하지 않은 것만 BFS 수행
					bfs(i, j, grid2);
					cnt2++;
				}
			}
		} //BFS 수행

		//정답 출력
		System.out.println(cnt1 + " " + cnt2);

	}// main

	static void bfs(int r, int c, char[][] grid) {
		Queue<int[]> q = new ArrayDeque<>(); // 좌표 저장할 큐

		// 시작 정점 큐에 넣고 방문처리
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();	//좌표 꺼냄

			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				// 범위 초과 여부 확인
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				// 방문 체크
				if (visited[nr][nc])
					continue;
				// 현재 값과 비교
				if (grid[curr[0]][curr[1]] != grid[nr][nc])
					continue;
				// 위 세 조건 만족하는 경우만 큐에 넣고 방문 처리
				q.add(new int[] { nr, nc });
				visited[nr][nc] = true;
			}

		} // while
	}// bfs

}
