import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class B_2960 {
    @Test
    void test() {
        assertEquals(solution("7 3"), "6");
        assertEquals(solution("15 12"), "7");
        assertEquals(solution("10 7"), "9");
        assertEquals(solution("20 11"), "3");
    }

    public String solution(String string) {
        var array = string.split(" ", 2);

        var N = Integer.parseInt(array[0]);
        var K = Integer.parseInt(array[1]);

        var flags = new boolean[N + 1];
        loop:
        for (int i = 2; i <= N; i++) {
            if (flags[i]) {
                continue;
            }

            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    continue loop;
                }
            }

            for (int j = i; j <= N; j += i) {
                if (flags[j]) {
                    continue;
                }

                flags[j] = true;

                if (--K == 0) {
                    return String.valueOf(j);
                }
            }
        }

        return null;
    }
}
