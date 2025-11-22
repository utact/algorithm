import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		st = new StringTokenizer(br.readLine());

		int[] nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 누적합 배열 구하기
		int[] sum = new int[N + 1];
		sum[1] = nums[1];
		for (int i = 2; i <= N; i++) {
			sum[i] = nums[i] + sum[i - 1];
		}

		StringBuilder sb = new StringBuilder();

		// 합 구하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int result = sum[b] - sum[a - 1];

			sb.append(result).append("\n");
		}

		// 결과 출력하기
		System.out.println(sb);

	}// main

}
