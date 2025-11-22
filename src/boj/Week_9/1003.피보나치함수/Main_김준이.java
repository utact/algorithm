import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_김준이 {

	static int[] fibo;
	static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			// 피보나치 0, 1 호출 기록 배열
			// [n][0]은 0이 나온 횟수, n[1]은 1이 나온 횟수
			arr = new int[N + 1][2];
			arr[0][0] = 1;
			arr[0][1] = 0;

			if (N > 0) {
				arr[1][0] = 0;
				arr[1][1] = 1;
			}

			int n = 2;
			while (n <= N) {
				arr[n][0] = arr[n - 1][0] + arr[n - 2][0];
				arr[n][1] = arr[n - 1][1] + arr[n - 2][1];
				n++;
			}

			System.out.println(arr[N][0] + " " + arr[N][1]);
		}
	}// main

}
