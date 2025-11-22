import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//투포인터

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 길이 N짜리 수열
		int S = Integer.parseInt(st.nextToken()); // 합 기준값

		st = new StringTokenizer(br.readLine());

		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		} // 수열 입력 끝

		int start = 0; // 구간합 시작지점
		int end = 0; // 구간합 끝지점
		int sum = nums[0]; // 현재 구간의 합
		int len = Integer.MAX_VALUE; // 구간 길이

		// 구간합 >= S면 -> 길이 갱신하고 start 포인터 값을 빼고 한 칸 앞으로 이동한다
		// 구간합 < S면 -> end 포인터를 한 칸 뒤로 이동해서 값을 더한다
		while (end < N) {
			if (sum >= S) {
				len = Math.min(end - start + 1, len);
				sum -= nums[start++];
			} else {
				if (++end < N)
					sum += nums[end];
			}
		}
		
		//len 값이 갱신이 안 됐으면 불가능한 것
		if (len == Integer.MAX_VALUE) {
			len = 0;
		}

		System.out.println(len);
	}// main

}
