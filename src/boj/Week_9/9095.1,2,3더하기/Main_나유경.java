import java.io.*;
import java.util.*;

public class Main {

	static int[] dp;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine());
			dp = new int[n + 1];
			calc(n);
			System.out.println(dp[n]);
		}

	}

	private static void calc(int n) {
		dp[1] = 1;
		if(n>=2)dp[2]=2;
		if(n>=3)dp[3]=4;
		
		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i- 1] + dp[i - 2] + dp[i - 3];
		}

	}
}
