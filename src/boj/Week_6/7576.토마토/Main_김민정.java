import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김민정 {
	static int M, N; // 상자의 가로, 세로 칸 수 (<=1000)
	static int[][] box; // 토마토 상자 배열
	static int[][] dist; // 날짜 저장 겸 방문 체크 배열 ..
	static Queue<int[]> q = new ArrayDeque<int[]>(); // 시작 좌표 넣을 큐...?

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int cnt, ans;	//안 익은 토마토 개수, 정답 변수  

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		box = new int[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				box[i][j] = tmp;

				if (tmp == 1) { // 입력과 동시에 이미 익은 토마토의 좌표를 큐에 넣음 (시작 정점이 됨)
					q.add(new int[] { i, j });
				}else if(tmp == 0) {
					cnt++;
				}

			}
		} // 토마토 정보 입력
		ans = 0;
		BFS();
		if(cnt>0) { // 안 익은 토마토가 남아있으면 
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
		

	}// main

	static void BFS() {

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				if (nr >= N || nr < 0 || nc >= M || nc < 0) // 범위 벗어났을 경우
					continue;

				if (box[nr][nc] != 0)
					continue;

				if (dist[nr][nc] > 0)
					continue;

				// 아직 안 익어있고 방문하지 않은 것에 대해서만 ㄱㄱ
				q.add(new int[] { nr, nc });
				dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
				ans = Math.max(ans, dist[nr][nc]);	//둘 중 더 큰 값(최댓값) 정답 변수에 저장 
				cnt--;	//안 익은 토마토 개수 1 감소 
			}

		} // while

	}

}
