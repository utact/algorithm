import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, maxBlock;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(map, 0);
		
		System.out.println(maxBlock);
	}
	
	//재귀함수 
	static void dfs(int[][] nowMap, int cnt){
		if(cnt == 5) { //5번 움직였으면 2차원 배열 돌면서 최댓값 찾아서 저장 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maxBlock = Math.max(maxBlock, nowMap[i][j]);
				}
			}
			return;
		}
		
		//4방향으로 모두 움직여보기 
		dfs(move(nowMap, 0), cnt+1); //상  
		dfs(move(nowMap, 1), cnt+1); //우
		dfs(move(nowMap, 2), cnt+1); //좌 
		dfs(move(nowMap, 3), cnt+1); //하  
	}
	
	//이동, 합치기 함수 -> 만들어진 2차원 배열 리턴
	static int[][] move(int[][] originalMap, int direction){
		//원본은 그대로 두고 새 2차원 배열에 이동 및 합친 결과 저장
		int[][] nextMap = new int[N][N];
		Queue<Integer> q = new ArrayDeque<>();
		
		if(direction == 2) { //왼쪽밀기(기본)
			for (int i = 0; i < N; i++) { //행 우선, 왼쪽부터 큐에 넣기
				for (int j = 0; j < N; j++) {
					if(originalMap[i][j] != 0) //0이 아닌 숫자 큐에 넣기(이동)
						q.add(originalMap[i][j]);
				}
				//같은 수 합치기 
				int idx = 0; 
				while(!q.isEmpty()) { //큐가 빌 때까지(남은 숫자가 없을 때까지 돌리기  
					int block = q.poll(); //큐에서 하나 꺼내기 
					if(q.isEmpty()) { //하나 꺼내고 큐가 비면 바로 2차원배열에 저장하고 while문 탈출
						nextMap[i][idx++] = block;
						break;
					}
					//큐가 비지 않았으면 다음 수 꺼내서 현재 수와 같은지 보고 같으면 더해서 2차원배열에 저장, 다르면 그대로 저장
					if(q.peek() == block) {
						nextMap[i][idx++] = block*2;
						q.poll();
					}else {
						nextMap[i][idx++] = block;
					}
				}
			}
		}else if(direction == 0) { //위쪽밀기 
			for (int j = 0; j < N; j++) { //열 우선, 위쪽부터 큐에 넣기 
				for (int i = 0; i < N; i++) {
					if(originalMap[i][j] != 0)
						q.add(originalMap[i][j]);
				}
				int idx = 0;
				while(!q.isEmpty()) {
					int block = q.poll();
					if(q.isEmpty()) {
						nextMap[idx++][j] = block;
						break;
					}
					if(q.peek() == block) {
						nextMap[idx++][j] = block*2;
						q.poll();
					}else {
						nextMap[idx++][j] = block;
					}
				}
			}
		}else if(direction == 1) { //오른쪽밀기  
			for (int i = 0; i < N; i++) { //행 우선, 오른쪽부터 큐에 넣기 
				for (int j = N-1; j >= 0; j--) {
					if(originalMap[i][j] != 0)
						q.add(originalMap[i][j]);
				}
				int idx = N-1;
				while(!q.isEmpty()) {
					int block = q.poll();
					if(q.isEmpty()) {
						nextMap[i][idx--] = block;
						break;
					}
					if(q.peek() == block) {
						nextMap[i][idx--] = block*2;
						q.poll();
					}else {
						nextMap[i][idx--] = block;
					}
				}
			}
		}else { //아래쪽밀기 
			for (int j = 0; j < N; j++) { //열 우선, 아래쪽부터 큐에 넣기 
				for (int i = N-1; i >= 0; i--) {
					if(originalMap[i][j] != 0)
						q.add(originalMap[i][j]);
				}
				int idx = N-1;
				while(!q.isEmpty()) {
					int block = q.poll();
					if(q.isEmpty()) {
						nextMap[idx--][j] = block;
						break;
					}
					if(q.peek() == block) {
						nextMap[idx--][j] = block*2;
						q.poll();
					}else {
						nextMap[idx--][j] = block;
					}
				}
			}
		}
		return nextMap;
	}
}
