import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_정선열 {
    static int N, M;
    static int min;
    static int[][] arr;
    static boolean[][] visit;
    static List<int[]>[] cctv;
    static List<int[]> list;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 배열
        arr = new int[N][M];
        list = new LinkedList<>();
        int wall = 0; // 벽 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] !=0 && arr[i][j] != 6) list.add(new int[] {i,j}); // 카메라 위치 저장
            }
        }
        // 감시 방향
        cctv = new LinkedList[6];
        for (int i = 1; i <= 5; i++) cctv[i] = new LinkedList<>(); // 초기화
        cctv[1].add(new int[] {0}); cctv[1].add(new int[] {1}); cctv[1].add(new int[] {2}); cctv[1].add(new int[] {3});
        cctv[2].add(new int[] {0,1}); cctv[2].add(new int[] {2,3});
        cctv[3].add(new int[] {0,3}); cctv[3].add(new int[] {1,3}); cctv[3].add(new int[] {1,2}); cctv[3].add(new int[] {0,2});
        cctv[4].add(new int[] {0,1,2}); cctv[4].add(new int[] {1,2,3}); cctv[4].add(new int[] {2,3,0}); cctv[4].add(new int[] {3,0,1});
        cctv[5].add(new int[] {0,1,2,3});
        
        // 그냥 카메라 다 돌려 보면 될듯
        // -> boolean 배열로 체크
        min = Integer.MAX_VALUE;
        visit = new boolean[N][M];
        dfs(0);
        System.out.println(min);
    }


    private static void dfs(int i) {
        // 종료 조건
        if (i == list.size()) {
            int cnt = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (arr[x][y]==0 && !visit[x][y]) cnt++;
                    if (min <= cnt) return;
                }
            }
            min = cnt;
            return;
        }
        
        // 현재 cctv 위치
        int r = list.get(i)[0];
        int c = list.get(i)[1];
        int type = arr[r][c];
        
        // 모든 경우의 수
        for (int[] curr : cctv[type]) {
            // 색칠
            List<int[]> painting = new LinkedList<>();
            for (int j : curr) {
                int nr = r + dr[j];
                int nc = c + dc[j];
                
                while (nr >= 0 && nc >= 0 && nr < N && nc < M &&
                        arr[nr][nc] != 6) {
                    // 감시 처리
                    if (arr[nr][nc] == 0 && !visit[nr][nc]) {
                        visit[nr][nc] = true;
                        painting.add(new int[] {nr,nc});
                    }
                    nr += dr[j];
                    nc += dc[j];
                }
            }
            
            // 더 탐색
            dfs(i+1);
            
            for (int[] paint : painting) {
                visit[paint[0]][paint[1]] = false;
            }
        }
        
    }

}