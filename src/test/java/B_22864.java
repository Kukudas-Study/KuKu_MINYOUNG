import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class B_22864 {
    @Test
    void test() {
        assertEquals(solution("5 3 2 10"), "24");
        assertEquals(solution("10 5 1 10"), "15");
        assertEquals(solution("11 5 1 10"), "0");
    }

    public String solution(String string) {
        var array = string.split(" ", 4);

        var A = Integer.parseInt(array[0]);
        var B = Integer.parseInt(array[1]);
        var C = Integer.parseInt(array[2]);
        var M = Integer.parseInt(array[3]);

        var i = 0;
        var j = 0;
        var k = 0;

        while (k++ < 24) {
            if (j + A <= M) {
                i += B;
                j += A;
            } else {
                j -= C;
                if (j < 0) {
                    j = 0;
                }
            }
        }

        return String.valueOf(i);
    }
}
