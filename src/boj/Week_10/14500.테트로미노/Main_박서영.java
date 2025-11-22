import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer; // 'T' 모양 체크를 위해

public class Main_박서영 {

    // 전역(static) 변수 선언
    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int maxSum = 0;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M];

        // 격자판 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 칸 (i, j)에서 탐색 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1. DFS 탐색 (T 모양 제외)
                visited[i][j] = true;
                dfs(i, j, 1, grid[i][j]);
                visited[i][j] = false; // 백트래킹

                // 2. T 모양 탐색
                checkTShape(i, j);
            }
        }

        // 최종 결과 출력
        System.out.println(maxSum);
    }

    /**
     * 1. T 모양을 제외한 모든 테트로미노 탐색 (DFS + 백트래킹)
     * @param r 현재 행
     * @param c 현재 열
     * @param depth 현재까지 방문한 칸 수
     * @param currentSum 현재까지의 합
     */
    public static void dfs(int r, int c, int depth, int currentSum) {
        // 4칸을 모두 방문했으면 최댓값 갱신
        if (depth == 4) {
            maxSum = Math.max(maxSum, currentSum);
            return;
        }

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 격자 범위 체크
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                // 방문하지 않은 칸이면
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true; // 방문 처리
                    dfs(nr, nc, depth + 1, currentSum + grid[nr][nc]);
                    visited[nr][nc] = false; // 백트래킹 (방문 해제)
                }
            }
        }
    }

    /**
     * 2. 'T' 모양(ㅗ, ㅜ, ㅏ, ㅓ) 탐색
     * (r, c)를 T의 중심(볼록 튀어나온 부분 말고)으로 간주
     */
    public static void checkTShape(int r, int c) {
        // 4방향 이웃 좌표 리스트
        ArrayList<int[]> neighbors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            neighbors.add(new int[]{r + dr[i], c + dc[i]});
        }

        // 4개의 이웃 중 3개를 선택하는 4가지 조합 확인
        // (즉, 4개 중 1개(i)를 빼는 경우를 순회)
        for (int i = 0; i < 4; i++) {
            int currentSum = grid[r][c]; // 중심 값
            boolean isValid = true;

            for (int j = 0; j < 4; j++) {
                if (i == j) continue; // i번째 이웃은 제외

                int nr = neighbors.get(j)[0];
                int nc = neighbors.get(j)[1];

                // 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    isValid = false; // 범위 밖이면 이 T 모양은 불가능
                    break;
                }
                currentSum += grid[nr][nc];
            }

            // 4칸이 모두 유효했다면 최댓값 갱신
            if (isValid) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }
    }
}