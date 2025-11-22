import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_정선열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// make-set
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) arr[i] = i;
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		HashSet<Integer> hs = new HashSet();
		for (int i = 1; i <= N; i++) {
			findset(i);
			if (!hs.contains(arr[i])) hs.add(arr[i]);
		}
		System.out.println(hs.size()); 
	}
	static int[] arr;
	
	// union
	static void union(int i, int j) {
		arr[findset(i)] = findset(j);
	}
	// 대표자 탐색 + 경로압축
	static int findset(int j) {
		if (arr[j] != j) arr[j] = findset(arr[j]);
		return arr[j];
	}
}
