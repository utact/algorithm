import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김준이 {

	static int N;
	static int[][] map;
	static int max, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		slideBlock(0, map);

		System.out.println(ans);
	}// main

	static void slideBlock(int n, int[][] arr) {
		// 최대 5번 이동
		if (n == 5) {
			findMax(arr);
			return;
		}

		int[][] next;
		next = moveUp(copyArr(arr));
		slideBlock(n + 1, next);

		next = moveDown(copyArr(arr));
		slideBlock(n + 1, next);

		next = moveLeft(copyArr(arr));
		slideBlock(n + 1, next);

		next = moveRight(copyArr(arr));
		slideBlock(n + 1, next);
	}

	// 블록 위로 밀기
	static int[][] moveUp(int[][] arr) {
		Queue<Integer> q = new LinkedList<>();

		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if (arr[i][j] == 0)
					continue;
				q.add(arr[i][j]);
				arr[i][j] = 0;
			}

			int idx = 0;
			boolean merged = false;

			if (!q.isEmpty()) {
				arr[idx][j] = q.poll();
			}

			while (!q.isEmpty()) {
				int curr = q.poll();
				if (arr[idx][j] == curr && !merged) {
					arr[idx][j] *= 2;
					merged = true;
				} else {
					arr[++idx][j] = curr;
					merged = false;
				}
			} // while
		}

		return arr;
	}

	// 블록 아래로 밀기
	static int[][] moveDown(int[][] arr) {
		Queue<Integer> q = new LinkedList<>();

		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j] == 0)
					continue;
				q.add(arr[i][j]);
				arr[i][j] = 0;
			}

			int idx = N - 1;
			boolean merged = false;

			if (!q.isEmpty()) {
				arr[idx][j] = q.poll();
			}

			while (!q.isEmpty()) {
				int curr = q.poll();
				if (arr[idx][j] == curr && !merged) {
					arr[idx][j] *= 2;
					merged = true;
				} else {
					arr[--idx][j] = curr;
					merged = false;
				}
			}
		}

		return arr;
	}

	// 블록 왼쪽으로 밀기
	static int[][] moveLeft(int[][] arr) {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0)
					continue;
				q.add(arr[i][j]);
				arr[i][j] = 0;
			}

			int idx = 0;
			boolean merged = false;

			if (!q.isEmpty()) {
				arr[i][idx] = q.poll();
			}

			while (!q.isEmpty()) {
				int curr = q.poll();
				if (arr[i][idx] == curr && !merged) {
					arr[i][idx] *= 2;
					merged = true;
				} else {
					arr[i][++idx] = curr;
					merged = false;
				}
			}
		}

		return arr;
	}

	// 오른쪽으로 밀기
	static int[][] moveRight(int[][] arr) {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (arr[i][j] == 0)
					continue;
				q.add(arr[i][j]);
				arr[i][j] = 0;
			}

			int idx = N - 1;
			boolean merged = false;

			if (!q.isEmpty()) {
				arr[i][idx] = q.poll();
			}

			while (!q.isEmpty()) {
				int curr = q.poll();
				if (arr[i][idx] == curr && !merged) {
					arr[i][idx] *= 2;
					merged = true;
				} else {
					arr[i][--idx] = curr;
					merged = false;
				}
			}
		}

		return arr;
	}

	// 가장 큰 블록 찾아서 갱신하기
	static void findMax(int[][] arr) {
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}

		if (max > ans)
			ans = max;

	}// findMax

	static int[][] copyArr(int[][] arr) {
		int[][] copied = new int[N][N];
		for (int i = 0; i < N; i++) {
			copied[i] = arr[i].clone();
		}

		return copied;
	}
}