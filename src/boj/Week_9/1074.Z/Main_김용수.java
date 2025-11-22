import java.io.*;
import java.util.*;

public class Main_김용수 {

	static int solve(int N, int r, int c, int idx) {
		// 더 이상 쪼갤 수 없으면 종료
		if (N == 0)
			return idx;

		// 현재 크기의 절반
		int half = (int) Math.pow(2, N - 1);

		int quad;
		if (r < half && c < half) {
			quad = 0; // 좌상
		} else if (r < half && c >= half) {
			quad = 1; // 우상
		} else if (r >= half && c < half) {
			quad = 2; // 좌하
		} else {
			quad = 3; // 우하
		}

		idx += quad * half * half;

		// 좌표축소
		int nr = r % half;
		int nc = c % half;

		return solve(N - 1, nr, nc, idx);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); 
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		System.out.println(solve(N, r, c, 0));
	}
}