import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N, M;
    static int[] curr;
    static int[][] arr;
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        // 도시 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) home.add(new int[]{i, j});
                if (arr[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }
        
        // 조합 저장
        curr = new int[M];
        comb(0, 0);
        System.out.println(min);
    }

    // 조합 생성
    static void comb(int d, int start) {
    	// 조합이 생성되었다면
        if (d == M) {
            int cityDist = chicken_distance(); // 탐색 수행
            min = Math.min(min, cityDist);
            return;
        }
        
        // 
        for (int i = start; i < chicken.size(); i++) {
            curr[d] = i;
            comb(d + 1, i + 1);
        }
    }

    // 도시 치킨 거리 계산
    static int chicken_distance() {
        int total = 0;
        
        for (int[] h : home) {
        	// 현재 집
            int hx = h[0];
            int hy = h[1];
            int min = Integer.MAX_VALUE;
            
            // 가장 가까운 치킨 집 탐색
            for (int idx : curr) {
                int[] c = chicken.get(idx);
                int cx = c[0];
                int cy = c[1];
                int dist = Math.abs(hx - cx) + Math.abs(hy - cy);
                min = Math.min(min, dist);
            }
            total += min;
        }

        return total;
    }
}
