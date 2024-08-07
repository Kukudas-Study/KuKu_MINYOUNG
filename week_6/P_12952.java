package week_6;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class P_12952 {
    public static void main(String[] args) {
        System.out.println(new P_12952().solution(4) == 2);
        System.out.println(new P_12952().solution(5) == 10);
        System.out.println(new P_12952().solution(6) == 4);
        System.out.println(new P_12952().solution(7) == 40);
        System.out.println(new P_12952().solution(8) == 92);
    }

    public int solution(int n) {
        var atomicInteger = new AtomicInteger(0);
        track(n, 0, new boolean[n], new ArrayList<>(), atomicInteger);
        return atomicInteger.get();
    }

    void track(
            int n,
            int index,
            boolean[] skips,
            ArrayList<int[]> queens,
            AtomicInteger atomicInteger
    ) {
        if (queens.size() == n) {
            atomicInteger.incrementAndGet();
            return;
        }

        outer:
        for (var y1 = index; y1 < n; y1++) {
            inner:
            for (var x1 = 0; x1 < n; x1++) {
                if (skips[x1]) {
                    continue;
                }

                for (var queen : queens) {
                    var x2 = queen[0];
                    if (x1 == x2) {
                        continue outer;
                    }

                    var y2 = queen[1];
                    if (y1 == y2) {
                        continue inner;
                    }

                    if (x1 - y1 == x2 - y2 || x1 + y1 == x2 + y2) {
                        continue inner;
                    }
                }

                var queen = new int[]{x1, y1};
                queens.add(queen);
                skips[x1] = true;
                track(n, index + 1, skips, queens, atomicInteger);
                skips[x1] = false;
                queens.remove(queens.size() - 1);
            }
        }
    }
}
