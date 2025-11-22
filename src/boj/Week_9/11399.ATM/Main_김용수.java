import java.util.Arrays;
import java.util.Scanner;

public class Main_김용수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = sc.nextInt();
		}

		Arrays.sort(p);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for(int j= 0; j<=i; j++) {
				ans += p[j];
			}
		}
		
		System.out.println(ans);
	}
}
