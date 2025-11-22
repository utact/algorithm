import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_정선열 {
	static int N,M;
	static int[][] arr;
	static List<int[]> virus,safe; 
	static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		virus = new LinkedList<>();
		safe = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st =new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) virus.add(new int[] {i,j}); // 바이러스 리스트
				if (arr[i][j] == 0) safe.add(new int[] {i,j}); // 안전구역 리스트
			}
		}
		
		int answer = solve();
        System.out.println(answer);
		
	}

	private static int solve() {
		int max = 0;
        int size = safe.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    
                	// 맵 복사
                	int[][] tmp = new int[N][M];
                	for (int a = 0; a < N; a++) {
						for (int b = 0; b < M; b++) {
							tmp[a][b] = arr[a][b];
						}
					}
                	
                	// 벽 3군데
                    int[] x = safe.get(i);
                    int[] y = safe.get(j);
                    int[] z = safe.get(k);
                    tmp[x[0]][x[1]] = 1;
                    tmp[y[0]][y[1]] = 1;
                    tmp[z[0]][z[1]] = 1;
                    bfs(tmp); // 확산
                    
                    // 남은 safe 개수 카운트
                    int cnt = 0;
                    for (int i2 = 0; i2 < N; i2++) {
                        for (int j2 = 0; j2 < M; j2++) {
                            if (tmp[i2][j2] == 0) cnt++;
                        }
                    }
                    if (cnt > max) max = cnt;
                }
                
            }
        }
        return max;
    }
	
	// 바이러스 확산 bfs
	static void bfs(int[][] arr) {
        Queue<int[]> q = new LinkedList<>();
        // 초기
        for (int[] v : virus) q.offer(new int[]{v[0], v[1]});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] == 0) {
                    arr[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
