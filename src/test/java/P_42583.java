import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class P_42583 {
    @Test
    void test() {
        assertEquals(solution(2, 10, new int[]{7, 4, 5, 6}), 8);
        assertEquals(solution(100, 100, new int[]{10}), 101);
        assertEquals(solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}), 110);
        assertEquals(solution(10, 100, new int[]{50, 30, 10, 10, 30, 10, 40}), 23);
        assertEquals(solution(10, 10, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}), 73);
        assertEquals(solution(10, 12, new int[]{4, 4, 4, 2, 2, 1, 1, 1, 1}), 26);
        assertEquals(solution(10, 12, new int[]{1, 1, 1, 1, 2, 2, 4, 4, 4}), 26);
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        var queue = new LinkedList<Integer>();
        for (var truck : truck_weights) {
            queue.offer(truck);
        }

        var list = new ArrayList<Map.Entry<Integer, Integer>>();
        var time = 0;
        do {
            var iterator = list.iterator();
            var i = -1;
            while (iterator.hasNext()) {
                var entry = iterator.next();
                var next = entry.getValue() + 1;
                if (next >= bridge_length) {
                    iterator.remove();
                    continue;
                }

                i++;
                list.set(i, Map.entry(entry.getKey(), next));
            }

            if (list.size() < bridge_length && !queue.isEmpty()) {
                int sum = 0;
                for (var entry : list) {
                    sum += entry.getKey();
                }

                if (sum + queue.peek() <= weight) {
                    list.add(Map.entry(queue.poll(), 0));
                }
            }

            time++;
        } while (!list.isEmpty());
        return time;
    }
}
