// 14890_경사로
// 완전탐색 + 시뮬레이션 (행/열 각각 검사, 경사로 설치 가능 여부 판단)

import java.io.*;
import java.util.*;

public class Main_나유경 {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        // 행 검사
        for (int i = 0; i < N; i++) {
            if (check(map[i])) count++;
        }

        // 열 검사
        for (int j = 0; j < N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) col[i] = map[i][j];
            if (check(col)) count++;
        }

        System.out.println(count);
    }

    // 한 줄(행 or 열)이 지나갈 수 있는지 판단
    static boolean check(int[] line) {
        boolean[] slope = new boolean[N]; // 경사로 설치 여부
        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0) continue; // 높이 같으면 통과

            // 높이 차이 1 초과면 불가능
            if (Math.abs(diff) > 1) return false;

            // 오르막
            if (diff == 1) {
                // 뒤에서 L칸 확인
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || line[idx] != line[i] || slope[idx]) return false;
                    slope[idx] = true;
                }
            }
            // 내리막
            else if (diff == -1) {
                // 앞으로 L칸 확인
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || line[idx] != line[i + 1] || slope[idx]) return false;
                    slope[idx] = true;
                }
            }
        }
        return true;
    }
}