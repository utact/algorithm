import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, M, time, minTime, zeroNum, zeroInfection;
	static int[][] map, infection;
	static Point[] result;
	static List<Point> virus;
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
		
		map = new int[N][N];
		virus = new ArrayList<>();
		//지도 배열 입력받으면서 바이러스 좌표 리스트에 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)
					virus.add(new Point(i, j));
				if(map[i][j] == 0)
					zeroNum++; //빈칸 개수 세놓기
			}
		}
		
		if(zeroNum == 0) //빈칸이 없으면 모든 칸에 바이러스가 있게 되므로 바로 0 출력
			System.out.println(0);
		else {
			result = new Point[M];
			minTime = Integer.MAX_VALUE;
			findVirus(0, 0);
			
			//최소 시간이 갱신되지 않았다면(모든 조합에서 모든 빈 칸을 감염시킬 수 없는 경우) -1 출력
			if(minTime == Integer.MAX_VALUE)
				minTime = -1;
			
			//그 외에는 최소 시간 출력
			System.out.println(minTime);
		}
	}
	
	//활성 바이러스를 M개 뽑는 모든 조합 구하는 함수
	static void findVirus(int cnt, int start) {
		if(cnt == M) {
			infection = new int[N][N]; //감염시간, 방문여부 저장할 새로운 배열(map 배열 깊은복사)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					infection[i][j] = map[i][j];
				}
			}
			
			time = 0;
			zeroInfection = 0;
			bfs();
			
			//빈칸의 개수와 감염된 빈칸의 개수가 다르면 최소 시간 갱신하지 않음 
			if(zeroInfection != zeroNum)
				time = Integer.MAX_VALUE;
			
			//그 외에는 계산한 시간과 최소 시간 비교 후 갱신
			minTime = Math.min(minTime, time);
			return;
		}

		//바이러스 리스트에서 하나씩 뽑아서 배열에 저장, 재귀 이용해서 M개까지 뽑기
		for (int i = start; i < virus.size(); i++) {
			result[cnt] = new Point(virus.get(i).x, virus.get(i).y);
			findVirus(cnt + 1, i + 1);
		}
	}
	
	//바이러스를 퍼트리는 시간을 계산할 bfs 함수
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			//조합 배열에 들어있는 M개의 바이러스를 모두 큐에 넣고 한번에 bfs 돌리기
			q.add(new Point(result[i].x, result[i].y));
			//방문 체크를 위해 3부터 시작, 시간 계산을 위해 +1 해가며 도달하는 칸에 저장
			infection[result[i].x][result[i].y] = 3;
		}
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			for (int i = 0; i < dc.length; i++) {
				int nr = curr.x + dr[i];
				int nc = curr.y + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //범위 체크
				if(infection[nr][nc] == 1) continue; //벽 체크
				if(infection[nr][nc] == 2) { //비활성 바이러스 칸에 도달할 경우 시간 계산은 하지만 time에 갱신은 하지 않음
					infection[nr][nc] = infection[curr.x][curr.y] + 1;
					q.add(new Point(nr, nc));
				}else if(infection[nr][nc] == 0) { //빈칸에 도달할 경우 시간 계산 후 최댓값을 갱신하고, 감염시킨 빈칸의 개수를 +1 하기
					infection[nr][nc] = infection[curr.x][curr.y] + 1;
					time = Math.max(time, infection[nr][nc] - 3); //방문체크용으로 3부터 시작했으므로 시간 갱신 시 -3 하기
					q.add(new Point(nr, nc));
					zeroInfection++;
				}
			}
		}
	}
}
