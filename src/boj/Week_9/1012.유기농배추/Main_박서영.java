import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][M]; //배추밭 배열 - 0: 빈 땅, 1: 배추심은땅, 2: 방문한 땅
			int cnt = 0; //지렁이 필요 개수
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				map[Y][X] = 1; //배추 위치 저장
			}
			
			Queue<Point> q = new ArrayDeque<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0 || map[i][j] == 2) //2차원 배열 돌면서 방문했거나 배추가 없으면 패스
						continue;
					//방문 안했고 배추 있으면 bfs 시작
					q.add(new Point(i, j));
					map[i][j] = 2;
					
					while(!q.isEmpty()) {
						Point curr = q.poll();
						for (int k = 0; k < 4; k++) {
							int nx = curr.x + dx[k];
							int ny = curr.y + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue; //범위 체크
							if(map[nx][ny] == 0) continue; //배추 있는지 체크 
							if(map[nx][ny] != 2) { //방문 했는지 체크
								map[nx][ny] = 2;
								q.add(new Point(nx, ny));
							}
						}
					}
					cnt++; //큐 빌 때마다 지렁이 개수 하나 올리기
				}
			}
			
			System.out.println(cnt);
		}
	}
}

