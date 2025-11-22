import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		int N = Integer.parseInt(br.readLine());	//연산의 개수
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num > 0) {
				minHeap.add(num);
				continue;
			}
			
			if(minHeap.isEmpty()) {
				sb.append(0).append("\n");
				continue;
			}
			
			sb.append(minHeap.poll()).append("\n");
		}
		
		System.out.println(sb);
	}// main

}
