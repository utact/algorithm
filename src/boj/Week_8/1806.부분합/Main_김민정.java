import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김민정 {
	static int N, S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		int[] arr = new int[N]; // 정수 저장 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 수열 입력

		int ans = Integer.MAX_VALUE;

		int l = 0;
		int r = 0;

		int sum = arr[r];
		while (r < N) {
			if (sum >= S) { // S 이상이면
				ans = Math.min(ans, r - l + 1);
				sum -= arr[l];
				l++;
			} else { // S보다 작으면
				r++;
				if(r<N)
					sum+= arr[r];
				else
					break;
			}

		}

		if (ans == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(ans);

	}// main
}
