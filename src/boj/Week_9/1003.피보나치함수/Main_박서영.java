import java.io.*;

public class Main_박서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] dp0 = new int[41]; //0이 출력되는 개수 배열
		int[] dp1 = new int[41]; //1이 출력되는 개수 배열
		//N이 0일때, 1일때 미리 설정
		dp0[0] = 1;
		dp1[0] = 0;
		dp0[1] = 0;
		dp1[1] = 1;
		
		int max = 1; //N의 최댓값 저장, 1까지는 설정해놨으므로 1부터 시작 
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			//이전까지의 N의 최댓값과 비교해서 크면 이전에 계산한 부분은 계산 건너뛰고 계산 시작, 작으면 이전 dp배열에서 값 찾아서 출력
			if(max < N) {
				for (int i = max + 1; i <= N; i++) {
					dp0[i] = dp0[i-1] + dp0[i-2];
					dp1[i] = dp1[i-1] + dp1[i-2];
				}
			}
			max = Math.max(max, N); //N의 최댓값 저장
			
			sb.append(dp0[N]);
			sb.append(" ");
			sb.append(dp1[N]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
