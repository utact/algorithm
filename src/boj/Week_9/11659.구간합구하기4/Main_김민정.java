import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김민정 {
	static int N, M;
	static int[] cSum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//수의 개수, 연산 개수 입력 
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cSum = new int[N+1];
		
		//수를 입력받으면서 누적합 계산 
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			cSum[i] = cSum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		
		//연산 횟수만큼 누적합 수행 
		for(int k=0; k<M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			sb.append(cSum[j] - cSum[i-1]);
			sb.append("\n");
		}
		
		//정답 출력 
		System.out.println(sb);
		
		
	}
}
