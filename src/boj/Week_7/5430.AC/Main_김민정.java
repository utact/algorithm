import java.io.*;
import java.util.*;

/*
 * R : 배열에 있는 수의 순서 뒤집기
 * D : 첫번째 수 버리기
 * (비어있는데 사용하면 에러 발생)
 * 함수 조합해서 사용 가능
 * 
 * 걍 덱 써서 할까..???
 */

public class Main_김민정 {
	static int n;// 배열의 원소 개수
	static Deque<String> dequeArr;// 배열 원소가 저장될 덱
	static Deque<String> dequeP;// 명령어 저장될 덱

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			dequeArr = new ArrayDeque<>();

			// 명령어 배열에 저장
			String str = br.readLine();
			String[] p = str.split("");

			// 배열 원소 덱에 저장
			n = Integer.parseInt(br.readLine());// 배열에 들어있는 수의 개수
			str = br.readLine();
			str = str.substring(1, str.length() - 1);// [] 뺌
			if (n == 1) {
				dequeArr.add(str);
			} else if (n > 1) {
				String[] arr = str.split(",");
				for (String s : arr) {
					dequeArr.add(s);
				}
			}

			boolean isOk = true; // error인지 아닌지
			boolean isReverse = false;// 뒤집는지 아닌지

			for (String instr : p) {
				
				if (instr.equals("R")) {
					isReverse = !isReverse;
				} else if (instr.equals("D")) {
					if (n == 0) {
						isOk = false;
						break;
					}
					if (isReverse) {
						dequeArr.pollLast();
						n--;
					} else {
						dequeArr.poll();
						n--;
					}
				}
			} // 명령어 전체 돌기

			StringBuilder sb = new StringBuilder("[");
			if (!isOk) {
				System.out.println("error");
			}else if (n == 0){
				System.out.println("[]");
			}else if (isReverse) {
				while (!dequeArr.isEmpty()) {
					sb.append(dequeArr.pollLast() + ",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append("]");
				System.out.println(sb);
			} else {
				while (!dequeArr.isEmpty()) {
					sb.append(dequeArr.poll() + ",");
				}
				sb.delete(sb.length() - 1, sb.length());
				sb.append("]");
				System.out.println(sb);
			}


		} // test case loop

	}
}
