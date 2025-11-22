import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * x가 자연수이면 삽입
 * x가 0이면 최솟값 (루트 노드) 출력 후 삭제 
 */

public class Main_김민정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) { // 루트 출력 후 삭제 (비어있으면 0 출력)
				if (pq.isEmpty())
					sb.append(0);
				else
					sb.append(pq.poll());

				sb.append("\n");
			} else { // 삽입
				pq.add(x);
			}

		} // 총 N번의 연산 수행

		System.out.println(sb);	//정답 출력 
	}
}
