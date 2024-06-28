import java.util.LinkedList;

public final class B_2164 {
    public static void main(String[] args) {
        System.out.println(new B_2164().solution("1").equals("1"));
        System.out.println(new B_2164().solution("4").equals("4"));
        System.out.println(new B_2164().solution("6").equals("4"));
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
