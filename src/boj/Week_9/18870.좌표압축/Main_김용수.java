import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_김용수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] numList = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}

		int[] sorted = numList.clone();
		Arrays.sort(sorted);

		Map<Integer, Integer> map = new HashMap<>();

		int rank = 0;
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(sorted[i]))
				map.put(sorted[i], rank++);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map.get(numList[i])).append(' ');
		}
		System.out.println(sb.toString());

	}
}
