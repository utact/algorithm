/*
 * f(1) 개수 == f(n)의 값
 * f(0) 개수 == f(n-1)의 값
 * 
 * N이 0과 1인 경우에 대해서만 출력문 따로 처리해줌
 * N이 2 이상이면 피보나치 메서드 호출 후 계산 -> 반복문 통해 계산
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_김민정 {
	static int N;
	static int[] f;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	
			f = new int[N + 1];		// 피보나치 수열

			StringBuilder ans = new StringBuilder();	//정답 출력 변수
			if (N == 0) {
				ans.append("1 0");
			} else if (N == 1) {
				ans.append("0 1");
			} else {
				fibo(N);
				ans.append(f[N-1] + " " + f[N]);
			}
			
			System.out.println(ans);


		} // test case loop
	}// main

	static void fibo(int n) {
		f[1] = 1;
		
		for(int i=2; i<=n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
	}
}
