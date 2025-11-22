import java.util.Scanner;

public class Main_김용수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] zero = new int[41];
		int[] one = new int[41];

		zero[0] = 1;
		one[0] = 0;
		zero[1] = 0;
		one[1] = 1;

		for (int i = 2; i <= 40; i++) {
			zero[i] = zero[i - 1] + zero[i - 2];
			one[i] = one[i - 1] + one[i - 2];
		}

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt();
			System.out.println(zero[N]+" "+one[N]);
		}
	}
}