import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS..?
 */

public class Main_김민정 {
	static int L, S; // 사다리 수, 뱀 수
	static int[] map = new int[101];
	static boolean[] visited = new boolean[101];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		for (int i = 0; i < L + S; i++) {
			st = new StringTokenizer(br.readLine());
			int curr = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());

			map[curr] = next;
		} // 사다리, 뱀 정보 입력
		
		ans = 0;
		bfs(1);
		System.out.println(ans);

	}// main

	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		int cnt = 0;
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int size = q.size();
			//큐의 사이즈만큼 돌림 => 같은 층 
			for(int s=0; s<size; s++) {
				int curr = q.poll();
				// 종료 조건(100 만나면)
				if(curr == 100) return;
				//주사위 던짐 
				for(int i=1; i<=6; i++) {
					int next = curr + i;
					if(next<=100) {
						if(map[next] != 0 && !visited[map[next]]) {//뱀 혹은 사다리이면
							q.add(map[next]);
							visited[map[next]] = true;
						}else if(map[next] == 0 && !visited[next]){
							q.add(next);
							visited[next] = true;
						}
					}else {
						break;	// 100이상이면 for문 나옴 
					}
				}//주사위 던짐 완료 
			}//같은 층의 모든 노드 탐색 완료 
			ans++;

		}//while

	}
}