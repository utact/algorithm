// 17142_연구소3
// 조합(활성 바이러스 선택) + BFS

import java.io.*;
import java.util.*;

public class Main_나유경 {
    static int N, M;
    static int[][] map;
    static List<int[]> virusList = new ArrayList<>();
    static int emptyCount = 0; // 전체 빈칸 수
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusList.add(new int[]{i, j});
                else if (map[i][j] == 0) emptyCount++;
            }
        }

        // 빈 칸이 없으면 이미 다 감염된 상태
        if (emptyCount == 0) {
            System.out.println(0);
            return;
        }

        comb(0, 0, new int[M]);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 바이러스 M개 선택 (조합)
    static void comb(int start, int depth, int[] selected) {
        if (depth == M) {
            bfs(selected);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            selected[depth] = i;
            comb(i + 1, depth + 1, selected);
        }
    }

    // BFS로 퍼뜨리기
    static void bfs(int[] selected) {
        int[][] time = new int[N][N];
        for (int[] t : time) Arrays.fill(t, -1);

        Queue<int[]> q = new ArrayDeque<>();

        for (int idx : selected) {
            int[] v = virusList.get(idx);
            q.offer(new int[]{v[0], v[1]});
            time[v[0]][v[1]] = 0;
        }

        int spread = 0; // 감염된 빈칸 수
        int maxTime = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] == 1 || time[nr][nc] != -1) continue; // 벽 or 방문

                time[nr][nc] = time[cur[0]][cur[1]] + 1;
                if (map[nr][nc] == 0) { // 빈 칸이면
                    spread++;
                    maxTime = time[nr][nc];
                }
                q.offer(new int[]{nr, nc});
            }
        }

        if (spread == emptyCount) answer = Math.min(answer, maxTime);
    }
}