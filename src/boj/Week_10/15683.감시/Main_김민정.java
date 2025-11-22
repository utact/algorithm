import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CCTV {
    int r, c, type;
    CCTV(int r, int c, int type) {
        this.r = r; this.c = c; this.type = type;
    }
}

public class Main_김민정 {
    static int N, M;
    static int[][] office;

    // 상, 우, 하, 좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static List<CCTV> cams = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    // 각 타입별 가능한 방향 묶음
    // 인덱스: CCTV 타입(1~5)
    static int[][][] dirs = {
            {}, // 0 dummy
            { {0}, {1}, {2}, {3} },                          // 1번: 한 방향
            { {0,2}, {1,3} },                                // 2번: 서로 반대
            { {0,1}, {1,2}, {2,3}, {3,0} },                  // 3번: 직각 2방향
            { {0,1,2}, {1,2,3}, {2,3,0}, {3,0,1} },          // 4번: 3방향
            { {0,1,2,3} }                                    // 5번: 4방향
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= office[i][j] && office[i][j] <= 5) {
                    cams.add(new CCTV(i, j, office[i][j]));
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    // idx번째 CCTV에 대해 가능한 모든 방향 배치를 시도
    static void dfs(int idx) {
        if (idx == cams.size()) {
            answer = Math.min(answer, countBlind());
            return;
        }

        CCTV cam = cams.get(idx);
        for (int[] set : dirs[cam.type]) {
            // 현재 방향 세트로 감시 영역 표시하고, 표시한 좌표들을 스택에 기록(되돌릴 때 사용)
            List<int[]> marked = new ArrayList<>();
            for (int d : set) {
                mark(cam.r, cam.c, d, marked);
            }

            dfs(idx + 1);

            // 되돌리기
            for (int k = marked.size() - 1; k >= 0; k--) {
                int[] p = marked.get(k);
                office[p[0]][p[1]] = 0;
            }
        }
    }

    // 방향 d로 직진하며 감시 영역 표시
    // 빈 칸(0)만 특별 마커(7)로 표시하고 리스트에 기록 -> 되돌리기 쉽게
    static void mark(int r, int c, int d, List<int[]> marked) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (in(nr, nc) && office[nr][nc] != 6) { // 벽(6) 만나면 stop
            if (office[nr][nc] == 0) {
                office[nr][nc] = 7;          // 감시 영역 마킹
                marked.add(new int[]{nr, nc});
            }
            // CCTV(1~5)나 이미 마킹된 칸(7)은 그냥 통과
            nr += dr[d];
            nc += dc[d];
        }
    }

    static boolean in(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    // 사각지대(0)의 개수 계산
    static int countBlind() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (office[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}