import java.io.*;
import java.util.*;

public class Main_나유경 {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); 

        arr = new int[n+1]; // 전역 배열 사용 (1-indexed)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());  // 질문의 갯수
        StringBuilder sb = new StringBuilder();

        for (int q = 0; q < m; q++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // 팰린드롬 확인 
            boolean isPalin = true;  
            int left = s, right = e; // 구간의 시작 과 끝에서 출발 
            while (left < right) { // 왼쪽 포인터와 오른쪽 포인터가 교차하기전 까지 반복 
                if (arr[left] != arr[right]) { // 양끝이 다르면 팰린드롬이 아님 
                    isPalin = false;
                    break;
                }
                left++; // 오른쪽으로 이동 
                right--; //  왼쪽으로 이동 
            }
            sb.append(isPalin ? 1 : 0).append("\n");
        }

        System.out.println(sb);
    }
}
