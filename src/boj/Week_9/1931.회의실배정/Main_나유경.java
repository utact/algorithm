import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] meetings = new int[N][2]; // [시작, 끝]

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			meetings[i][1] = Integer.parseInt(st.nextToken()); // 끝 시간
		}

		// 끝나는 시간 기준 오름차순 정렬
		// 끝나는 시간이 같으면 시작 시간 기준 정렬
		Arrays.sort(meetings, (a, b) -> {
			if (a[1] == b[1])
				return a[0] - b[0];
			return a[1] - b[1];
		});

		int count = 0; // 회의 개수
		int lastEnd = 0; // 마지막으로 배정된 회의의 끝 시간

		for (int i = 0; i < N; i++) {
			int start = meetings[i][0];
			int end = meetings[i][1];

			if (start >= lastEnd) { // 이전 회의 끝나고 시작 가능하면
				count++;
				lastEnd = end; // 끝나는 시간 갱신
			}
		}

		System.out.println(count);
	}
}