import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, M, maxSafe;
	static Point[] result;
	static int[][] map, infection;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = new Point[3];
		findWall(0, 0);
		
		System.out.println(maxSafe);
	}
	
	//벽 3개를 놓는 경우의 수를 모두 뽑아서(백트래킹) 각각 bfs 돌리고 안전지대 도출한 뒤 최대 안전지대 개수 구하기
	static void findWall(int count, int start) {
		if(count == 3) {
			//bfs 돌리고 감염시킨 후 안전지대 개수를 셀 새로운 배열을 map에서 복사
			infection = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					infection[i][j] = map[i][j];
				}
			}
			//뽑은 벽 3개를 배열에 반영
			for (int i = 0; i < 3; i++) {
				infection[result[i].x][result[i].y] = 1;
			}
			bfs();
			
			int safe = 0; //안전지대 개수
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(infection[i][j] == 0)
						safe++;
				}
			}
			
			maxSafe = Math.max(maxSafe, safe); //최대 안전지대 개수 구하기
			return;
		}
		
		//start부터 N*M-1까지 순회
		for (int k = start; k < N*M; k++) {
			int i = k / M; //1차원 인덱스 k를 2차원 row i로 변환
			int j = k % M; //1차원 인덱스 k를 2차원 col j로 변환
			
			if(map[i][j] == 0) {
				result[count] = new Point(i, j); //3개의 벽 위치 배열
				findWall(count+1, k+1); //중복 뽑기를 막기 위해 k+1에서부터 시작
			}
		}
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		
		//바이러스가 있을 경우(2일 경우) 모두 큐에 넣어놓고 방문 체크함(3으로 바꿈)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(infection[i][j] == 2) {
					q.add(new Point(i, j));
					infection[i][j] = 3;
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			for (int k = 0; k < dc.length; k++) {
				int nr = curr.x + dr[k];
				int nc = curr.y + dc[k];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; //범위 체크
				if(infection[nr][nc] == 0) { //빈칸이면 감염시키고(방문체크) 큐에 넣기
					infection[nr][nc] = 3;
					q.add(new Point(nr, nc));
				}
			}
		}
	}
}
