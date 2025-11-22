import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int targetRow, targetCol;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		targetRow = Integer.parseInt(st.nextToken()); // 타겟 행
		targetCol = Integer.parseInt(st.nextToken()); // 타겟 열

		divideArr((int) Math.pow(2, N), 0, 0);

		System.out.println(ans);
	}// main

	static void divideArr(int len, int r, int c) {

		if (len == 1) {
			// 타겟이 포함된 마지막 구역까지 오면
			// 지그재그 탐색하면서 count해야 함
			int[] dr = { 0, 1, 0 };
			int[] dc = { 1, -1, 1 };

			int nr = r;
			int nc = c;

			if (targetRow == r && targetCol == c)
				return;

			for (int k = 0; k < 3; k++) {
				nr += dr[k];
				nc += dc[k];

				if (nr == targetRow && nc == targetCol)
					return;

				ans++;
			}
			return;
		}

		int nlen = len / 2; // 쪼갠 배열 한 변의 길이

		int targetPos = 0;
		// 1구역 검사
		if (hasTarget(nlen, r, c)) {
			divideArr(nlen, r, c);
			targetPos = 1;
		}

		// 2구역 검사
		if (hasTarget(nlen, r, c + nlen)) {
			divideArr(nlen, r, c + nlen);
			targetPos = 2;
		}

		// 3구역 검사
		if (hasTarget(nlen, r + nlen, c)) {
			divideArr(nlen, r + nlen, c);
			targetPos = 3;
		}

		// 4구역 검사
		if (hasTarget(nlen, r + nlen, c + nlen)) {
			divideArr(nlen, r + nlen, c + nlen);
			targetPos = 4;
		}

		count(targetPos, nlen);
	}

	// 쪼갠 구역에 target이 포함되는지 T/F 판단
	static boolean hasTarget(int len, int r, int c) {
		if (r <= targetRow && targetRow < r + len && c <= targetCol && targetCol < c + len)
			return true;

		return false;
	}

	// target이 포함되지 않은 쪼갠 구역을 count 할지 말지
	// 1, 2, 3, 4 구역이라 했을 때 자기보다 작은 번호의 구역은 count해야 하고 큰 번호는 count 안 함
	static void count(int num, int len) {
		switch (num) {
		case 1:
			break;
		case 2:
			ans += len * len;
			break;
		case 3:
			ans += len * len * 2;
			break;
		case 4:
			ans += len * len * 3;
			break;
		}
	}

}
