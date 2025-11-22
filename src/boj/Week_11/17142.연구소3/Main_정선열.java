import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N, M, min;
    static int[][] arr;
    static List<int[]> virus;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 배열
        arr = new int[N][N];
        virus = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) virus.add(new int[] {i, j});
            }
        }
        
        System.out.println(solve());
    }
    
    // 조합 생성해서 bfs 하고 반환
    private static int solve() {
        min = Integer.MAX_VALUE;
        int[] select = new int[M]; // virus 리스트의 인덱스를 담음
        comb(select, 0, 0);
        if (min == Integer.MAX_VALUE) return -1;
        else return min;
    }

    private static int comb(int[] select, int i, int start) {
        // 종료
        if (i == M) {
            int ans = bfs(select);
            if (ans < min) min = ans;
            return min;
        }
        
        for (int j = start; j < virus.size(); j++) {
            select[i] = j;
            comb(select, i + 1, j + 1); // [수정] i++, j++ -> i+1, j+1 (부작용 제거)
        }
        return min;        
    }

    private static int bfs(int[] select) {
        Queue<int[]> pq = new ArrayDeque<>();
        
        // 거리배열
        int[][] dist = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                dist[r][c] = -1;
            }
        }

        // 시작점
        for (int i = 0; i < M; i++) {
            int[] curr = virus.get(select[i]);
            dist[curr[0]][curr[1]] = 0;
            pq.add(new int[] {curr[0], curr[1]});
        }
        
        // bfs
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1];
            
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N
                		|| arr[nr][nc] == 1
                		|| dist[nr][nc] != -1) continue;

                dist[nr][nc] = dist[r][c] + 1;
                pq.add(new int[] {nr, nc});
            }
        }

        // 최종 시간 계산
        int time = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[r][c] == 0) { // 빈 칸만 시간 집계
                    if (dist[r][c] == -1) return Integer.MAX_VALUE; // 도달 불가 → 실패
                    time = Math.max(time, dist[r][c]);
                }
            }
        }
        return time;
    }
}
