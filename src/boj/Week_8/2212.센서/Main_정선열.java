import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정선열 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 설치가능한 집중국이 많으면 그냥 끝내도 됨.
        if (K >= N) {
        	System.out.println(0); 
        	return;
        }
        
        else {
        	
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        // 거리 배열: 다음 센서와의 거리
        int[] dist = new int[N-1];
        for (int i = 0; i < dist.length; i++) {
            dist[i]= arr[i+1]-arr[i];
        }
        
        // K개의 기지국을 설치
        // dist를 정렬 후 긴 거 K-1개를 빼면 => K개의 집중국이 생기는 거와 같음
        // ex) 2 3 1 1 2 => 1 3 6 7 8 10          => 3을 빼면 1 3  /  6 7 8 10 -> 이렇게 두개
        
        Arrays.sort(dist);
        
        int sum = 0;
        for (int i = 0; i < N-1 - (K-1) ; i++) {
			sum += dist[i];
		}
        System.out.println(sum);
        }
    }
}
