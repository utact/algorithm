import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, sharkSize, fishCnt, time;
	static int[][] map;
	static Point sharkLoca;
	static List<Point> fish = new ArrayList<>();
	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	
	static class Point{ //위치 좌표, 이동 거리 저장할 클래스
		int x, y, distance;

		public Point(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sharkSize = 2; //상어 크기
		fishCnt = 0; //먹은 물고기 수
		time = 0; //움직인 시간
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) //배열 입력받으면서 상어 위치 저장 및 상태 0으로 초기화
					map[i][j] = 0;
					sharkLoca = new Point(i, j, 0);
			}
		}
		
		//bfs 돌리면서 fish 리스트에 먹을 수 있는 물고기들 넣어두고 가장 가까운 물고기와의 거리를 time에 더하기
		//리스트 비었으면 while문 탈출
		while(true) {
			bfs();
			
			if(fish.isEmpty())
				break;
			else {
				//정렬조건: 거리 오름차순, 같을 경우 x값 오름차순(더 위쪽), 같을 경우 y값 오름차순(더 왼쪽)
				Collections.sort(fish, new Comparator<Point>() {
					@Override
					public int compare(Point o1, Point o2) {
						if(o1.distance != o2.distance)
							return o1.distance - o2.distance;
						else if(o1.x != o2.x)
							return o1.x - o2.x;
						else
							return o1.y - o2.y;
					}
				});
				
				//정렬 이후 fish 리스트의 가장 첫번째 물고기가 가장 가까운 물고기가 됨
				time += fish.get(0).distance - 1; //방문+거리저장배열을 1부터 시작해서 -1해주고 총 시간에 더해주기
				fishCnt++; //먹은 물고기 수 1 추가
				map[fish.get(0).x][fish.get(0).y] = 0; //먹은 물고기의 칸 0으로 바꾸기
				sharkLoca = new Point(fish.get(0).x, fish.get(0).y, 0); //상어 위치 이동
				if(fishCnt == sharkSize) { //먹은 물고기수와 상어 사이즈 같으면 사이즈 +1하고 물고기수 초기화
					sharkSize++;
					fishCnt = 0;
				}
				fish.clear(); //fish 리스트 초기화
			}
		}
		
		System.out.println(time); //결과 출력
	}
	
	static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(sharkLoca);
		int[][] visited = new int[N][N]; //방문체크+거리저장배열
		visited[sharkLoca.x][sharkLoca.y] = 1; //1부터 시작했으므로 나중에 -1해주기
		int min = Integer.MAX_VALUE; //이동거리 최솟값 저장(필요없는 연산 건너뛰기용)
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			if(visited[curr.x][curr.y] > min) continue; //이동거리가 최솟값보다 크면 건너뛰기
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.x + dr[i];
				int nc = curr.y + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //범위체크
				if(map[nr][nc] > sharkSize) continue; //상어크기보다 큰지 체크
				if(visited[nr][nc] == 0) { //방문체크
					visited[nr][nc] = visited[curr.x][curr.y] + 1; //이동거리 갱신
					//상어 크기보다 작은 물고기가 존재하고 거기까지의 거리가 최솟값보다 작거나 같다면 최솟값 갱신 후 리스트에 추가
					if(map[nr][nc] < sharkSize && map[nr][nc] != 0 && visited[nr][nc] <= min) { 
						min = visited[nr][nc];
						fish.add(new Point(nr, nc, visited[nr][nc]));
					}
					q.add(new Point(nr, nc, visited[nr][nc]));
				}
			}
		}
	}
}
