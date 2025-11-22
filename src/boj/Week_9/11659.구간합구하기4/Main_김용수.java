import java.util.Scanner;

public class Main_김용수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] numList = new int[N+1];
		//누적합
		int[] presum = new int[N+1];
		// 배열 받기
		for (int i = 1; i < N+1; i++) {
			numList[i] = sc.nextInt();
			presum[i] = presum[i-1]+ numList[i];
		}

		// M개의 줄에 대해 i,j번째 수 합을 처리할 반복문
		for (int test_case = 0; test_case < M; test_case++) {
			
			// i와 j를 각각 시작과 끝 인덱스로 설정
			int i = sc.nextInt();
			int j = sc.nextInt();

			System.out.println(presum[j]-presum[i-1]);
		}
	}
}
