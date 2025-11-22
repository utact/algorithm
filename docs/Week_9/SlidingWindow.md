# 슬라이딩 윈도우

## 🌻개념

고정 사이즈의 윈도우가 이동하면서 윈도우 내에 있는 데이터를 재활용

일정 범위 값을 비교하거나 연산할 때 사용

주로 배열과 그 부분배열을 어떤 조건하에서 계산하는 경우(구간합,부분 문자열)

<img width="908" height="181" alt="Image" src="https://github.com/user-attachments/assets/b19a6f6d-f4e0-494d-a99a-f677a08f3710" />

<img width="616" height="190" alt="Image" src="https://github.com/user-attachments/assets/da1f891c-5efa-4d97-9341-d614e80138ea" />

위 이미지를 보면, 투 포인터는 구간의 넓이가 조건에 따라 유동적으로 변하지만, 슬라이딩 윈도우는 구간이 넓이가 고정되어있다.

슬라이딩 윈도우 알고리즘은 연속되는 투 포인터와 유사하게 부분 배열들을 활용하여 특정 조건을 일치시키는 알고리즘이지만, **부분 배열의 길이(크기)가 고정적이다.**

**교집합의 정보를 공유하고, 차이가 나는 양쪽 끝 원소만 갱신하는 방법**을 통해 **배열이나 리스트의 요소의 일정 범위의 값을 비교**할 때 사용하면 매우 유용

## 🌻백준 12891_DNA 비밀번호

<img width="414" height="79" alt="Image" src="https://github.com/user-attachments/assets/2cb7f47f-567d-4929-8883-f9ffafe8797b" />

### 🌼풀이

중간에 있는 문자열은 체크하지않고 처음 부분은 제거하고 끝 부분을 추가해서 체크하기

<img width="467" height="106" alt="Image" src="https://github.com/user-attachments/assets/5ceef01b-4397-4dc8-85b4-3211b2902a75" />

슬라이딩 윈도우 알고리즘은 두 개의 포인터로 범위를 지정한 다음, 그 범위를 유지한 채로 이동하며 문제를 해결한다.

<img width="722" height="588" alt="Image" src="https://github.com/user-attachments/assets/96972122-a957-4fde-b8c9-d1bad1461453" />

예를들면, 슬라이딩 윈도우의 배열이 3인 슬라이딩 윈도우의 첫 번째 for문 범위는 아래와 같다.

<img width="816" height="602" alt="Image" src="https://github.com/user-attachments/assets/07c155d4-79a1-4d65-ba31-ecb4421ab186" />

### 🌼코드 동작 과정

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int S = sc.nextInt();   // 문자열 전체 길이
        int P = sc.nextInt();   // 부분 문자열 길이
        String dna = sc.next(); // DNA 문자열
        int[] minCnt = new int[4]; // A, C, G, T 최소 필요 개수

        for (int i = 0; i < 4; i++) {
            minCnt[i] = sc.nextInt();
        }

        int[] curCnt = new int[4]; // 현재 구간의 문자 개수
        char[] arr = dna.toCharArray();

        // 초기 윈도우 세팅
        for (int i = 0; i < P; i++) {
            addChar(curCnt, arr[i]);
        }

        int result = 0;
        if (check(curCnt, minCnt)) result++;

        // 슬라이딩 윈도우 이동
        for (int i = P; i < S; i++) {
            addChar(curCnt, arr[i]);       // 새 문자 추가
            removeChar(curCnt, arr[i - P]); // 빠진 문자 제거
            if (check(curCnt, minCnt)) result++;
        }

        System.out.println(result);
    }

    // 문자 추가
    private static void addChar(int[] curCnt, char c) {
        switch (c) {
            case 'A': curCnt[0]++; break;
            case 'C': curCnt[1]++; break;
            case 'G': curCnt[2]++; break;
            case 'T': curCnt[3]++; break;
        }
    }

    // 문자 제거
    private static void removeChar(int[] curCnt, char c) {
        switch (c) {
            case 'A': curCnt[0]--; break;
            case 'C': curCnt[1]--; break;
            case 'G': curCnt[2]--; break;
            case 'T': curCnt[3]--; break;
        }
    }

    // 조건 확인
    private static boolean check(int[] curCnt, int[] minCnt) {
        for (int i = 0; i < 4; i++) {
            if (curCnt[i] < minCnt[i]) return false;
        }
        return true;
    }
}

```

입력 받기

```java
int S = sc.nextInt();   // 문자열 전체 길이
int P = sc.nextInt();   // 부분 문자열 길이
String dna = sc.next(); // DNA 문자열
int[] N = new int[4];   // A, C, G, T 최소 개수 조건

```

예를 들어

- S = 9
- P = 8
- dna = "CCTGGATTG"
- N = {2, 0, 1, 1} (즉, A는 최소 2개, C는 최소 0개, G는 최소 1개, T는 최소 1개 필요)

초기 변수 세팅

```java
int start = 0;
int end = P; // 처음에는 [0, P) 구간을 검사
int count = 0;

```

while문: 구간 검사 반복

```java
while (end <= S) {
    int aCount = 0, cCount = 0, gCount = 0, tCount = 0;

    for (int i = start; i < end; i++) {
        switch (charArray[i]) {
            case 'A': aCount++; break;
            case 'C': cCount++; break;
            case 'G': gCount++; break;
            case 'T': tCount++; break;
        }
    }

    // 조건 만족 시 count 증가
    if (N[0] <= aCount && N[1] <= cCount && N[2] <= gCount && N[3] <= tCount) {
        count++;
    }

    // 구간을 오른쪽으로 한 칸 이동
    start++;
    end++;
}

```
