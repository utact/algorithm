import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_김민정 {
	static int N;
	static int[][] meeting;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		meeting = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			meeting[i][0] = Integer.parseInt(st.nextToken());
			meeting[i][1] = Integer.parseInt(st.nextToken());
		} // 회의 정보 입력

		// 정렬
		Arrays.sort(meeting, (a, b) -> {
			if (a[1] == b[1])
				return a[0] - b[0]; // 끝나는 시간 같으면 시작 시간 비교
			return a[1] - b[1]; // 끝나는 시간 기준으로 오름차순
		});

		// 선택
		int count = 0;
		int lastEnd = 0;

		for (int[] m : meeting) {
			if (m[0] >= lastEnd) {
				count++;
				lastEnd = m[1];
			}
		}

		// 정답 출력
		System.out.println(count);

	}
}
