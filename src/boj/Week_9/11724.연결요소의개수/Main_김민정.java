import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 무방향 그래프 -> 연결 요소 개수 구하기 (서로소 집합 개수 구한다는 건가?)
 * 
 * N : 정점 개수. M: 간선 개수
 * Union-Find 쓰면 될 듯?
 * 
 */

public class Main_김민정 {
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		} // make set

		// m개 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			unionSet(u, v);
		}

		// 서로소 집합 개수 구하기
		// Set에 넣장
		Set<Integer> pSet = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			pSet.add(findSet(i));
		}

		System.out.println(pSet.size());

	}// main

	// union set
	static void unionSet(int u, int v) {
		p[findSet(u)] = p[findSet(v)];
	}

	// find set
	static int findSet(int x) {
		if (p[x] != x)
			return findSet(p[x]);
		else
			return x;
	}

}
