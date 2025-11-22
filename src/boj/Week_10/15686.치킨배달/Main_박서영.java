import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int N, M, minSum;
	static Point[] result;
	static List<Point> house = new ArrayList<>();
	static List<Point> chicken = new ArrayList<>();
	
	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//지도 배열 받으면서 집의 좌표, 치킨집 좌표 저장
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					house.add(new Point(i, j));
				else if(map[i][j] == 2)
					chicken.add(new Point(i, j));
			}
		}
		
		minSum = Integer.MAX_VALUE; //최종 도시의 치킨거리 최솟값
		result = new Point[M]; //치킨집 M개 뽑는 조합 배열
		findChickenDist(0, 0);
		
		System.out.println(minSum);
	}
	
	//재귀 돌리면서 치킨집 M개 뽑는 조합 구하기, M개 뽑으면 각 치킨집에서 집까지 거리 구해서 최솟값 구하고 합에 더하기, 그렇게 구한 합들 중 최솟값을 결과값에 저장
	static void findChickenDist(int count, int start) {
		if(count == M) {
			int sum = 0; //각 집에서부터 치킨집까지 최소거리의 합
			for (int i = 0; i < house.size(); i++) {
				int minDist = Integer.MAX_VALUE; //각 집에서부터 치킨집까지 최소거리
				for (int j = 0; j < M; j++) {
					//거리 구하기
					int dist = Math.abs(house.get(i).x - result[j].x) + Math.abs(house.get(i).y - result[j].y);
					minDist = Math.min(minDist, dist); //최소거리 구하기
				}
				sum += minDist; //최소거리의 합 구하기
			}
			minSum = Math.min(minSum, sum); //각 최소거리의 합의 최솟값 구하기
			
			return;
		}
		
		//조합을 구하는 재귀함수
		for (int i = start; i < chicken.size(); i++) {
			result[count] = chicken.get(i);
			
			findChickenDist(count+1, i+1);
		}
	}
}
