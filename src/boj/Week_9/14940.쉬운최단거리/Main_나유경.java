import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int[][] dist;          // 거리 저장용 배열
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];

        int startR = 0, startC = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 시작점 찾기
                    startR = i;
                    startC = j;
                }
            }
        }

        bfs(startR, startC);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) sb.append(-1 + " ");
                else sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs(int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        dist[sr][sc] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue; // 범위 밖
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 0) continue; // 갈 수 없는 땅

                visited[nr][nc] = true;
                dist[nr][nc] = dist[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
    }
}