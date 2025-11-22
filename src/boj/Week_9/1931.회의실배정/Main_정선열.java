import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정선열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        room[] arr = new room[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new room(start, end);
        }
        Arrays.sort(arr);
        int lastEnd = 0;
        int cnt = 0;
        for (room m : arr) {
            if (m.s >= lastEnd) {
                cnt++;
                lastEnd = m.e;
            }
        }
        System.out.println(cnt);
    }
    static class room implements Comparable<room>{
    	int s;
    	int e;
    	room(int s, int e){
    		this.s = s;
    		this.e = e;
    	}
		@Override
		public int compareTo(room o) {
			// 끝나는 시간이 우선으로 정렬
			int t = this.e -o.e;
            if (t != 0) return t;
            
            // 그다음엔 시작시간이 빨라야 들어옴
            return this.s - o.s;
		}
    	
    }
}
