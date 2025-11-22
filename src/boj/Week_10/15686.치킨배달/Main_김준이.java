import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_김준이 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static int N, M;
	static List<Pos> homeList, chickenList;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 최대 치킨집 개수

		arr = new int[N][N];
		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					homeList.add(new Pos(i, j));
				}
				if (arr[i][j] == 2) {
					chickenList.add(new Pos(i, j));
				}

			}
		}

		if (chickenList.size() > M) {
			comb(0, 0, new ArrayList<Pos>());
		} else {
			calDist(chickenList);
		}

		System.out.println(min);

	}// main

	// 치킨집 조합 생성해서 -> 조합 M개 완성되면 치킨거리 계산
	static void comb(int start, int cnt, List<Pos> selected) {
		if (cnt == M) {
			calDist(selected);
			return;
		}

		// 치킨집 조합 생성
		for (int i = start; i < chickenList.size(); i++) {
			selected.add(chickenList.get(i));
			comb(i + 1, cnt + 1, selected);
			selected.remove(selected.size() - 1);
		}
	}

	// 각 집에서 치킨집까지 최소 거리를 계산
	static void calDist(List<Pos> selected) {
		int sum = 0;
		for (Pos home : homeList) {
			int dist = Integer.MAX_VALUE;
			for (Pos chick : selected) {
				dist = Math.min(dist, Math.abs(home.r - chick.r) + Math.abs(home.c - chick.c));
			}
			sum += dist;
		}

		min = Math.min(min, sum);
		return;
	}

}
