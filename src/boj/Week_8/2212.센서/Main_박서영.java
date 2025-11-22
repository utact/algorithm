import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //센서 개수 
		int[] sensor = new int[N]; //센서 배열
		
		int K = Integer.parseInt(br.readLine()); //집중국 개수 
		
		if(K >= N) {//집중국이 센서 개수보다 많거나 같을 경우 영역의 길이가 0이므로 최솟값은 무조건 0이 됨 
			System.out.println(0);
			return;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { //센서 배열 입력받기 
			sensor[i] = Integer.parseInt(st.nextToken());
		}
		
		//집중국의 수신가능영역 길이의 합이 최소가 되려면 각각 영역의 사잇값의 합이 최대가 되어야 함.
		
		Arrays.sort(sensor); //센서 크기순 정렬
		
		int[] minus = new int[N-1]; //이웃한 센서와의 차 배열
		
		for (int i = 0; i < N-1; i++) {
			minus[i] = sensor[i+1] - sensor[i];
		}
		
		//센서의 차 배열 크기순 정렬 후 큰 순서대로 K-1개 뽑아서 더함.
		Arrays.sort(minus); 
		int maxGap = 0;
		
		for (int i = 0; i < K-1; i++) {
			maxGap += minus[N-2-i];
		}
		
		//센서의 최댓값과 최솟값의 차(센서가 존재하는 좌표 길이)에서 각 영역의 사잇값(최대)을 빼면 수신가능영역의 길이(최소)가 됨.
		int result = (sensor[N-1] - sensor[0]) - maxGap;
		
		System.out.println(result);
	}
}
