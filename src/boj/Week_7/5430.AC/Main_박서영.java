
import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			char[] p = str.toCharArray(); //입력받은 함수 p char배열로 바꾸기
			
			int n = Integer.parseInt(br.readLine());
			
			Deque<String> d = new ArrayDeque<>();
			
			str = br.readLine();
			if(n != 0) { //배열에 숫자가 있을때만 덱에 넣기, 없으면 넘어감
				String str2 = str.substring(1, str.length()-1);
				String[] num = str2.split(",");
				
				for (int i = 0; i < num.length; i++) {
					d.add(num[i]);
				}
			}
			
			int count = 0; //R이 나온 개수 
			boolean isError = false; //에러 여부 확인
			for (int i = 0; i < p.length; i++) {
				if(p[i] == 'R') {
					count++;
				}else if(p[i] == 'D') {
					if(d.size() == 0) { //덱에 아무것도 없을때 D 나오면 에러처리
						isError = true;
						break;
					}
					if(count%2 == 0) { //R이 0번 또는 짝수번 나오면 덱의 첫번째 데이터 버림
						d.removeFirst();
					}else { //R이 홀수번 나오면 덱의 마지막 데이터 버림
						d.removeLast();
					}
				}
			}
			
			if(isError) { //에러여부 확인 후 에러 출력
				System.out.println("error");
			}else { //R의 개수에 따라 출력 순서 다르게 하기
				int size = d.size();
				System.out.print("[");
				if(size == 0)
					System.out.print("]");
				else {
					if(count%2 == 0) {
						for (int i = 0; i < size-1; i++) {
							System.out.print(d.pollFirst() + ",");
						}
						System.out.print(d.poll() + "]");
					}else {
						for (int i = 0; i < size-1; i++) {
							System.out.print(d.pollLast() + ",");
						}
						System.out.print(d.poll() + "]");
					}
				}
				System.out.println();
			}
		}
	}
}
