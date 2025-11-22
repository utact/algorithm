import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE; //조건을 만족하는 최소길이
		int sum = 0; //부분합
		int start = 0; //시작지점
		
		//수열의 처음부터 더하면서 S넘는지 확인
		//S 넘으면 최솟값 갱신, 제일 처음 더한 값 빼고 시작지점 +1
		//S보다 작아질 때까지 위 과정 계속하다가 작아지면 다시 다음 데이터 더하면서 S 넘는지 확인
		for (int i = 0; i < N; i++) {
			sum += arr[i]; 
			
			while(sum >= S) {
				min = Math.min(min, i-start+1);
				sum -= arr[start++];
			}
			if(min == 1) break; //최솟값이 1이면 더이상 작아질 수 없으므로 중단
		}
		
		if(min == Integer.MAX_VALUE) min = 0;
		
		System.out.println(min);
	}
}
