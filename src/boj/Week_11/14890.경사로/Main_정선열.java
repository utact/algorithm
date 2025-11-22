import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_정선열 {
	
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 그냥 행별로 열벌로 모두 체크 하면 될 듯
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			if(isOk(arr[i])) ans++;
		}
		
		for (int j = 0; j < N; j++) {
			int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = arr[i][j];
            }
			if(isOk(col)) ans++;
		}
		
		System.out.println(ans);
	}

	private static boolean isOk(int[] l) {
		boolean[] visit = new boolean[N];
		
		for (int i = 0; i < N - 1; i++) {
            int cur = l[i];
            int nxt = l[i + 1];
            int diff = nxt - cur;

            // 1. 같은 높이
            if (diff == 0) continue;

            // 2. 올라가는 방향
            if (diff == 1) {
                for (int k = i; k > i - M; k--) {
                    if (k < 0) return false;               // 경계 바깥임
                    if (l[k] != cur) return false;         // 연속이 아님
                    if (visit[k]) return false;             // 중복 사용 금지
                }
                
                // 경사로 설치 작업 수행
                for (int k = i; k > i - M; k--) {
                	visit[k] = true;
                }
                continue;
            }

            // 내려가는 경우
            if (diff == -1) {
                for (int k = i + 1; k <= i + M; k++) {
                    if (k >= N) return false;              // 경계 밖
                    if (l[k] != nxt) return false;      // 연속성 실패
                    if (visit[k]) return false;             // 중복 사용 금지
                }
                for (int k = i + 1; k <= i + M; k++) {
                	visit[k] = true; // 경사로 배치
                }
                continue;
            }
            return false;
        }
        return true;
    }
}
