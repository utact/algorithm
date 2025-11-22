import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int N, M;
	static int[][] map;
	static int[][] visited;
	static List<int[]> virusList;
	static int[][] selVirus;
	static int min = Integer.MAX_VALUE;
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 연구소 크기
		M = Integer.parseInt(st.nextToken()); // 바이러스 개수

		map = new int[N][N];
		virusList = new ArrayList<>();
		selVirus = new int[M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusList.add(new int[] { i, j });
				}
			}
		} // 입력 끝
		
		combvirus(0, 0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}

	}// main

	static void combvirus(int idx, int sidx) {
		// 바이러스를 다 조합한 경우
		if (sidx == M) {
			spreadVirus();
			return;
		}

		// 더 이상 고려할 바이러스가 없을 경우
		if (idx >= virusList.size()) {
			return;
		}

		selVirus[sidx][0] = virusList.get(idx)[0];
		selVirus[sidx][1] = virusList.get(idx)[1];

		combvirus(idx + 1, sidx + 1); // 바이러스 선택한 경우

		combvirus(idx + 1, sidx); // 바이러스 선택하지 않은 경우
	}
	
	
	// 바이러스 퍼지기
	static void spreadVirus() {
		
		Queue<int[]> q = new LinkedList<>();
		visited = new int[N][N];
		int time = 0;
		
		// 방문 배열 초기화
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		
		
		//활성화된 바이러스를 큐에 넣고 bfs 시작
		for(int i = 0; i < M; i++) {
			int r = selVirus[i][0];
			int c = selVirus[i][1];
			q.add(new int[] {r, c});
			visited[r][c] = 0;
		}
		
		// bfs
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			for(int k = 0; k < 4; k++) {
				int nr = curr[0] + dr[k];
				int nc = curr[1] + dc[k];
				
				//유효 인덱스가 아니거나 or 벽이거나 or 방문한 곳이거나
				if(nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;
				if(map[nr][nc] == 1)
					continue;
				if(visited[nr][nc] != -1)
					continue;
				
				// 큐에 추가
				q.add(new int[] {nr, nc});
				visited[nr][nc] = visited[curr[0]][curr[1]] + 1;
				
				// 조기 종료 조건
				if(map[nr][nc] == 0 && visited[nr][nc] > min)
					return;
			}
		}
		
		//빈칸인데 바이러스가 안 퍼진 곳이 있는지 체크 + 이번 조합에서 time 값 찾기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0 && visited[i][j] == -1) {
					return;
				}
				
				if(map[i][j] == 0 && visited[i][j] > time)
					time = visited[i][j];
			}
		}
		
		// 전역 최소 time 값 갱신
		if(time < min)
			min = time;
		
		
	}//spreadVirus
	
}