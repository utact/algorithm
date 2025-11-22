import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_정선열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<String> list = new LinkedList<>();
		
		HashSet<String> hs = new HashSet<>(); // Hashset이 contains 조회속도가 빠름
		for (int i = 0; i < N; i++) {
			hs.add(br.readLine());
		}
		
		// set안에 존재하면 list에 추가
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if (hs.contains(str)) list.add(str);
		}
		
		// 사전식 정렬을 위한 배열화
		Object[] arr = list.toArray();
		Arrays.sort(arr);
		
		// 출력
		StringBuilder sb = new StringBuilder(); 
		sb.append(list.size()+"\n");
		for (Object string : arr) {
			sb.append(string+"\n");
		}
		System.out.println(sb);
	}
}
