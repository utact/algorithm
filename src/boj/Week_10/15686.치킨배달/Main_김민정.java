import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 0: 빈칸, 1: 집, 2: 치킨집
 */

class Point {
	int r, c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

}

public class Main_김민정 {
	static int N, M; // N: 도시 배열의 크기, M : 뽑은 치킨집 개수
	static int ans = Integer.MAX_VALUE;
	static int[][] map; // 도시 정보
	static int[][] dist;// 거리 정보
	static List<Point> chicken = new ArrayList<>(); // 치킨 집 좌표
	static List<List<Point>> combList = new ArrayList<>();// 치킨 집 M개 뽑은 조합

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 2) // 치킨 집 좌표 넣음
					chicken.add(new Point(i, j));

				map[i][j] = n;
			}
		} // 입력 끝

		// 조합 생성
		backtrack(0, new ArrayList<>());

		// 조합 뽑아내면서 치킨 거리 계산
		for (List<Point> comb : combList) {
			dist = new int[N][N];
			// 거리배열 초기화
			for (int i = 0; i < N; i++) 
				Arrays.fill(dist[i], Integer.MAX_VALUE);

			// 조합 케이스 뽑아내기
			for (Point chicken : comb) {
				int r = chicken.r;
				int c = chicken.c;

				// 치킨 거리 배열 계산
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 1) {
							int d = Math.abs(r - i) + Math.abs(c - j);
							dist[i][j] = Math.min(dist[i][j], d);
						}
					}
				}


			}
			
			// 조합 케이스 별 치킨 거리 합 계산
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (dist[i][j] != Integer.MAX_VALUE) {
						sum += dist[i][j];
					}
				}
			}
			
			//정답 갱신
			ans = Math.min(ans, sum);

		} // 전체 조합 케이스 탐색

		System.out.println(ans);

	}// main

	
	static void backtrack(int start, List<Point> path) {
		if (path.size() == M) {
			combList.add(new ArrayList<>(path));
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			path.add(chicken.get(i));
			backtrack(i + 1, path);
			path.remove(path.size() - 1);
		}
	}// 조합

}