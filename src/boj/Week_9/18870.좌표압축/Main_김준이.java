import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//1. 입력 받을 때 입력 받은 순서 + 값을 동시에 배열에 저장 -> 리스트에 넣어서 작은 순으로 정렬
//2. 꺼내서 좌표 압축시키고 리스트에 추가
//3. 그리고 입력받은 순으로 다시 정렬해서 출력

public class Main_김준이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		int N = Integer.parseInt(br.readLine()); // 좌표의 개수

		List<int[]> numlist = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int seq = 0; // 입력 받은 순서
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			numlist.add(new int[] { tmp, seq++ });
		}
		
		numlist.sort(Comparator.comparingInt(a -> a[0]));
		
		List<int[]> list = new ArrayList<>();

		int prev = Integer.MIN_VALUE, pos = -1;

		for (int[] x : numlist) {
			int[] curr = x;
			if (curr[0] > prev) {
				pos++;
				prev = curr[0];
			}
			list.add(new int[] { pos, curr[1] });
		}

		list.sort(Comparator.comparingInt(a -> a[1]));

		StringBuilder sb = new StringBuilder();
		for (int[] x : list) {
			sb.append(x[0]).append(" ");
		}

		System.out.println(sb);

	}// main

}
