import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_정선열 {
	static int N;
	static int[][] arr;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int shark_r = 0;
		int shark_c = 0;
		
		// 배열 완성
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					shark_r = i;
					shark_c = j;
					arr[i][j] = 0; // 상어 위치를 0으로 변경해 bfs과정에서 혼동되지 않게 함.
				}
			}
		}
		
		int size = 2; // 초기 상어 크기
		int cnt = 0; // 먹은 물고기
		int time = 0; // 총 이동시간
		
		// 1. 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기
		// 2. 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기
		while (true) {
            int[] target = bfs(shark_r, shark_c, size);
            
            // 종료 조건
            if (target == null) break;

            int tr = target[0];
            int tc = target[1];
            int dist = target[2];

            // 이동
            time += dist;
            cnt += 1;
            arr[tr][tc] = 0;
            shark_r = tr;
            shark_c = tc;

            // 크기 증가 체크
            if (cnt == size) {
                size += 1;
                cnt = 0;
            }
        }

        System.out.println(time);
    }

    static int[] bfs(int sr, int sc, int size) {
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        // 방문 체크
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        int min = Integer.MAX_VALUE;
        int curr_r = -1, curr_c = -1;
        
        // bfs 조회
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            int cd = dist[r][c];

            // 가지치기: 최단거리 기준 탐색
            if (cd > min) continue;

            // 먹을 수 있다면 아기상어 위치 갱신
            if (arr[r][c] > 0 && arr[r][c] < size) {
                if (cd < min || // 최단 거리 갱신
                    (cd == min && (r < curr_r || (r == curr_r && c < curr_c)))) { // 행/열 우선순위
                    min = cd;
                    curr_r = r;
                    curr_c = c;
                }
                // 추가 탐색
                continue;
            }

            // 4방 탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if (arr[nr][nc] > size) continue;

                visited[nr][nc] = true;
                dist[nr][nc] = cd + 1; // 거리 갱신
                q.offer(new int[]{nr, nc});
            }
        }

        if (curr_r == -1) return null; // 먹이 없음
        return new int[]{curr_r, curr_c, min};
    }

}
