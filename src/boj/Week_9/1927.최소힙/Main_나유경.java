import java.io.*;
import java.util.*;

// 최소 힙  
// 값의 크기대로 꺼내는 PriorityQueue
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) { // 0 일 때
				if (pq.isEmpty()) {
					sb.append("0\n");
				} else {// 자연수 일 때
					sb.append(pq.poll()).append("\n");
				}
			} else {
				pq.add(x); // 힙에 값 추가
			}
		}
		
		// 출력
		System.out.println(sb);

	}
}