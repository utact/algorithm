
import java.io.*;
import java.util.*;

public class Main_박서영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //세로 
		int M = Integer.parseInt(st.nextToken()); //가로 
		int B = Integer.parseInt(st.nextToken()); //블록 수
		int[][] map = new int[N][M]; //지도 배열
		int max = 0; //최대 높이
		int min = 256; //최소 높이 
		int minTime = Integer.MAX_VALUE; //최소 소요시간
		int maxLand = 0; //최소 소요시간의 땅 높이 중 최댓값
		
		//지도배열 입력받으며 최대, 최소 높이 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		
		//최대, 최소 높이 사이의 높이에 도달하기까지 걸리는 시간 모두 계산해서 최솟값 구하기 
		for (int h = min; h <= max; h++) { //최대 높이와 최소 높이 사이의 수로 시간 계산할 높이 설정
			int block = 0; //설정한 높이에 도달하기까지의 블록 변화량
			int time = 0; //설정한 높이에 도달하기까지의 시간
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] > h) { //설정 높이보다 현재 높이가 높을 경우
						block += map[i][j] - h;
						time += 2*(map[i][j] - h);
					}else if(map[i][j] < h) { //설정 높이보다 현재 높이가 낮을 경우 
						block -= h - map[i][j];
						time += h - map[i][j];
					}
				}
			}
			if(B + block >= 0) { //시작 시 블록 갯수에 블록 변화량을 더한 개수가 0 이상이면(가진 블록으로 작업 가능하다면) 결과값 갱신
				minTime = Math.min(minTime, time);
				if(minTime == time) {
					maxLand = Math.max(maxLand, h);
				}
			}
		}
		
		System.out.println(minTime + " " + maxLand);
	}
}
