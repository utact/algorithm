import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int N = Integer.parseInt(br.readLine()); // 2xn 직사각형의 가로 길이
		
		int[] arr = new int[N+1];
		
		arr[1] = 1;
		
		if(N > 1) {
			arr[2] = 2;	//초기값 세팅
		}
		
		//매 단계마다 모듈러 연산 적용 (오버플로우 방지)
		for(int i = 3; i <= N; i++) {
			arr[i] = (arr[i-1] + arr[i-2])  % 10007;
		}
		
		System.out.println(arr[N]);
	}

}
