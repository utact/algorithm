import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int X = Integer.parseInt(br.readLine());
		int ans = 0;

		// 방문체크 안 했더니 메모리초과
		// 어떤 숫자에 처음 도달했을 때의 연산 횟수가 곧 최소 횟수
		boolean[] visited = new boolean[1000001];

		// bfs로 /3, /2, -1의 경우를 모두 진행하면서 1이 먼저 나오면 끝
		Queue<int[]> q = new LinkedList<>();

		if (X % 3 == 0) {
			q.add(new int[] { X / 3, 1 });
			visited[X / 3] = true;
		}
		if (X % 2 == 0) {
			q.add(new int[] { X / 2, 1 });
			visited[X / 2] = true;
		}
		q.add(new int[] { X - 1, 1 });
		visited[X - 1] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll(); // 현재 연산 대상

			if (curr[0] == 1) {
				ans = curr[1];
				break;
			}
			
			int tmp1 = curr[0] / 3;
			int tmp2 = curr[0] / 2;
			int tmp3 = curr[0] - 1;
			
			if (curr[0] % 3 == 0 && !visited[tmp1]) {
				q.add(new int[] { tmp1, curr[1] + 1 });
				visited[tmp1] = true;
			}
			if (curr[0] % 2 == 0 && !visited[tmp2]) {
				q.add(new int[] { tmp2, curr[1] + 1 });
				visited[tmp2] = true;
			}

			if (tmp3 > 0 && !visited[tmp3]) {
				q.add(new int[] { tmp3, curr[1] + 1 });
				visited[tmp3] = true;
			}

		}

		System.out.println(ans);

	}// main

}
