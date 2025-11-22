import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] adj = new int[101]; //인접 배열
		
		for (int i = 0; i < N; i++) { //사다리 입력받아서 인접 배열에 넣기
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a] = b;
		}
		
		for (int i = 0; i < M; i++) { //뱀 입력받아서 인접 배열에 넣기
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj[a] = b;
		}
		
		int[] move = new int[101]; //주사위 굴리는 횟수 겸 방문 체크 배열
		Queue<Integer> q = new ArrayDeque<>(); //bfs용 큐
		//시작지점 큐에 넣고 방문체크(1부터 시작, 나중에 결과에서 1 빼기)
		q.add(1);
		move[1] = 1;
		
		while(!q.isEmpty()) {
			int curr = q.poll(); //큐에서 하나 뽑기
			
			//주사위 굴려서 갈 수 있는 모든 지점 확인
			for (int i = 1; i <= 6; i++) {
				int nx = curr + i;
				if(nx >= 101) continue; //범위 체크
				if(adj[nx] > 0) //뱀 또는 사다리일 경우 nx를 이동한 칸으로 대체
					nx = adj[nx];
				if(move[nx] == 0) { //방문하지 않았을 경우 큐에 넣고 주사위 굴린 횟수 1 더하기
					q.add(nx);
					move[nx] = move[curr] + 1;
				}
			}
			
			if(move[100] > 0) break; //100 자리에 방문했을 경우 종료
		}
		
		System.out.println(move[100] - 1); //방문체크용으로 카운트를 1부터 시작했으므로 1 빼기
	}
}
