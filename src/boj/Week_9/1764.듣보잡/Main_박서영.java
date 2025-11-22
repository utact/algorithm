import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> hear = new HashSet<>(); //듣도 못한 사람
		List<String> hearSee = new ArrayList<>(); //듣도 보도 못한 사람
		
		for (int i = 0; i < M; i++) {
			hear.add(br.readLine());
		}
		
		//보도 못한 사람 입력받으며 동시에 듣도 못한 사람에 포함되는지 확인 후 듣보잡 리스트에 넣음
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			if(hear.contains(name)) {
				hearSee.add(name);
			}
		}
		
		Collections.sort(hearSee); //듣보잡 정렬
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(hearSee.size()).append("\n"); //듣보잡 수 출력
		for (int i = 0; i < hearSee.size(); i++) {
			sb.append(hearSee.get(i)).append("\n"); //듣보잡 명단 사전순 출력
		}
		
		System.out.println(sb);
	}
}
