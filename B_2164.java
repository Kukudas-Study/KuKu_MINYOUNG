import java.io.*;
import java.util.LinkedList;

public final class B_2164 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(String.valueOf(solution(Integer.parseInt(reader.readLine()))));
        writer.flush();
        writer.close();
        reader.close();
    }

    // count 카드의 수
    public static int solution(int count) {
        var deque = new LinkedList<Integer>();

        // 카드를 LinkedList에 담는다.
        for (var number = 1; number <= count; number++) {
            deque.add(number);
        }

        // 카드가 비어있지 않을 때,
        while (deque.size() > 1) {
            // 맨 앞을 버린다.
            deque.pollFirst();
            // 다시 맨 앞을 맨 아래로 옮긴다.
            deque.add(deque.pollFirst());
        }

        return deque.getFirst();
    }
}
