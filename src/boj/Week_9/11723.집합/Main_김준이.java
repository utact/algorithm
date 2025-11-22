import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//배열 사용
//해당 숫자가 있으면 1, 없으면 0

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int T = Integer.parseInt(br.readLine()); // 연산의 개수

		int[] arr = new int[21];

		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String command = st.nextToken(); // 명령어
			int num = 0;
			if (!command.equals("all") && !command.equals("empty")) {
				num = Integer.parseInt(st.nextToken()); // 명령 인자
			}

			switch (command) {
			case "add":
				if (arr[num] == 0) {
					arr[num] = 1;
				}
				break;
			case "check":
				if (arr[num] == 1) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case "remove":
				arr[num] = 0;
				break;
			case "toggle":
				if (arr[num] == 1) {
					arr[num] = 0;
				} else {
					arr[num] = 1;
				}
				break;
			case "all":
				Arrays.fill(arr, 1);
				break;
			case "empty":
				Arrays.fill(arr, 0);
				break;
			}

		} // end

		System.out.println(sb);

	}// main

}
