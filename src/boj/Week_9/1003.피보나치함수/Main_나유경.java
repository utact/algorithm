import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] ans0;
	static int[] ans1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		ans0 = new int[41];
		ans1 = new int[41];

		//초기값 
		ans0[0] = 1;
		ans1[0] = 0;
		ans0[1] = 0;
		ans1[1] = 1;

		//점화식
		for (int i = 2; i <= 40; i++) {
			ans0[i] = ans0[i - 1] + ans0[i - 2];
			ans1[i] = ans1[i - 1] + ans1[i - 2];
		}

		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(ans0[n] + " " + ans1[n]);
		}
	}
}
