import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_정선열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		BigInteger[] dp = new BigInteger[n+1];
		dp[0] = BigInteger.ZERO;
		dp[1] = BigInteger.ONE;
		if (n >= 2)
			dp[2] =BigInteger.TWO;
		
		if (n>=3) {
			for (int i = 3; i <= n; i++)
				dp[i] = dp[i-1].add(dp[i-2]);
		}
		System.out.println(dp[n].mod(BigInteger.valueOf(10007)));
	}
}
