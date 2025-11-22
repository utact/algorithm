import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_김준이 {

	/**
	 * int 오버플로우 -> long으로 계산 
	 * 모든 나무를 잘라야 하는 경우 처리
   * 올림 나눗셈
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 나무 개수
		int M = Integer.parseInt(st.nextToken()); // 목표 나무 길이

		int[] tree = new int[N];
		long[] height = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		// 배열 내림차순 정렬
		Arrays.sort(tree);
		for (int i = 0, j = tree.length - 1; i < j; i++, j--) {
			int t = tree[i];
			tree[i] = tree[j];
			tree[j] = t;
		}

		int idx = 0; // 잘릴 나무의 개수
		// height[i] = tree[i]를 목표값으로 설정했을 때 얻을 나무 높이
		for (int i = 1; i < N; i++) {
			long diff = (long) tree[i - 1] - tree[i];
			height[i] = height[i - 1] + diff * i;
			if (height[i] >= M) {
				idx = i;
				break;
			}
		}

		// 나무를 다 잘라가야 하는 경우
		if (idx == 0) {
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += tree[i];
			}

			height[N] = sum;
			idx = N;
		}

		// i와 i-1 사이 값을 목표치로 설정해야 함
		// i-1에서 얼마나 더 가야하는가?
		long need = M - height[idx - 1]; // 부족한 나무 높이 길이
		long steps = (need + idx - 1) / idx; // 얼마나 더 낮출것인가 (올림 나눗셈)
		long ans = tree[idx - 1] - steps;
		System.out.println(ans);

	}

}
