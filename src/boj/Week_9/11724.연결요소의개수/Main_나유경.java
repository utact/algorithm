import java.io.*;
import java.util.*;

// 연결 요소의 개수
// 방향 없는 그래프 
public class Main {

	static int N, M; // 정점 수, 간선 수
	static int count; // 연결요소 의 갯수
	static boolean visited[];
	static List<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}

		visited = new boolean[N + 1];
		count = 0;

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				count++;
			}
		}
		
		System.out.println(count);

	}

	private static void dfs(int node) {
		visited[node] = true;

		for (int next : graph[node]) {
			if (!visited[next]) {
				dfs(next);
			}
		}

	}
}
