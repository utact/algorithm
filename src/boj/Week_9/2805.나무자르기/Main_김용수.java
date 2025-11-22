import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김용수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] trees = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}

		int min = 0;
		int ans = 0;
		while (min<=max) {
			int mid = (min + max) / 2;
			long sum = 0;

			for (int t : trees) {
				if (t > mid) {
					sum += t - mid;
					if (sum>=M)
						break;
				}
			}

			if (sum >= M) {
				ans = mid;
				min = ++mid;
			} else
				max = --mid;

		}

		System.out.println(ans);

	}
}
