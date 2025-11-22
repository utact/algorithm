import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // 정점, 간선
	static List<Integer>[] graph;
	static boolean[] visited;

	static int ans; // connected component 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 그래프 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		visited = new boolean[N + 1];
		ans = 0;

		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			bfs(i);
			ans++;
		}

		System.out.println(ans);
	}// main

	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();

		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int i = 0; i < graph[curr].size(); i++) {
				int tmp = graph[curr].get(i);
				if (visited[tmp]) {
					continue;
				}

				q.add(tmp);
				visited[tmp] = true;
			}
		}
	}

}
