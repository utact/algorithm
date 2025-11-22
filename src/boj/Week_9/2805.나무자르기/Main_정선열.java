import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정선열 {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];

		int max = Integer.MIN_VALUE;;
		int min = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		// 이분 탐색: -> 높이를 찾아서 해당 높이가 만족하면 return
		int ans = 0;
		while(min <= max) {
			int mid = (min+max) / 2;
			long sum = 0;
			
			// 자르는 높이 i
			for (int i : arr){
				if (i > mid) {
					sum += (i - mid);
					if (sum >= M) break; // 나무를 더 안 잘라도 됨
				}
			}
			
			// 넘었으면 한단계 아래 방문
			if (sum >= M) {
				ans = mid;
				min = mid + 1; 
			} 
			// 그렇지 않다면 lower 경계 한단계 올ㄹ미
			else {
				max = mid - 1; 
			}
		}
		
		System.out.println(ans);
		
	}
}
