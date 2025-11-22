import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, M, minResult;
	static List<Point> location;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Point{
		int x, y, type;

		public Point(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		location = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5)
					location.add(new Point(i, j, map[i][j]));
			}
		}
		
		minResult = Integer.MAX_VALUE;
		findDirection(0);
		
		System.out.println(minResult);
	}
	
	static void findDirection(int idx) {
		if(idx == location.size()) {
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0)
						result++;
				}
			}
			minResult = Math.min(minResult, result);
			return;
		}
		
		Point curr = location.get(idx);
		int r = curr.x;
		int c = curr.y;
		
		switch(curr.type) {
			case 1: //상, 하, 좌, 우
				for (int i = 0; i < 4; i++) {
					check(r, c, i, 7);
					findDirection(idx + 1);
					check(r, c, i, 0);
				}
				break;
			case 2: //좌우, 상하
				for (int i = 0; i < 2; i++) {
					if(i == 0) {
						check(r, c, 2, 7);
						check(r, c, 3, 7);
					}else {
						check(r, c, 0, 7);
						check(r, c, 1, 7);
					}
					findDirection(idx+1);
					if(i == 0) {
						check(r, c, 2, 0);
						check(r, c, 3, 0);
					}else {
						check(r, c, 0, 0);
						check(r, c, 1, 0);
					}
				}
				break;
			case 3: //상우, 하우, 하좌, 상좌
				for (int i = 0; i < 4; i++) {
					if(i == 0) {
						check(r, c, 0, 7);
						check(r, c, 3, 7);
					}else if(i == 1){
						check(r, c, 1, 7);
						check(r, c, 3, 7);
					}else if(i == 2){
						check(r, c, 1, 7);
						check(r, c, 2, 7);
					}else{
						check(r, c, 0, 7);
						check(r, c, 2, 7);
					}
					findDirection(idx+1);
					if(i == 0) {
						check(r, c, 0, 0);
						check(r, c, 3, 0);
					}else if(i == 1){
						check(r, c, 1, 0);
						check(r, c, 3, 0);
					}else if(i == 2){
						check(r, c, 1, 0);
						check(r, c, 2, 0);
					}else{
						check(r, c, 0, 0);
						check(r, c, 2, 0);
					}
				}
				break;
			case 4: //좌상우, 상우하, 우하좌, 하좌상
				for (int i = 0; i < 4; i++) {
					if(i == 0) {
						check(r, c, 0, 7);
						check(r, c, 2, 7);
						check(r, c, 3, 7);
					}else if(i == 1){
						check(r, c, 0, 7);
						check(r, c, 1, 7);
						check(r, c, 3, 7);
					}else if(i == 2){
						check(r, c, 1, 7);
						check(r, c, 2, 7);
						check(r, c, 3, 7);
					}else{
						check(r, c, 0, 7);
						check(r, c, 1, 7);
						check(r, c, 2, 7);
					}
					findDirection(idx+1);
					if(i == 0) {
						check(r, c, 0, 0);
						check(r, c, 2, 0);
						check(r, c, 3, 0);
					}else if(i == 1){
						check(r, c, 0, 0);
						check(r, c, 1, 0);
						check(r, c, 3, 0);
					}else if(i == 2){
						check(r, c, 1, 0);
						check(r, c, 2, 0);
						check(r, c, 3, 0);
					}else{
						check(r, c, 0, 0);
						check(r, c, 1, 0);
						check(r, c, 2, 0);
					}
				}
				break;
			case 5: //상하좌우
					check(r, c, 0, 7);
					check(r, c, 1, 7);
					check(r, c, 2, 7);
					check(r, c, 3, 7);
					findDirection(idx+1);
					check(r, c, 0, 0);
					check(r, c, 1, 0);
					check(r, c, 2, 0);
					check(r, c, 3, 0);
				break;
		}
	}
	
	static void check(int nr, int nc, int dir, int value) {
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) break;
			if(map[nr][nc] == 6) break;
			if(map[nr][nc] >= 1 && map[nr][nc] <= 5) continue;
			if(value == 7) {
				if(map[nr][nc] == 0)
					map[nr][nc] = 7;
				else if(map[nr][nc] >= 7) 
					map[nr][nc]++;
			}else {
				if(map[nr][nc] == 7)
					map[nr][nc] = 0;
				else if(map[nr][nc] > 7)
					map[nr][nc]--;
			}
		}
	}
}
