import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 총합이 최소가 되려면 무조건 오름차순 정렬 후 작은 값부터 더해야함 !
 */

public class Main_김민정 {
	static int N;
	static int[] cSum;			// 누적합 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}// 수열 입력
		
		// 배열 오름차순 정렬
		Arrays.sort(arr);
		
		//누적합 구하기
		int[] cSum = new int[N]; 
		for(int i=1; i<N; i++) {
			arr[i] += arr[i-1]; 
		}
		
		//정답 구하기
		int sum = 0;
		for(int i=0; i<N; i++) {
			sum+= arr[i];
		}
		
		System.out.println(sum);
	}
}
