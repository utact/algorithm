import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_정선열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		// dp 배열
		dp = new int[41][2];
		
		// 초기값
		dp[0][1] = 0;
		dp[0][0] = 1;
		dp[1][0] = 0;
		dp[1][1] = 1;
		fibonacci(40);
		for (int i = 0; i < T; i++) {
			int curr = Integer.parseInt(br.readLine());		// 현재 숫자
			sb.append(dp[curr][0] +" "+ dp[curr][1] +"\n");	// 출력
		}
		System.out.println(sb);
	}
	static int[][] dp;
	static void fibonacci(int n) {
		if (n <= 1) return;
		
		for (int i = 2; i <= n; i++) {
			dp[i][0]= dp[i-2][0]+dp[i-1][0];
			dp[i][1]= dp[i-2][1]+dp[i-1][1];
		}
	}
}
