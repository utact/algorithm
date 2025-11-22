import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_정선열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] dp = new int[12];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		int i = 4;
		while (i < 12) {
			dp[i] = dp[i-1]+ dp[i-2] + dp[i-3]; 
			i++;
		}
	
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < T; j++) {
			int curr = Integer.parseInt(br.readLine());
			sb.append(dp[curr]+"\n");
		}
		System.out.println(sb);
	}
}
