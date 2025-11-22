import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		int S = 0;

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			if (cmd.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				S |= (1 << x);
			} else if (cmd.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				S &= ~(1 << x);
			} else if (cmd.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				sb.append((S & (1 << x)) != 0 ? 1 : 0).append('\n');
			} else if (cmd.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				S ^= (1 << x);
			} else if (cmd.equals("all")) {
				S = (1 << 21) - 1;
			} else if (cmd.equals("empty")) {
				S = 0;
			}
		}

		System.out.println(sb);
	}
}
