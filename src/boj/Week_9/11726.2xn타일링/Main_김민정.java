import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * DP
 * f[n] = f[n-1] + f[n-2] (단, n>=3)
 */

public class Main_김민정 {
	static int n;
	static long[] f;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		f = new long[n + 1];

		if (n == 1) {
			System.out.println(1%10007);
		} else if (n == 2) {
			System.out.println(2%10007);
		} else {
			f[1] = 1;
			f[2] = 2;

			for (int i = 3; i <= n; i++) {
				f[i] = (f[i - 1] + f[i - 2])%10007;
			}

			System.out.println(f[n]);
		}

	}
}
