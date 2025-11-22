
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int[] board;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		board = new int[101]; // 1베이스
		for (int i = 0; i < N + M; i++) {
			board[sc.nextInt()] = sc.nextInt();
		} // 사다리, 뱀

		visited = new boolean[101];

		System.out.println(bfs(1));

	}

	static int bfs(int v) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { v, 0 }); // 위치와 주사위 굴린 횟수저장
		visited[v] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == 100)
				return cur[1];

			for (int i = 1; i <= 6; i++) { // 주사위 눈금
				int nv = cur[0] + i;
				if (nv>100)
					continue;
				if (!visited[nv]) {
					if (board[nv] > 0) // 사다리나 뱀.
						q.add(new int[] { board[nv], cur[1] + 1 });
					else
						q.add(new int[] { nv, cur[1] + 1 });
					visited[nv] = true;
				}
			}

		}
		return -1;

	}
}