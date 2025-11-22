

import java.util.*;

public class Main_김용수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] x = new int[N];
        for (int i = 0; i < N; i++) x[i] = sc.nextInt();

        if (K >= N) {
            System.out.println(0);
            return;
        }

        Arrays.sort(x);

        
        int[] d = new int[N - 1]; // 간격 배열
        for (int i = 0; i < N - 1; i++) d[i] = x[i + 1] - x[i];
        
        // 간격을 오름차순 정렬 → 작은 간격부터 N-K개 더하면 최소합
        // K개로 나눈다 -> K-1번 자른다 -> N-1번에서 큰 간격 K-1번을 뺸다
        // -> N-1 -(K-1) => 작은 간격 N-K개가 남는다.
        Arrays.sort(d);

        int ans = 0; 
        for (int i = 0; i < N-K; i++) ans += d[i];

        System.out.println(ans);
    }
}
