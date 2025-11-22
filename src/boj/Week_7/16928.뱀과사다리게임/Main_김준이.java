import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 사다리의 수
		int M = Integer.parseInt(st.nextToken()); // 뱀의 수

		int[] map = new int[101]; // 뱀사다리 정보 저장
		int[] dist = new int[101]; // 주사위 횟수 저장(bfs 거리)

		Arrays.fill(dist, -1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // x번 칸에 도착하면
			int y = Integer.parseInt(st.nextToken()); // y로 이동한다
			map[x] = y;

		} // 사다리 정보 입력

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // u번 칸에 도착하면
			int v = Integer.parseInt(st.nextToken()); // v로 이동한다
			map[u] = v;
		} // 뱀 정보 입력

		Queue<Integer> q = new LinkedList<>();
		dist[1] = 0;
		q.add(1);

		while (!q.isEmpty()) {
			int curr = q.poll();
			if (curr == 100)
				break;

			// 주사위 던져서 갈 수 있는 곳 모두 큐에 삽입
			for (int i = 1; i <= 6; i++) {
				int next = curr + i; // 다음 주사위 던저셔 갈 곳

				// 유효 인덱스 체크 + 방문 체크
				if (next > 100 || dist[next] != -1)
					continue;

				// 뱀과 사다리가 있으면 주사위 카운트 없이 바로 이동
				if (map[next] != 0) {
					next = map[next];
				}

				// 최종 결정된 다음위치가 방문하지 않은 곳이면
				if (dist[next] == -1) {
					dist[next] = dist[curr] + 1;
					q.add(next);
				}
			}
		} // bfs

		System.out.println(dist[100]);
	}// main

}