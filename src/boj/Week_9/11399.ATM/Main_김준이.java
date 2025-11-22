import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int N = Integer.parseInt(br.readLine()); // 좌표의 개수

		int[] arr = new int[N];
		int[] sum = new int[N];	//누적합
		int ans = 0;	//최소 시간

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}//입력 끝

		Arrays.sort(arr); // 소요 시간을 기준으로 정렬
		
		sum[0] = arr[0];
		for (int i = 1; i < N; i++) {
			sum[i] = arr[i] + sum[i-1];
		}
		
		for(int i = 0; i < N; i++) {
			ans += sum[i];
		}
		
		System.out.println(ans);
		
	}// main

}
