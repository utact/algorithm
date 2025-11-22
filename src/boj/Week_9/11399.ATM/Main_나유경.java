import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 입력받기
		int[] arr = new int[N];
		// 시간합
		int[] timeArr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);//시간 최소화 하기위한 정렬

		int time = 0; // 인출 하는데 걸리는 시간

		for (int i = 0; i < N; i++) {
			time += arr[i]; // 지금까지합
			timeArr[i] = time; // timeArr에 저장
		}

		// timeArr의 전체합
		int total = 0;
		for (int i = 0; i < timeArr.length; i++) {
			total += timeArr[i];
		}
		System.out.println(total);
	}
}
