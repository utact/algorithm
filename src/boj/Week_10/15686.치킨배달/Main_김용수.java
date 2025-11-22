import java.util.ArrayList;
import java.util.Scanner;

public class Main_김용수 {
    static int N, M;
    static ArrayList<int[]> house = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static int[] pick;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {          
                int v = sc.nextInt();
                if (v == 1) house.add(new int[]{i, j});
                else if (v == 2) chicken.add(new int[]{i, j});
            }
        }

        pick = new int[M];
        comb(0, 0);
        System.out.println(answer);
    }

    static void comb(int depth, int start) {
        if (depth == M) {
            answer = Math.min(answer, calc());
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            pick[depth] = i;
            comb(depth + 1, i + 1);
        }
    }

    static int calc() {
        int sum = 0;
        for (int[] h : house) {
            int best = Integer.MAX_VALUE;
            for (int idx : pick) {
                int[] c = chicken.get(idx);
                int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]);
                if (d < best) best = d;
            }
            sum += best;
            if (sum >= answer) return sum;
        }
        return sum;
    }
}
