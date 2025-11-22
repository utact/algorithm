### **문제 링크**
[14502번 연구소](https://www.acmicpc.net/problem/14502)

> 벽 3개를 설치하여 최대한 넓은 안전구역 확보

### **접근 방법**
> 시간 복잡도: 2초  
> 제한 조건: 3 ≤ N,M ≤ 8 , 3개의 벽 설치 가능

```jsx
0: 빈칸 ↔ 1: 벽 ↔ 2: 바이러스
```
⇒ 임의의 빈 칸 중 3칸에 벽을 설치하고 완전 탐색수행

<aside>

### ⏱️ 예상 시간 복잡도

$_{N*M}C_3 * O(BFS + 배열 복사 + 안전구역  카운팅)$

</aside>

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj14502_연구소 {
	static int N,M;
	static int[][] arr;
	static List<int[]> virus,safe; 
	static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		virus = new LinkedList<>();
		safe = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st =new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) virus.add(new int[] {i,j}); // 바이러스 리스트
				if (arr[i][j] == 0) safe.add(new int[] {i,j}); // 안전구역 리스트
			}
		}
		
			int answer = solve();
      System.out.println(answer);
		
	}

//////////// 문제 해결 ///////////////////
	private static int solve() {
		int max = 0;
        int size = safe.size();

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    
                	// 맵 복사
                	int[][] tmp = new int[N][M];
                	for (int a = 0; a < N; a++) {
						for (int b = 0; b < M; b++) {
							tmp[a][b] = arr[a][b];
						}
					}
                	
                	// 벽 3군데 설정
                    int[] x = safe.get(i);
                    int[] y = safe.get(j);
                    int[] z = safe.get(k);
                    tmp[x[0]][x[1]] = 1;
                    tmp[y[0]][y[1]] = 1;
                    tmp[z[0]][z[1]] = 1;
                    bfs(tmp); // 확산
                    
                    // 남은 safe 개수 카운트
                    int cnt = 0;
                    for (int i2 = 0; i2 < N; i2++) {
                        for (int j2 = 0; j2 < M; j2++) {
                            if (tmp[i2][j2] == 0) cnt++;
                        }
                    }
                    if (cnt > max) max = cnt;
                }
                
            }
        }
        return max;
    }
	
	// 바이러스 확산 bfs
	static void bfs(int[][] arr) {
        Queue<int[]> q = new LinkedList<>();
        // 초기
        for (int[] v : virus) q.offer(new int[]{v[0], v[1]});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] == 0) {
                    arr[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }
}

```

---

## 예시

**ex 1)**

<img width="859" height="574" alt="image" src="https://github.com/user-attachments/assets/e1abeffc-295f-49c5-9f74-c763d0f2407c" />


**ex 2)**

<img width="883" height="223" alt="image" src="https://github.com/user-attachments/assets/c5cfdd2d-5b0b-4d2c-9df5-01d2fcd6795b" />

**ex3)**

<img width="862" height="306" alt="image" src="https://github.com/user-attachments/assets/b6d1fa96-a036-45a6-ac44-141a267fc730" />

경우의 수가 다양함. → 하단의 삼각형을 생성하면 됨.
