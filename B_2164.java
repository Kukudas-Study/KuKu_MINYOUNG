import java.util.LinkedList;

public final class B_2164 {
    public static void main(String[] args) {
        System.out.println(new B_2164().solution("1") == 1);
        System.out.println(new B_2164().solution("4") == 4);
        System.out.println(new B_2164().solution("6") == 4);
    }

    public int solution(String string) {
        var count = Integer.parseInt(string);
        var deque = new LinkedList<Integer>();

        for (var number = 1; number <= count; number++) {
            deque.add(number);
        }

        while (deque.size() > 1) {
            deque.pollFirst();
            deque.add(deque.pollFirst());
        }

        return deque.getFirst();
    }
}
