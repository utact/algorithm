import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김준이 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static int N;
	static int[][] map, dist;
	static int sharkSize;
	static int totalDist;
	static int eaten;
	static List<Pos> fishList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		N = Integer.parseInt(br.readLine()); // 공간의 크기

		map = new int[N][N];
		sharkSize = 2;
		totalDist = 0;
		eaten = 0;

		int r = -1;
		int c = -1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					r = i;
					c = j;
				}
			}
		} // 물고기 정보 입력 끝

		Pos p = new Pos(r, c);
		Pos nextPos;

		while (true) {
			findTarget(p.r, p.c);
			
			if (fishList.size() == 0) {
				break;
			} else {
				nextPos = eatFish();
			}
			p = nextPos;
		}

		System.out.println(totalDist);
	}// main

	// 현위치에서 다음에 먹을 물고기 찾기 - bfs
	static void findTarget(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		fishList = new ArrayList<>();
		int minDist = Integer.MAX_VALUE;

		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			int[] tmp = dist[i];
			Arrays.fill(tmp, -1);
		}

		q.add(new Pos(r, c));
		dist[r][c] = 0;

		while (!q.isEmpty()) {
			Pos curr = q.poll();

			// 작은 물고기면 후보 리스트에 넣기
			if (map[curr.r][curr.c] > 0 && map[curr.r][curr.c] < sharkSize) {
				if (fishList.isEmpty())
					minDist = dist[curr.r][curr.c];

				if (minDist < dist[curr.r][curr.c])
					break;

				fishList.add(curr);
			}

			for (int k = 0; k < 4; k++) {
				int nr = curr.r + dr[k];
				int nc = curr.c + dc[k];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N) // 유효 인덱스
					continue;
				if (map[nr][nc] > sharkSize || dist[nr][nc] > -1) // 아기상어보다 크거나 방문한 곳
					continue;

				dist[nr][nc] = dist[curr.r][curr.c] + 1;
				q.add(new Pos(nr, nc));

			}
		} // while

	}

	static Pos eatFish() {
		fishList.sort((a, b) -> {
			if (a.r != b.r)
				return Integer.compare(a.r, b.r);
			else
				return Integer.compare(a.c, b.c);
		});

		Pos target = fishList.get(0);

		map[target.r][target.c] = 0; // 먹고
		eaten++;

		totalDist += dist[target.r][target.c]; // 거리 업데이트

		fishList.clear(); // fishList 초기화

		if (eaten == sharkSize) { // 상어크기가 커지면 키워주기
			sharkSize++;
			eaten = 0;
		}

		return target;
	}

}
