import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_김용수 {
	static int V, E;
	static List<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();
		E = sc.nextInt();

		adjList = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<Integer>();
		} // 초기화

		for (int i = 1; i < E + 1; i++) {
			int s = sc.nextInt();
			int e = sc.nextInt();

			adjList[s].add(e);
			adjList[e].add(s);
		}
		
		int cnt = 0;
		
		for (int i = 1; i<V+1; i++)	{
			if(!visited[i]) {
				cnt++;
				dfs(i);
			}
		}
		
		System.out.println(cnt);

	}//main
	
	static void dfs(int v) {
		if (visited[v]) {
			return;
		}
		
		visited[v] =true;
		for(int i: adjList[v]) {
			if (!visited[i])
				dfs(i);
		}
	}//dfs
}
