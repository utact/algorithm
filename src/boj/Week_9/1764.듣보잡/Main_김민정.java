import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_김민정 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			set.add(br.readLine());
		}
		
		ArrayList<String> list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			if(set.contains(str)) {
				list.add(str);
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); i++) {;
			sb.append(list.get(i) + '\n');
		}
		System.out.println(sb);
		
	}
}
