import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class B_2164 {
    @Test
    void test() {
        assertEquals(new B_2164().solution("1"), "1");
        assertEquals(new B_2164().solution("4"), "4");
        assertEquals(new B_2164().solution("6"), "4");
    }

    public String solution(String string) {
        var count = Integer.parseInt(string);
        var deque = new LinkedList<Integer>();

        for (var number = 1; number <= count; number++) {
            deque.add(number);
        }

        while (deque.size() > 1) {
            deque.pollFirst();
            deque.add(deque.pollFirst());
        }

        return String.valueOf(deque.getFirst());
    }
}
