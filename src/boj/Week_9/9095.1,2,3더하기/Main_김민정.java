import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * DP 
 * +1, +2, +3
 * -> N까지 도달하는 경우의 수 
 
 * 점화식 => f(n) = f(n-1) + f(n-2) + f(n-3) (단, n>3), f(1)=1, f(2)=2, f(3) = 4
 */
public class Main_김민정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 정수 N

			int ans = 0;
			int[] f = new int[N + 1];

			if (N == 1) {
				ans = 1;
			} else if (N == 2) {
				ans = 2;
			} else if (N == 3) {
				ans = 4;
			} else {
				// 초기값 세팅
				f[1] = 1;
				f[2] = 2;
				f[3] = 4;

				for (int i = 4; i <= N; i++) {
					f[i] = f[i - 1] + f[i - 2] + f[i - 3];
				} // 경우의 수 계산
				ans = f[N];
			}

			System.out.println(ans);

		} // test case loop

	}// main
}
