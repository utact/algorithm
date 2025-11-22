import java.util.HashSet;
import java.util.Scanner;

public class Main_김용수 {
	static HashSet<Integer> set = new HashSet<>();
	static HashSet<Integer> all = new HashSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i < 21; i++) {
			all.add(i);
		}
		StringBuilder sb = new StringBuilder();
		int M = sc.nextInt();
		for (int m = 0; m < M; m++) {

			String command = sc.next();
			int x = 0;
			if (!"allempty".contains(command)) {
				x = sc.nextInt();
			}

			switch (command) {
			case "add":
				set.add(x);
				break;
			case "remove":
				set.remove(x);
				break;
			case "check":
				if (set.contains(x))
					sb.append(1);
				else
					sb.append(0);
				sb.append('\n');
				break;
			case "toggle":
				if (set.contains(x))
					set.remove(x);
				else
					set.add(x);
				break;
			case "all":
				set.clear();
				set.addAll(all);
				break;
			case "empty":
				set.clear();
				break;

			}
		}
		System.out.println(sb.toString());
	}
}