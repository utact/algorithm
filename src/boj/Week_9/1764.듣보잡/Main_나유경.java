import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 교집합 구하기 + 사전순 정렬
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 듣도못한사람을 set으로 넣기
		Set<String> setN = new HashSet<>();
		for (int i = 0; i < n; i++) {
			String nameN = br.readLine();
			setN.add(nameN);
		}
		// contains로 보도못한 사람이 있는지 보고
		// 있다면 정답list에 추가
		List<String> list = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			String nameM = br.readLine();
			if (setN.contains(nameM)) {
				list.add(nameM);
			}
		}
		Collections.sort(list);

		System.out.println(list.size());
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s).append("\n");
		}
		System.out.print(sb);
	}

}
