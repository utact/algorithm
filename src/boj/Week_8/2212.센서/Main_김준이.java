import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//수신 영역의 길이 = 각 수신 구간의 최대값 - 최소값
//수신 구간이 짧아지려면 -> 간격이 큰 곳부터 끊어줘야 함
//코드 더러워짐........

public class Main {
	static int N, K;
	static int[] sensor;
	static int[][] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		N = Integer.parseInt(br.readLine()); // 센서의 개수
		K = Integer.parseInt(br.readLine()); // 집중국 개수

		// 예외: 집중국 > 센서면 => 수신길이 모두 0 가능
		if (K >= N) {
			System.out.println(0);
			return;
		}

		sensor = new int[N];
		dist = new int[N - 1][2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sensor); // 센서 좌표 오름차순 정렬

		// 간격을 저장해서 간격이 큰 곳부터 끊어줘야 함
		for (int i = 0; i < N - 1; i++) {
			dist[i][0] = sensor[i + 1] - sensor[i];
			dist[i][1] = i;
		}

		Arrays.sort(dist, (a, b) -> Integer.compare(b[0], a[0]));

		// 잘라야 하는 구간 저장
		// dist에서 큰 값부터 끊어야 함 -> K-1번
		int[] cut = new int[K - 1];

		for (int i = 0; i < cut.length; i++) {
			cut[i] = dist[i][1];
		}

		Arrays.sort(cut);

		// 구간 합 구하기(수신 길이 구하기)
		int ans = 0;
		for (int i = 0; i < cut.length; i++) {
			// 첫구간
			if (i == 0) {
				ans += sensor[cut[i]] - sensor[0];
			} else {
				// 이후 구간
				ans += sensor[cut[i]] - sensor[cut[i - 1] + 1];
			}
		}

		// 마지막 구간 처리
		if (K == 1) {
			ans += sensor[N - 1] - sensor[0];
		} else {
			ans += sensor[N - 1] - sensor[cut[cut.length - 1] + 1];
		}
		System.out.println(ans);

	}// main

}
