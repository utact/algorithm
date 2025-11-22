import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1의 덩어리 카운트 (BFS)
 */
public class Main_김민정 {
	static int N, M, K;	//행, 열, 배추 심어진 위치 개수
	static boolean[][] map;	//배추 밭 정보
	static boolean[][] visited;	//방문 체크
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			map = new boolean[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[y][x] = true;
			}// 배추 밭 정보
			
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visited[i][j] && map[i][j]) {
						BFS(i, j);
						cnt++;
					}
				}
			}// 정점 다 돌면서 BFS 수행
			
			
			System.out.println(cnt);
		}//test case loop
		
	}// main
	
	
	static void BFS(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {r,c});
		visited[r][c] = true;
	
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				
				//범위 체크
				if(nr<0 || nr>=N || nc<0 || nc>=M) {
					continue;
				}
				
				//방문 체크 및 배추 여부 체크
				if(visited[nr][nc] || !map[nr][nc]) {
					continue;
				}
				

				q.add(new int[] {nr, nc});
				visited[nr][nc] = true;
				
			}//4방향 탐색
			
		}// q 빌때까지 반복
		
		
	}
	
}
