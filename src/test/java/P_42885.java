import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class P_42885 {
    @Test
    void test() {
        assertEquals(solution(new int[]{70, 50, 80, 50}, 100), 3);
        assertEquals(solution(new int[]{70, 80, 50}, 100), 3);
    }

    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        var answer = 0;
        var j = people.length - 1;

        loop:
        for (int i = 0; i < people.length; i++) {
            var x = people[i];
            if (x == -1) {
                continue;
            }

            for (; j > i; j--) {
                var y = people[j];
                if (y == -1 || x + y > limit) {
                    continue;
                }

                people[i] = -1;
                people[j] = -1;
                answer++;
                continue loop;
            }

            people[i] = -1;
            answer++;
        }

        return answer;
    }
}
