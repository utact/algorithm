import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Candidate implements Comparable<Candidate>{
    int r, c, dist;
    Candidate(int r, int c, int dist) { this.r = r; this.c = c; this.dist = dist; }
    @Override public int compareTo(Candidate o) {
        if (this.dist != o.dist) return this.dist - o.dist;
        if (this.r != o.r) return this.r - o.r;
        return this.c - o.c;
    }
}

public class Main_김민정 {
    static int N;
    static int[][] sea;
    static int sharkSize = 2, eaten = 0, time = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first;
        do {
            first = br.readLine();
            if (first == null) throw new IOException("No input");
            first = first.trim();
        } while (first.isEmpty());
        N = Integer.parseInt(first);

        sea = new int[N][N];
        int sr = 0, sc = 0;

        for (int i = 0; i < N; i++) {
            String line;
            do {
                line = br.readLine();
                if (line == null) throw new IOException("Unexpected EOF while reading grid");
                line = line.trim();
            } while (line.isEmpty());
            StringTokenizer st = new StringTokenizer(line);

            for (int j = 0; j < N; j++) {
                while (!st.hasMoreTokens()) {
                    line = br.readLine();
                    if (line == null) throw new IOException("Unexpected EOF while reading row tokens");
                    st = new StringTokenizer(line);
                }
                int n = Integer.parseInt(st.nextToken());
                sea[i][j] = n;
                if (n == 9) { sr = i; sc = j; sea[i][j] = 0; }
            }
        }

        while (true) {
            boolean[][] visited = new boolean[N][N];
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{sr, sc, 0});
            visited[sr][sc] = true;

            int minDist = Integer.MAX_VALUE;
            ArrayList<Candidate> candidates = new ArrayList<>();

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1], d = cur[2];

                if (d > minDist) break;

                if (sea[r][c] > 0 && sea[r][c] < sharkSize) {
                    minDist = d;
                    candidates.add(new Candidate(r, c, d));
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (visited[nr][nc]) continue;
                    if (sea[nr][nc] <= sharkSize) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc, d + 1});
                    }
                }
            }

            if (candidates.isEmpty()) break;

            Collections.sort(candidates);
            Candidate best = candidates.get(0);

            time += best.dist;
            sr = best.r; sc = best.c;
            sea[best.r][best.c] = 0;

            eaten++;
            if (eaten == sharkSize) { sharkSize++; eaten = 0; }
        }

        System.out.println(time);
    }
}
