import java.util.Scanner;

public class Main_김용수 {
    static int N, S, ans;
    static int[] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();      
        S = sc.nextInt();      
        a = new int[N + 1];    // 1-based

        for (int i = 1; i <= N; i++) a[i] = sc.nextInt();

        ans = Integer.MAX_VALUE;
        slide();               // 두 포인터로 최소 길이 찾기

        // 못 만들면 0
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }

    // 슬라이딩 윈도우
    static void slide() {
        int l = 1, r = 0;  // 비어있는 윈도우(1..0)
        long sum = 0;

        while (true) { 
            if (sum >= S) { //합 충분
                ans = Math.min(ans, r - l + 1); // 길이 갱신하고 왼쪽 줄이기
                sum -= a[l++];
            } 
            
            else { // 합이 부족하면 
                if (r == N) break;  // 더 못 늘리면 종료
                sum += a[++r]; // 오른쪽 늘리기
            }
        }
    }
}
