import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int N;
	static int[][] map;
	static int white, blue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		boolean isPaper = true;

		// 종이 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != map[0][0]) {
					isPaper = false;
				}
			}
		} // 입력 끝

		white = 0;
		blue = 0;

		// 전체가 종이인지 먼저 체크
		if (isPaper) {
			if (map[0][0] == 0) { // 흰
				white++;
				System.out.println(white + "\n" + blue);
				return;
			} else {
				blue++;
				System.out.println(white + "\n" + blue);
				return;
			}
		}

		// 종이 쪼개면서 찾기
		dividePaper(N, 0, 0);

		System.out.println(white + "\n" + blue);

	}// main

	// r,c는 시작 row, column & len은 현재 종이 한 변의 길이
	static void dividePaper(int len, int r, int c) {

		if (len == 1) {
			return;
		}
		// 구역을 쪼개
		int nlen = len / 2; // 쪼갠 종이 한 변 길이

		// 1구역 검사
		if (!isUnifromColor(nlen, r, c)) {
			dividePaper(nlen, r, c);
		}

		// 2구역 검사
		if (!isUnifromColor(nlen, r, c + nlen)) {
			dividePaper(nlen, r, c + nlen);
		}

		// 3구역 검사
		if (!isUnifromColor(nlen, r + nlen, c)) {
			dividePaper(nlen, r + nlen, c);
		}

		// 4구역 검사
		if (!isUnifromColor(nlen, r + nlen, c + nlen)) {
			dividePaper(nlen, r + nlen, c + nlen);
		}

	}

  //색종이인지 검사
	static boolean isUnifromColor(int len, int r, int c) {
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (map[i][j] != map[r][c]) {
					return false;
				}
			}
		}

		// 색종이면 색깔에 맞춰서 cnt 증가
		if (map[r][c] == 0)
			white++;
		else
			blue++;

		return true;
	}

}
