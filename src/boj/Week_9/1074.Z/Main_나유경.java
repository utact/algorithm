import java.io.*;
import java.util.*;

public class Main {

    static int N, r, c;
    static int count = 0;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int)Math.pow(2, N);
        z(size, 0, 0);
        System.out.println(result);
    }

    static void z(int size, int row, int col) {
        // 2x2 크기일 때는 직접 확인
        if (size == 1) {
            if (row == r && col == c) result = count;
            count++;
            return;
        }

        int half = size / 2;

        // r,c가 어느 사분면에 속하는가?
        if (r < row + half && c < col + half) { // 1사분면 (왼위)
            z(half, row, col);
        } else if (r < row + half && c >= col + half) { // 2사분면 (오위)
            count += half * half;
            z(half, row, col + half);
        } else if (r >= row + half && c < col + half) { // 3사분면 (왼아래)
            count += 2 * half * half;
            z(half, row + half, col);
        } else { // 4사분면 (오아래)
            count += 3 * half * half;
            z(half, row + half, col + half);
        }
    }
}