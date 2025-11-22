import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_정선열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1000001];
		
		// 초기값
		dp[0] = dp[1] = 0;
		dp[2] = dp[3] = 1;

		int i = 4; // 초기값
		while (i <= X) {
			dp[i] = dp[i-1]+1; // 3번 연산만 수행
			if (i % 2 == 0) {
				int divide_2 = i/2;
				dp[i] = Math.min(dp[i],dp[divide_2]+1);
			}
			if (i % 3 == 0) {
				int divide_3 = i/3;
				dp[i] = Math.min(dp[i],dp[divide_3]+1);
			}
			i++;
		}
		System.out.println(dp[X]);
	}
}
