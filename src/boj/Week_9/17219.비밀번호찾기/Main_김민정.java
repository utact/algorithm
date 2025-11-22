import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 코드 고민
 * 
 * Map으로 할 수 있나?
 */
public class Main_김민정 {
	static int N, M;
	static Map<String, String> map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>(N);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());

			map.put(st.nextToken(), st.nextToken());
		}// 사이트 - 비밀번호 맵 입력
		
		for(int i=0; i<M; i++) {
//			System.out.println(map.get(br.readLine()));
			bw.write(map.get(br.readLine()) + '\n');
		}// M개 반복문 돌면서 출력
		
		bw.flush();
		
	}
}
