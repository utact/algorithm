import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_김민정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//입력받을 수열의 크기 
		int[] num = new int[N];						//수열 

		Set<Integer> set = new TreeSet<Integer>();	//TreeSet은 자동 정렬을 수행하기 때문에 채택

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			num[i] = n;
		} // 입력 완료

		for (int n: num) {	//중복 제거 + 오름차순 정렬 
			set.add(n);
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();	//원소 값과 순위 저장할 map

		int rank = 0;
		for (int n : set) {
			map.put(n, rank++);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map.get(num[i]) + " ");
		}

		System.out.println(sb);	//정답 출력 
	}
}
