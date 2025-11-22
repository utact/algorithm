# 연구소 3 (BOJ 17142)

## 

“활성 바이러스 M개를 선택해 모든 빈 칸을 감염시키는 최소 시간을 구하는 조합 + BFS 문제”

## 문제 정리

- **연구소 지도**가 주어짐
    - `0`: 빈 칸
    - `1`: 벽
    - `2`: 비활성 바이러스
- 이 중 **M개의 바이러스만 활성화**하여 감염 시작
- 모든 **빈 칸을 감염시키는 최소 시간**을 구해야 함
- 단, **모두 감염이 불가능한 경우 -1 출력**

## 접근 방식

1. **조합(Combination)** 으로 활성화할 M개의 바이러스 위치 선택
2. 선택된 바이러스들을 동시에 **BFS로 전염 확산**
3. 모든 빈 칸이 감염될 때까지 걸린 시간을 계산
4. 가능한 모든 조합 중 **최소 감염 시간**을 갱신

## 사용된 자료구조

| 자료구조 | 역할 |
| --- | --- |
| `int[][] map` | 연구소 지도 저장 (0, 1, 2) |
| `List<int[]> virusList` | 모든 바이러스의 좌표 저장 |
| `Queue<int[]>` | BFS 탐색용 큐 |
| `int[][] time` | 각 칸이 감염된 시간 기록 |
| `int[] selected` | 조합으로 선택된 바이러스 인덱스 |

## 

### 초기 설정

```java
if (map[i][j] == 2) virusList.add(new int[]{i, j});
else if (map[i][j] == 0) emptyCount++;

```

- 바이러스의 좌표를 `virusList`에 저장
- 빈 칸의 개수를 `emptyCount`로 카운트
- **빈 칸이 0개면 이미 다 감염 → 0 출력**

---

### 조합으로 M개의 바이러스 선택

```java
comb(0, 0, new int[M]);

```

- 모든 바이러스 중에서 `M개`를 뽑는 조합 수행
- M개를 모두 뽑으면 `bfs(selected)` 호출

### BFS로 감염 확산

```java
for (int idx : selected) {
    int[] v = virusList.get(idx);
    q.offer(new int[]{v[0], v[1]});
    time[v[0]][v[1]] = 0;
}

```

- 선택된 M개의 바이러스를 **동시에** 감염 시작점으로 큐에 넣음
- `time[r][c]`는 해당 칸이 감염된 시간을 기록

### 감염 시뮬레이션

```java
if (map[nr][nc] == 0) { // 빈 칸일 경우
    spread++;
    maxTime = time[nr][nc];
}

```

- BFS로 상하좌우 4방향으로 전염
- 새로 감염된 칸(`0`)이면 `spread` 증가
- 마지막 감염 시간(`maxTime`) 갱신

### 감염 완료 확인

```java
if (spread == emptyCount)
    answer = Math.min(answer, maxTime);

```

- 전체 빈 칸(`emptyCount`)과 감염된 칸(`spread`) 수가 같으면 성공
- 모든 조합 중 가장 작은 시간을 `answer`에 저장