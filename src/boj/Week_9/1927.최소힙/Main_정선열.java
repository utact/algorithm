import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_정선열 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int curr = Integer.parseInt(br.readLine());
            if (curr != 0)
                pq.add(curr);
            else {
                if (pq.isEmpty())
                    sb.append(0 + "\n");
                else
                    sb.append(pq.poll()+"\n");
            }
        }
        System.out.println(sb);

    }

}