import java.io.*;
import java.util.*;

public class Main_박서영 {
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		cnt = 0; //몇번째로 방문했는지 카운트
		where(N, r, c);
		
		System.out.println(cnt);
	}
	
	//사분면을 분할해서 (2의 N-1제곱을 기준으로) 위치를 판별한 뒤 각 사분면마다 카운트한뒤 재귀
	//2사분면은 4의 N-1제곱 더하고 재귀 호출, 3사분면은 두번 더하기, 4사분면은 세번 더하기(이전 사분면을 이미 지나왔기 때문에 카운트에 추가)
	//2, 3, 4사분면은 다음 재귀 호출 시 다음 재귀의 기준이 되는 사각형에 맞게 r 또는 c의 위치 수정
	static void where(int N, double r, double c) {
		if(N == 0)
			return;
		
		if(r < Math.pow(2, N-1) && c < Math.pow(2, N-1)) { //1사분면
			where(N-1, r, c);
		}else if(r < Math.pow(2, N-1) && c >= Math.pow(2, N-1)) { //2사분면
			cnt += Math.pow(4, N-1);
			where(N-1, r, c - Math.pow(2, N-1));
		}else if(r >= Math.pow(2, N-1) && c < Math.pow(2, N-1)) { //3사분면
			cnt += (Math.pow(4, N-1))*2;
			where(N-1, r - Math.pow(2, N-1), c);
		}else { //4사분면
			cnt += (Math.pow(4, N-1))*3;
			where(N-1, r - Math.pow(2, N-1), c - Math.pow(2, N-1));
		}
	}
}
