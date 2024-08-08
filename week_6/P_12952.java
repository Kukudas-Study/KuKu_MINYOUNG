package week_6;

import java.util.concurrent.atomic.AtomicInteger;

public final class P_12952 {
    public static void main(String[] args) {
        System.out.println(new P_12952().solution(4) == 2);
        System.out.println(new P_12952().solution(5) == 10);
        System.out.println(new P_12952().solution(6) == 4);
        System.out.println(new P_12952().solution(7) == 40);
        System.out.println(new P_12952().solution(8) == 92);
        System.out.println(new P_12952().solution(9) == 352);
        System.out.println(new P_12952().solution(10) == 724);
        System.out.println(new P_12952().solution(11) == 2680);
        System.out.println(new P_12952().solution(12) == 14200);
    }

    public int solution(int n) {
        var atomicInteger = new AtomicInteger();
        track(n, 0, new int[n], atomicInteger);
        return atomicInteger.get();
    }

    // y1 현재 탐색하는 줄의 위치
    // queens 퀸의 목록인데, int[n][2] 대신 키를 값으로 하여 int[n]을 사용한다.
    // atomicInteger 정답
    void track(
            int n,
            int y1,
            int[] queens,
            AtomicInteger atomicInteger
    ) {
        if (y1 == n) {
            atomicInteger.incrementAndGet();
            return;
        }

        // 모든 열을 순회한다.
        loop:
        for (var x1 = 0; x1 < n; x1++) {

            // 이전까지의 행을 순회한다.
            for (int y2 = 0; y2 < y1; y2++) {

                // 한 행마다 퀸이 위치한다.
                var x2 = queens[y2];

                // 안전하지 않으면 스킵한다.
                if (x1 == x2 || Math.abs(y1 - y2) == Math.abs(x1 - x2)) {
                    continue loop;
                }
            }

            // 백트래킹.
            // 이 때, 이 루프의 다음에 오는 퀸은 어짜피 key값, 즉 y값이 일치하므로
            // 덮어 씌워지므로 상태를 되돌릴 필요가 없다.
            queens[y1] = x1;
            track(n, y1 + 1, queens, atomicInteger);
        }
    }
}
