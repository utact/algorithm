import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Set으로 풀어보기 ㄱㄱ
 */
public class Main_김민정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> set = new HashSet<>(); // 집합
		Set<Integer> num = new HashSet<>( // 1-20 집합
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

		int M = Integer.parseInt(br.readLine()); // 연산 수
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 토큰 개수 1이면 all 이거나 empty 임
			if (st.countTokens() == 1) {
				String op = st.nextToken();
				switch (op) {
				case ("all"):
					set.clear();
					set.addAll(num);
					break;
				case ("empty"):
					set.clear();
					break;
				}// 케이스 별로 연산 수행

			} else {
				String op = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				switch (op) {
				case ("add"):
					set.add(n);
					break;
				case ("remove"):
					set.remove(n);
					break;
				case ("check"):
//					System.out.println((set.contains(n)) ? 1 : 0);
					sb.append((set.contains(n)) ? 1 : 0);
					sb.append("\n");
					break;
				case ("toggle"):
					if (set.contains(n))
						set.remove(n);
					else
						set.add(n);
					break;

				}// 케이스 별로 연산 수행
			}//토큰 수 1개냐 2개냐에 따라 구분 

		} // 연산 수행

		System.out.println(sb);
	}
}