import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//15686_치킨배달 
//DFS
//치킨집 위치 리스트에서 M개를 조합으로 선택하고, 
//선택된 조합마다 도시 치킨 거리(집 -> 가장 가까운 치킨거리합)을 계산하여 최소값을 찾기 
public class Main_나유경 {

	static int N; // 맵 크기
	static int M; // 치킨 집 선택 개수
	static int[][] map;
	static List<int[]> houses = new ArrayList<>();
	static List<int[]> chickens = new ArrayList<>();
	static int[] selected; // chickens 리스트의 index
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) // 집 위치 저장
					houses.add(new int[] { i, j });
				if (map[i][j] == 2) // 치킨 집 위치 저장
					chickens.add(new int[] { i, j });
			}
		}

		selected = new int[M];

		dfs(0, 0);
		System.out.println(ans);

	}

	// depth: 현재선택개수, start: 다음 탐색 시작 인덱스
	private static void dfs(int depth, int start) {

		// M개 선택 완료하면 거리 계산
		if (depth == M) {
			calc();
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			selected[depth] = i; // 이번 조합에서 사용할 치킨집 index기록 
			dfs(depth + 1, i + 1); // 다음선택
		}

	}

	private static void calc() {
		int sum = 0; // 이번 조합에서의 도시 치킨 거리

		for (int[] house : houses) {
			int minDist = Integer.MAX_VALUE; // 이 집 최소 기준 치킨 거리
			
			// 선택 된 치킨집 M개 중 거리 최소값 구하기
			for (int idx : selected) {
				int[] chicken = chickens.get(idx);
				int dist = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]); // |r1-r2|+|c1-c2|
				minDist = Math.min(minDist, dist); // 이 집에대한 가장 가까운 치킨 집 거리 갱신 
			}
			sum += minDist; // 각 집의 최소 거리 누적
		}
		ans = Math.min(ans, sum);// 최소정답갱신
	}

}
