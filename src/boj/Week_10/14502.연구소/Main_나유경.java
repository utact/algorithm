import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
연구소_14502

0: 빈 칸 / 1: 벽 / 2: 바이러스
벽 3개를 세운 뒤, 바이러스가 퍼지고 남는 "안전지대(0)"의 최대 크기 구하기

1. DFS: 벽 3개를 세우는 모든 조합을 탐색
2. BFS: 벽이 세워진 상태에서 바이러스(2) 전파
3. 안전지대 개수 계산 후 최대값 갱신

*/
public class Main_나유경 {

	static int n,m;
	static int[][] board;
	static int ans = Integer.MIN_VALUE;

	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

	n = Integer.parseInt(st.nextToken()); // 세로(행)
		m = Integer.parseInt(st.nextToken()); // 가로(열)
		board = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(ans);
	}

	//DFS를 통해서 벽 3개를 세우는 모든 경우의 수를 구함
	public static void dfs(int wallCount) {
		if(wallCount == 3) {
			bfs();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(board[i][j] == 0) {
					board[i][j]=1;
					dfs(wallCount +1);
					board[i][j]=0;
				}
			}
		}
	}

	// 벽을 3개 세웠으면 BFS를 통해서 감염시작
	public static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(board[i][j]==2) {
					queue.offer(new int[] {i,j});
				}
			}
		}
		int[][] copyBoard = new int[n][m];
		for (int i = 0; i < n; i++) {
			copyBoard[i] = board[i].clone();
		}
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if(nr >=0 && nc >=0 && nr < n && nc < m) {
					if(copyBoard[nr][nc] == 0) {
						queue.offer(new int[] {nr, nc});
						copyBoard[nr][nc] = 2;
					}
				}
			}
		}
		checkSaveZone(copyBoard);
	}

	//감염을 마치면 안전지대가 얼마나 있는지 확인 
	public static void checkSaveZone(int[][] map) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]==0) {
					count++;
				}
			}
		}
		ans = Math.max(count, ans);
	}
}
