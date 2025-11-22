import java.io.*;
import java.util.*;

//“좌표를 압축한다” -> ‘원래 숫자 대신 그 숫자의 순서(인덱스) 를 쓰는 것’ 
//입력: 2 4 -10 4 -9
//출력: 2 3 0 3 1
public class Main {

	static int N;
	static int[] arr;
	static int[] clone;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		clone = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			clone[i] = arr[i]; // 원래 입력 순서 기억용
		}
		// 복사본 정렬 (작은 수부터)
		Arrays.sort(clone);

		// Map : 값에 인덱스 매핑
		Map<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		for (int num : clone) {
			if (!map.containsKey(num))
				map.put(num, idx++);
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int n : arr) {
			sb.append(map.get(n)).append(" ");
		}
		System.out.println(sb);

	}
}
