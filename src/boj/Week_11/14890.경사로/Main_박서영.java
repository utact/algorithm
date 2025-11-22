import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, L, roadCnt; //지도 크기, 경사로의 길이, 지나갈 수 있는 길의 개수
	static int[][] map; //지도 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//지도를 돌면서 0,0일때 가로/세로 확인, x좌표가 0일때 세로 확인, y좌표가 0일때 가로 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == 0){
					if(j == 0)
						checkRow(i, j);
					checkColumn(i, j);
				}else if(j == 0)
					checkRow(i, j);
			}
		}
		
		System.out.println(roadCnt);
	}
	
	//가로줄 확인하는 함수
	static void checkRow(int x, int y) {
		int length = 1; //같은 높이의 연속된 칸의 길이
		boolean isPossible = true; //길을 지나갈 수 있는지 여부
		boolean[] visited = new boolean[N]; //경사로를 놓았는지 확인하는 방문체크 배열
		
		exit:
		for (int i = 1; i < N; i++) {
			if(map[x][y+i-1] == map[x][y+i]) { //이전 칸과 같은 숫자일 때(평지)
				length++;
			}else if(map[x][y+i-1] - map[x][y+i] == 1) { //이전 칸보다 1이 작을 때(내리막)
				if(y+i-1+L >= N) { //경사로를 놓을 만큼 칸이 있어야 함
					isPossible = false;
					break;
				}else {
					for (int j = 1; j < L; j++) { //같은 높이의 칸이 현재 칸부터 L개 있어야 함
						if(map[x][y+i+j] != map[x][y+i]) {
							isPossible = false;
							break exit;
						}
					}
				}
				length = 1; //모든 조건 통과했다면 길이 카운트 1로 초기화
				for (int j = 0; j < L; j++) { //경사로를 놓은 칸은 방문체크
					visited[i+j] = true;
				}
			}else if(map[x][y+i-1] - map[x][y+i] == -1) { //이전 칸보다 1이 클 때(오르막)
				if(length < L) { //이전까지 카운트했던 같은 높이의 연속된 칸의 길이가 경사로의 길이보다 커야 함
					isPossible = false;
					break;
				}else { //이전에 경사로를 놓은 적이 있는 칸에는 경사로를 놓을 수 없음
					if(visited[i-L]) {
						isPossible = false;
						break;
					}
					length = 1; //모든 조건 통과했다면 길이 카운트 1로 초기화
				}
			}else { //칸의 높이 차가 2 이상인 경우
				isPossible = false;
				break;
			}
		}
		
		//길을 건널 수 있으면 카운트 올리기
		if(isPossible) 
			roadCnt++;
	}
	
	//세로줄 확인하는 함수
	//가로줄과 x/y만 다르고 다 똑같음
	static void checkColumn(int x, int y) {
		int length = 1;
		boolean isPossible = true;
		boolean[] visited = new boolean[N];
		
		exit:
		for (int i = 1; i < N; i++) {
			if(map[x+i-1][y] == map[x+i][y]) {
				length++;
			}else if(map[x+i-1][y] - map[x+i][y] == 1) {
				if(x+i-1+L >= N) {
					isPossible = false;
					break;
				}else {
					for (int j = 1; j < L; j++) {
						if(map[x+i+j][y] != map[x+i][y]) {
							isPossible = false;
							break exit;
						}
					}
				}
				length = 1;
				for (int j = 0; j < L; j++) {
					visited[i+j] = true;
				}
			}else if(map[x+i-1][y] - map[x+i][y] == -1) {
				if(length < L) {
					isPossible = false;
					break;
				}else {
					if(visited[i-L]) {
						isPossible = false;
						break;
					}
					length = 1;
				}
			}else {
				isPossible = false;
				break;
			}
		}
		
		if(isPossible) 
			roadCnt++;
	}
}
