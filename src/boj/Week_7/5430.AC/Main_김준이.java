import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String command = br.readLine(); // 명령어 p
			int n = Integer.parseInt(br.readLine()); // 수행할 배열의 길이

			// 수행할 배열 입력 받기 시작
			StringBuilder sb = new StringBuilder();
			sb.append(br.readLine()); // 배열 입력 받기
			sb.deleteCharAt(0); // 시작 대괄호 제거
			sb.deleteCharAt(sb.length() - 1); // 닫는 대괄호 제거

			LinkedList<Integer> list = new LinkedList<>(); // 뒤집기 작업 편하게 하려고 리스트에 넣어둠
			if (sb.length() != 0) {
				String[] arr = sb.toString().split(",");
				for (int i = 0; i < arr.length; i++) {
					list.add(Integer.parseInt(arr[i]));
				}
			}

			boolean flag = false; // 빈 괄호인데 D 작업이 일어나면 error
			boolean reverse = false; // 뒤집기 상태인지 아닌지 체크

			// 이제 명령어 수행
			for (int i = 0; i < command.length(); i++) {
				char curr = command.charAt(i);
				if (curr == 'D' && list.isEmpty()) {
					flag = true;
					break;
				}

				// 뒤집기 작업
				if (curr == 'R') {
					reverse = !reverse;
				}
				// 삭제 작업
				else if (curr == 'D') {
					if (!reverse) {
						list.removeFirst();
					} else {
						list.removeLast();
					}
				}
			}

			// 결과 출력
			// 1. flag true면 바로 에러 출력
			// 2. 빈 문자열이면 바로 [] 출력
			// 3. 출력할 값이 있으면 뒤집기 상태에 따라 값 정리해서 출력
			if (flag) {
				System.out.println("error");
			} else if (list.size() == 0) {
				System.out.println("[]");
			} else {
				StringBuilder ans = new StringBuilder();
				ans.append("[");

				if (!reverse) {
					for (int num : list) { // 시간 초과 막으려고 for-each 사용
						ans.append(num + ",");
					}
				} else {
					for (int i = list.size() - 1; i >= 0; i--) {
						ans.append(list.get(i) + ",");
					}
				}
				ans.deleteCharAt(ans.length() - 1); // 마지막 , 제거
				ans.append("]");
				System.out.println(ans);
			}

		} // test case loop
	}// main

}
