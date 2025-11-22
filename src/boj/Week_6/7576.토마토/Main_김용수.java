import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_김용수 {
	static int M, N,day;
	static int[][] box;
	static List<int[]> start;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		M = sc.nextInt(); // 가로
		N = sc.nextInt(); // 세로
		box = new int[N][M];
		start = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				box[i][j] = sc.nextInt();
				if (box[i][j] == 1) {
					start.add(new int[] { i, j });
				}
			}
		}

		// 익은 토마토로 부터 확장
		day = 0;
		bfs();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					day = -1;
					break;
				}
			}
		}
		
		System.out.println(day);

	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		for (int s = 0; s < start.size(); s++) {
			q.add(new int[] {start.get(s)[0], start.get(s)[1],0});
		}
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			day = curr[2];
			for (int k = 0; k < 4; k++) {
				int nr = curr[0] + dr[k];
				int nc = curr[1] + dc[k];
				
				if(nr<0||nc<0||nr>=N||nc>=M)
					continue;
				if(box[nr][nc]==0) {
					box[nr][nc] = 1;
					q.add(new int[] {nr,nc,day+1});
				}
			}
		}

	}
}
