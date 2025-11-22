import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 15683_감시
// DFS + 시뮬레이션 + 백트래킹
// CCTV 마다 가능한 방향 조합을 DFS로 모두 탐색하며, 
// 각 조합마다 감시 구역을 표시하고 사각지대(0)를 계산하여 최소값(ans) 갱신
public class Main_나유경 {

	static int M, N; // 세로 , 가로
	static int[][] map;
	static int ans = Integer.MAX_VALUE; // 최소값

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	// CCTV
	static List<CCTV> cctvs = new ArrayList<>();

	static class CCTV {
		int r, c, type;

		CCTV(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}

	// CCTV 타입 별 감시 가능한 방향 조합
	static int[][][] DIR = { {}, // 0번 dummy
			{ { 0 }, { 1 }, { 2 }, { 3 } }, // 1번
			{ { 0, 1 }, { 2, 3 } }, // 2번
			{ { 0, 3 }, { 3, 1 }, { 1, 2 }, { 2, 0 } }, // 3번
			{ { 0, 3, 1 }, { 3, 1, 2 }, { 1, 2, 0 }, { 2, 0, 3 } }, // 4번
			{ { 0, 1, 2, 3 } } // 5번
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctvs.add(new CCTV(i, j, map[i][j])); // CCTV수집
				}
			}
		}

		dfs(0, copyMap(map));

		System.out.println(ans);
	}

	// index번 째 CCTV 방향 선택
	static void dfs(int idx, int[][] curMap) {
		// CCTV 전부 방향 설정 했으면 사각지대 계산
		if (idx == cctvs.size()) {
			ans = Math.min(ans, countBlindSpot(curMap));
			return;
		}

		CCTV cctv = cctvs.get(idx);
		int type = cctv.type;

		for (int[] dirs : DIR[type]) {
			int[][] tempMap = copyMap(curMap);

			for (int d : dirs) {
				watch(tempMap, cctv.r, cctv.c, d);
			}

			dfs(idx + 1, tempMap); // 다음 CCTV 처리

		}

	}

	// 백트래킹 하기 위한 맵 복사
	static int[][] copyMap(int[][] origin) {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = origin[i].clone();
		}
		return copy;
	}

	// 사각지대 카운트
	private static int countBlindSpot(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	// 특정 방향으로 감시 표시
	static void watch(int[][] map, int r, int c, int d) {
		int nr = r;
		int nc = c;

		while (true) {
			nr += dr[d];
			nc += dc[d];

			// 경계
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				break;

			// 벽
			if (map[nr][nc] == 6)
				break;

			// 감시표기
			if (map[nr][nc] == 0)
				map[nr][nc] = 7;

		}

	}

}
