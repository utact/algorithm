import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int N;
	static int[] arr;
	static boolean[][] dp; // row번~col번까지는 회문임을 기록

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N + 1]; // 인덱스 1~N까지 사용

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][i] = true;
		}
		
		isPalindrome(); // 회문 검사
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 여기서 바로 sysout으로 출력하던거 stringbuilder로 바꾸니까 시간 통과함
			if (dp[start][end]) {
				sb.append(1 + "\n");
			} else {
				sb.append(0 + "\n");
			}

		}
		
		System.out.println(sb);

	}// main

	// 사전에 회문을 다 입력해둠
	static void isPalindrome() {
		for (int i = 1; i <= N; i++) {
			// 홀수길이 회문 검사
			int left = i;
			int right = i;
			while (true) {
				left -= 1;
				right += 1;

				if (left < 1 || right > N)
					break;

				if (arr[left] != arr[right]) {
					break;
				} else {
					dp[left][right] = true;
				}
			}

			// 짝수 길이 회문 검사
			left = i;
			right = i + 1;
			while (true) {
				if (left < 1 || right > N)
					break;
				if (arr[left] != arr[right]) {
					break;
				} else {
					dp[left][right] = true;
				}
				left -= 1;
				right += 1;
			}

		}
	}

}
