# 감시

# 목표

1. CCTV 방향을 적절히 선택해서 사각지대(0의 개수)를 최소화
2.  CCTV는 5종류, 각각 가능한 방향 세트가 다름
3. 벽(6)을 만나면 CCTV 시야가 막힘
4. DFS 방식으로 모든 CCTV 방향 조합 시도 → 최소 사각지대(ans)갱신 

# CCTV 정보저장

CCTV는 좌표와 타입 번호가 필요하다 이를 class로 관리한다

# 방향 벡터

상, 하, 좌, 우의 방향 배열을 생성한다 

# CCTV 타입 별 감시 방향 테이블

백트래킹 시, 이 테이블 기반으로 CCTV 방향 조합 선택한다

1번 부터 쓰기 위해 index 0은 사용하지 않는다!!

1번 CCTV: 한방향

2번 CCTV: 상+하, 좌+우

3번 CCTV: L자회전

4번 CCTV: 3방향

5번 CCTV: 4방향전체

```jsx
static int[][][] DIR = { {},
			{ { 0 }, { 1 }, { 2 }, { 3 } }, 
			{ { 0, 1 }, { 2, 3 } }, 
			{ { 0, 3 }, { 3, 1 }, { 1, 2 }, { 2, 0 } }, 
			{ { 0, 3, 1 }, { 3, 1, 2 }, { 1, 2, 0 }, { 2, 0, 3 } },
			{ { 0, 1, 2, 3 } }, 
	};
```

# 최소사각지대 ans

줄어드는 방향으로 업데이트를 해야함 

방법은 

dfs로 모든 조합을 탐색하면서 사각지대 개수를 계산할 때 

ans = Math.min(ans, 현재 사각지대)로 갱신할 것임

# DFS에 맵 복사본 넘김

for 

백트래킹

meaning

map을 통째로 깊은 복사해서 DFS에 넘기어 복구가 필요없는 상황으로 만듬

why

원래 맵을 건드리고 나중에 되돌리면 되돌리기 좌표 저장해야하기 때문에 코드가 복잡하다

그러니 복사본을 만들어서 DFS분기 마다 전달한다. 

```jsx
dfs(idx + 1, tempMap); 

...
     

static int[][] copyMap(int[][] origin) {
     int[][] copy = new int[N][M];
     for (int i = 0; i < N; i++) {
          copy[i] = origin[i].clone();
    }
      return copy;
}
```

# DFS: index번째 CCTV 방향 선택

CCTV를 하나씩 선택하면서 

가능한 모든 방향 조합을 시도하고

각 조합마다 감시를 적용한 상태로 다음 CCTV로 재귀호출

모든 CCTV 방향이 정해지면 사각지대 개수를 계산해서 최소값을 갱신함

```jsx
static void dfs(int idx, int[][] curMap) {

		if (idx == cctvs.size()) {
			ans = Math.min(ans, countBlindSpot(curMap));
			return;
		}

		CCTV cctv = cctvs.get(idx);
		int type = cctv.type;

		for (int[] dirs : DIR[type]) {
			int[][] tempMap = copyMap(curMap);

			for (int d : dirs) {
				watch(tempMap, cctv.r, cctv.c, d);
			}

			dfs(idx + 1, tempMap); 

		}

	}
```

# 특정 방향으로 감시 표시

CCTV 위치에서 선택 된 방향으로 쭉 나아가면서

경계 → 중지

벽 → 중지

빈칸 → 감시표시함

```jsx
static void watch(int[][] map, int r, int c, int d) {
    int nr = r;
    int nc = c;

    while (true) {
        nr += dr[d];
        nc += dc[d];

        if (nr < 0 || nr >= N || nc < 0 || nc >= M) break; // 경계
        
        if (map[nr][nc] == 6) break; // 벽

        if (map[nr][nc] == 0) map[nr][nc] = 7; // 감시 표기
    }
}
```

# 사각지대 카운트