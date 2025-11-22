import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 정수 n
			cnt = 0;

			sum123(0);

			System.out.println(cnt);
		}

	}// main
	
	//DFS(백트래킹) - n < 11
	static void sum123(int sum) {

		if (sum == N) {
			cnt++;
			return;
		}

		if (sum + 1 <= N) {
			sum123(sum + 1);
		}
		if (sum + 2 <= N) {
			sum123(sum + 2);
		}
		if (sum + 3 <= N) {
			sum123(sum + 3);
		}

		return;
	}

}
