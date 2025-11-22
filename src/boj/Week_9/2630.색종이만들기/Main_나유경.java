import java.io.*;
import java.util.*;

// 색종이 만들기 
public class Main {

	static int[][] arr;
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 현재 영역이 모두 같은 색 인가
		// 모두 같다면 카운트
		// 다르면 4등분 해서 다시검사 ( 재귀호출 )
		// 최종 적으러 파란색/흰색 색종이 출럭

		solve(0, 0, n);
		System.out.println(white);
		System.out.println(blue);

	}

	private static void solve(int r, int c, int size) {
		// 같은 색인지 확인 → 아니면 반으로 잘라서 네 조각 검사
		if (check(r, c, size)) {
			if (arr[r][c] == 0)
				white++;
			else
				blue++;
			return;
		}
		
		int half = size / 2;
		solve(r, c, half); // 왼쪽 위
		solve(r, c + half, half);// 오른쪽 위
		solve(r + half, c, half);// 왼쪽 아래
		solve(r + half, c + half, half); // 오른쪽 아래

	}

	// 해당 영역이 모두 같은 색인지 확인
	private static boolean check(int r, int c, int size) {
		int color = arr[r][c];
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (arr[i][j] != color)
					return false;
			}
		}
		return true;
	}

}