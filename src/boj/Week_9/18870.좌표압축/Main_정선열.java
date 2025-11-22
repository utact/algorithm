import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_정선열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        // 정렬
        int[] arr_sort = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr_sort);
        
        // 매핑
        Map<Integer, Integer> map = new HashMap<>();
        int r = 0;
        for (int i = 0; i < N; i++) {
            int v = arr_sort[i];
            if (!map.containsKey(v)) {
            	map.put(v, r++);
            }
        }
        
        // 원래 순위로 복구
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
			sb.append(map.get(arr[i])+" ");
		}
        
        System.out.println(sb);
	}

}
