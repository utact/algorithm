import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N, M;
    static int[][] arr;
    static boolean[][] visit;
    static int ans = 0;
    static int max = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 배열 완성
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i][j]);
            }
        }

        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(i, j, 1, arr[i][j]);
                visit[i][j] = false;
                dfsT(i, j);
            }
        }
        System.out.println(ans);
    }

    static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dr[d];
            int ny = y + dc[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny]) continue;

            visit[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + arr[nx][ny]);
            visit[nx][ny] = false; // 원상 복구
        }
    }

    // ㅏ는 한 붓 그리기 안됨 == dfs 불가 -> 예외 처리
    static void dfsT(int x, int y) {
        // 4방향 중 하나(k)를 제외하고 나머지 3방향을 더함
        for (int k = 0; k < 4; k++) {
            int sum = arr[x][y];
            boolean ok = true;
            for (int d = 0; d < 4; d++) {
                if (d == k) continue; // 하나는 제외
                int nx = x + dr[d];
                int ny = y + dc[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                	ok = false;
                	break;
                	}
                sum += arr[nx][ny];
            }
            if (ok) ans = Math.max(ans, sum);
        }
    }
}