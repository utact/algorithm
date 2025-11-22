import java.io.*;
import java.util.*;

// BFS
// 아기상어 ]
// BFS로 최단거리 물고기 탐색 후 이동·성장 시뮬레이션
public class Main_나유경 {
    static int n;
    static int[][] map;
    static int[] dr = {-1, 0, 0, 1}; 
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int sr = 0, sc = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sr = i;
                    sc = j;
                    map[i][j] = 0;
                }
            }
        }

        int size = 2;
        int eat = 0;
        int time = 0;

        while (true) {
            int[] result = bfs(sr, sc, size);
            if (result == null) break; // 먹을 수 있는 물고기 없음 → 끝

            int nr = result[0], nc = result[1], dist = result[2]; // 목표 위치, 거리
            time += dist;          // 이동 시간 누적
            eat++;                 // 먹은 물고기 수 증가
            map[nr][nc] = 0;       // 지도에서 먹은 물고기 제거
            sr = nr; sc = nc;      // 상어 위치 이동

            if (eat == size) {     // 자신의 크기만큼 먹으면 사이즈 증가
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }

    static int[] bfs(int sr, int sc, int size) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        List<int[]> fishes = new ArrayList<>();

        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    if (map[nr][nc] <= size) { // 지나갈 수 있음
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc, dist + 1});

                        if (map[nr][nc] > 0 && map[nr][nc] < size) {
                            fishes.add(new int[]{nr, nc, dist + 1}); // 먹을 수 있는 물고기 후보
                        }
                    }
                }
            }
        }

        if (fishes.isEmpty()) return null; // 먹을 수 있는 물고기 없음

        //람다 ... ?? 
        // 거리 → 행 → 열 기준 정렬
        fishes.sort((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        return fishes.get(0); // 가장 우선순위 높은 물고기 반환
    }
}