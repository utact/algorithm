import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_김용수 {
	static int N;
	static int[][] meets;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		meets = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meets[i][0] = Integer.parseInt(st.nextToken());
			meets[i][1] = Integer.parseInt(st.nextToken());
		} // 입력

		Arrays.sort(meets, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1])
					return Integer.compare(o1[1], o2[1]);
				return Integer.compare(o1[0], o2[0]);
			}

		});
		
		int cnt = 0;
		int last = 0;
		for(int i = 0; i<N; i++) {
			if(meets[i][0] >= last) {
				cnt++;
				last = meets[i][1];
				
			}
		}
		
		System.out.println(cnt);

	}
}
