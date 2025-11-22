import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N, M;
    static int[][] arr;
    static int[][] dist;
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dist = new int[N][M];
        int sr = -1, sc = -1;
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                dist[r][c] = -1;
                if (arr[r][c] == 2) {
                	sr = r;
                	sc = c;
                	}
            }
        }
        
        // bfs 수행
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        dist[sr][sc] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (arr[nr][nc] == 0 || dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] == 0) sb.append(0);
                else sb.append(dist[r][c]);
                if (c + 1 < M) sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
