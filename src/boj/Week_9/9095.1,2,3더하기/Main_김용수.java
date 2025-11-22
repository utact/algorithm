import java.util.Scanner;

public class Main_김용수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int[] numList = new int[T];
		int N=0;
		for(int i = 0; i<T; i++) {
			numList[i] = sc.nextInt();
			N = Math.max(N, numList[i]);
		}
		int[] dp = new int[N+1];
		dp[1] = 1;
		dp[2] = dp[1] +1; // 2
		dp[3] = dp[1] + dp[2] + 1; // 4
		dp[4] = dp[1] + dp[2]+ dp[3]; // 7
		for(int i = 5; i<=N; i++	) {
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		
		for(int i = 0; i<T; i++) {
			System.out.println(dp[numList[i]]);
		}
	}
}
