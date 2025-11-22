import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main_김용수 {
	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		Set<String> hear = new HashSet<>();
		Set<String> listen = new HashSet<>();
		for (int i = 0; i < N; i++) {
			hear.add(sc.next());
		}

		for (int i = 0; i < M; i++) {
			listen.add(sc.next());
		}
		List<String> ans = new ArrayList<>();
		
		for (String x : hear) {
			if (listen.contains(x)) {
				ans.add(x);
			}
		}
		
		Collections.sort(ans);
		
		System.out.println(ans.size());
		for(int i = 0; i<ans.size(); i++) {
			System.out.println(ans.get(i));
		}
	}
}
