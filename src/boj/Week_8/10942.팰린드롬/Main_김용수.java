
import java.io.*;
import java.util.*;

public class Main_김용수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N + 1];          // 1-index
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

        boolean[][] dp = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) dp[i][i] = true;

        for (int i = 1; i + 1 <= N; i++) if (a[i] == a[i + 1]) dp[i][i + 1] = true;

        for (int len = 3; len <= N; len++) {
            for (int s = 1; s + len - 1 <= N; s++) {
                int e = s + len - 1;
                if (a[s] == a[e] && dp[s + 1][e - 1]) dp[s][e] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(M * 2);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(dp[S][E] ? 1 : 0).append('\n');
        }
        System.out.print(sb.toString());
    }
}
