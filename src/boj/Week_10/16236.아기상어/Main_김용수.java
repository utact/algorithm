import java.util.*;
public class Main_김용수 {
    static int N;
    static int[][] map;
    static int[] start;
    static int baby = 2;   // 상어 크기
    static int eat = 0;    // 현재 크기에서 먹은 마릿수
    static int time = 0;   // 정답(누적 시간)

    static int[] dr = {-1, 0, 0, 1}; // 위, 왼, 오, 아래 
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    start = new int[]{i, j};
                    map[i][j] = 0; // 시작 위치는 빈 칸으로
                }
            }
        }

        while (true) {
            int[] picked = bfsPick(); 
            if (picked == null) break; // 더 이상 먹을 물고기 없음

            int r = picked[0], c = picked[1], d = picked[2];
            time += d;        // 시간 증가
            eat++;            // 먹은 마릿수 증가
            if (eat == baby) { // 크기 증가 조건
                baby++;
                eat = 0;
            }
            start[0] = r; start[1] = c;
            map[r][c] = 0;    // 먹은 자리 비우기
        }

        System.out.println(time);
    }

    // 한 번의 탐색에서 "가장 가까운" + "위/왼 우선" 물고기 하나를 고른다
    static int[] bfsPick() {
        boolean[][] vis = new boolean[N][N];
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0], start[1]});
        vis[start[0]][start[1]] = true;
        dist[start[0]][start[1]] = 0;

        int bestR = -1, bestC = -1, bestD = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0], cc = cur[1];
            int cd = dist[cr][cc];

            // 이미 현재까지 찾은 bestD보다 멀면 더 볼 필요 없음
            if (cd > bestD) continue;

            // 먹을 수 있는 물고기면 후보 갱신 (거리 같으면 위/왼 우선)
            if (map[cr][cc] > 0 && map[cr][cc] < baby) {
                if (cd < bestD ||
                   (cd == bestD && (cr < bestR || (cr == bestR && cc < bestC)))) {
                    bestD = cd;
                    bestR = cr;
                    bestC = cc;
                }
                // 같은 거리대는 끝까지 살펴야 하므로 continue
                continue;
            }

            for (int k = 0; k < 4; k++) {
                int nr = cr + dr[k];
                int nc = cc + dc[k];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (vis[nr][nc]) continue;
                if (map[nr][nc] <= baby) {
                    vis[nr][nc] = true;
                    dist[nr][nc] = cd + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        if (bestR == -1) return null;           // 후보 없음
        return new int[]{bestR, bestC, bestD};  // (r, c, dist)
    }
}
