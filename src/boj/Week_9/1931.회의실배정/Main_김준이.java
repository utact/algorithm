import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int N = Integer.parseInt(br.readLine()); // 회의의 수

		List<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new int[] { start, end });
		}

		// 끝나는 시간이 빠른 순으로 정렬(같으면 시작 시간이 빠른 순)
		// 뒤에 더 많은 회의를 배치할 수 있는 여지가 커짐
		Collections.sort(list, (a, b) -> {
			if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
		    return Integer.compare(a[1], b[1]);
		});

		int prev = list.get(0)[1]; // 직전 회의가 끝난 시간
		int ans = 1;
		for (int i = 1; i < list.size(); i++) {
			int[] curr = list.get(i);
			// 직전 회의가 끝난 이후에 시작하는 회의라면 추가
			if (curr[0] >= prev) {
				prev = curr[1];
				ans++;
			}
		}

		System.out.println(ans);
	}

}
