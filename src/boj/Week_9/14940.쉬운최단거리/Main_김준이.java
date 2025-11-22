import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로의 크기
		int M = Integer.parseInt(st.nextToken()); // 가로의 크기

		int[][] map = new int[N][M];
		int[][] dist = new int[N][M]; // 방문체크 겸 거리 배열
		
		for(int i = 0 ; i < N; i++) {
			Arrays.fill(dist[i], -1);	//거리 배열 초기값 -1 (갈 수 없는 곳은 -1)
		}
		
		int[] dr = { 1, -1, 0, 0 }; // 델타배열 (가로세로 이동)
		int[] dc = { 0, 0, 1, -1 };

		int startRow = 0;
		int startCol = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) { // 출발 지점 체크
					startRow = i;
					startCol = j;
				}
				if (map[i][j] == 0) { // 0인 곳은 dist 미리 0으로 만들어두기
					dist[i][j] = 0;
				}
			}
		} // 입력 끝
		
		
		//bfs로 각 지점에서 목표지점까지의 거리 찾기
		Queue<Pos> q = new LinkedList<>();
		
		q.add(new Pos(startRow, startCol));
		dist[startRow][startCol] = 0;
		
		while (!q.isEmpty()) {
			Pos curr = q.poll();

			for (int k = 0; k < 4; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];
				
				//유효하지 않은 인덱스 or 0인 땅 or 출발지점
				if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 0 || map[nr][nc] == 2)
					continue;
				
				//갈 수 있는 땅 중에서 방문한 땅이면 패스
				if(map[nr][nc] == 1 && dist[nr][nc] > -1)
					continue;
				
				q.add(new Pos(nr, nc));
				dist[nr][nc] = dist[curr.r][curr.c] + 1;
			}
		}
		
		//결과 출력 시작
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}// main

}
