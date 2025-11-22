import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_김용수 {
	static int N, w = 0, b = 0;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력

		dfs(0, 0, N);// 크기를 포함해서 dfs
		System.out.println(w);
		System.out.println(b);

	}

	static void dfs(int r, int c, int len) {
		int color = colorcheck(r, c, len);
		if (color == 0)
			w++;
		else if (color == 1)
			b++;
		else {
			int h = len / 2;
			dfs(r, c, h);
			dfs(r, c + h, h);
			dfs(r + h, c, h);
			dfs(r + h, c + h, h);
		}
	}

	static int colorcheck(int r, int c, int len) {
		int start = map[r][c];
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (map[i][j] != start)
					return -1;
			}
		}
		return start;
	}
}
