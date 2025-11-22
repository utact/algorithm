import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N;
    static int[][] arr;
    static int w = 0;
    static int b = 0;
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        StringBuilder sb = new StringBuilder();
        sb.append(w).append('\n').append(b).append('\n');
        System.out.print(sb.toString());
    }

    // 재귀를 통한 해결
    static void dfs(int r, int c, int size) {
        if (isOk(r, c, size)) {
            // 단색이면 카운트 증가
            if (arr[r][c] == 0) w++;
            else b++;
            return;
        }
        int half = size / 2;
        // 4분할 재귀
        dfs(r, c, half);
        dfs(r, c + half, half);
        dfs(r + half, c, half);
        dfs(r + half, c + half, half);
    }

    // 단색 검사
    static boolean isOk(int r, int c, int size) {
        int color = arr[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[i][j] != color) return false;
            }
        }
        return true;
    }
}

