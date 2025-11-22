import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정선열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 배열 완성
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr); // 정렬
		
		int sum = 0;
		for (int i = 0; i < N; i++) sum += arr[i]*(N-i);
		System.out.println(sum);
	}
}
