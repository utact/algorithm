import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람
		int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람

		Set<String> name1 = new HashSet<>(); // list 써서 list.contains 하는 것보다 시간 절감
		List<String> name2 = new ArrayList<>();

		// 듣도 못한 애들 입력받기
		for (int i = 0; i < N; i++) {
			name1.add(br.readLine());
		}

		// 보도 못한 애들 입력받으면서 체크하기
		for (int i = 0; i < M; i++) {
			String name = br.readLine();

			if (name1.contains(name)) {
				name2.add(name);
			}
		}

		// 사전순으로 정렬
		Collections.sort(name2);

		// 결과 출력 시작
		StringBuilder sb = new StringBuilder();
		sb.append(name2.size() + "\n");

		for (String x : name2) {
			sb.append(x + "\n");
		}

		System.out.println(sb);
	}// main

}
