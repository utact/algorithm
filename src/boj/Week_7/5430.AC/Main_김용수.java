import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main_김용수 {
	static List<String> numList;
	static boolean reverse;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {

			String p = sc.next();
			int N = sc.nextInt();
			sc.nextLine();
			// [ 다음 숫자
			// , 다음 숫자
			// ] 나오면 종료
			String str = sc.nextLine();
			boolean error = false;
			boolean blank = false;
			numList = new ArrayList<>();
			
			if (N > 0) // 배열 비었음
			{
				str = str.substring(1, str.length() - 1);
				numList = new ArrayList<>(Arrays.asList(str.split(",")));
			}
			reverse = false;

			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == 'R') {
					if (reverse) {
						reverse = false;
					} else {
						reverse = true;
					}
				} // 뒤집기
				else { // 제거
					if (numList.isEmpty()) { // 뺄수 없다.
						error = true;
						break;
					}

					if (reverse) {
						numList.remove(numList.size() - 1);
					} else {
						numList.remove(0);
					}
				}
			}

			StringBuilder sb = new StringBuilder();

			if (error)
				sb.append("error");

			else {
				sb.append('[');
				if (reverse) {
					for (int i = numList.size() - 1; i >= 0; i--) {

						sb.append(numList.get(i)).append(',');
					}
				}

				else {
					for (int i = 0; i < numList.size(); i++) {

						sb.append(numList.get(i)).append(',');
					}
				}
				if(sb.length()>1)
					sb.deleteCharAt(sb.length() - 1);
				sb.append(']');
			}

			System.out.println(sb);

		}
	}
}