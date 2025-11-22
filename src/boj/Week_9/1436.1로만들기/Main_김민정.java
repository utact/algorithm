import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * 
 * 최소 연산으로 1 만들때 연산의 수행 횟수 출력 
 * 
 * BFS일까 ....? 
 * visited를 해볼까..... 일단ㄴ 크기는 N+1로 선언 ㄱ
 */

public class Main_김민정 {
	static int N;		//입력 변수
	static int[] dist;	//방문 체크 겸 레벨 계산 배열 
	static int ans;		//최소 연산 수행 횟수 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		if (N == 1) {	//입력 1이면 조기 종료 
			System.out.println(0);
			return;
		}

		ans = 0;
		dist = new int[N + 1];
		BFS(N);

		System.out.println(ans);

	}// main

	static void BFS(int v) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(v);
		
		
		while (!q.isEmpty()) {
			int curr = q.poll();

			if (curr == 1) {
				ans = dist[curr];
				return;
			}

			for (int i = 1; i <= 3; i++) {
				int n = 0;
				switch (i) {
				case 1:
					n = curr - 1;
					if (n > 0 && dist[n]==0) {
						q.add(n);
						dist[n] = dist[curr] + 1;
					}
					break;
				case 2:
					n = curr / 2;
					if (curr % 2 == 0 && n > 0 && dist[n]==0) {
						q.add(n);
						dist[n] = dist[curr] + 1;
					}
					break;
				case 3:
					n = curr / 3;
					if (curr % 3 == 0 && n > 0 && dist[n]==0) {
						q.add(n);
						dist[n] = dist[curr] + 1;
					}
					break;
				}//switch 문
				
			}//연산 1-3 수행

		}

	}
}
