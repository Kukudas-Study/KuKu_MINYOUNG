package week_5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class B_15649 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var strings = solution(N, M);
        for (int index = 0; index < strings.size(); index++) {
            if (index > 0) {
                writer.write("\n");
            }

            writer.write(strings.get(index));
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    // 백트래킹을 사용한다.
    public static List<String> solution(int N, int M) {
        // 자연수 배열
        var numbers = new int[N];
        for (int index = 0; index < N; index++) {
            numbers[index] = index + 1;
        }

        // 결과 담는 리스트
        // 매개변수에 대한 의미는 후술한다.
        var destination = new ArrayList<String>();
        track(
                new boolean[N + 1],
                numbers,
                new int[M],
                0,
                M,
                destination
        );
        return destination;
    }

    // 정보(?), 불변(?)에 해당하는 매개변수들
    // - visited 이미 뽑은 숫자 배열
    // - numbers 숫자를 뽑을 배열
    //
    // 값(?), 가변(?)에 해당하는 매개변수들
    // - array 뽑은 숫자 배열
    // - index 뽑은 숫자의 수 = numbers 현재 크기 - 1
    // - max 뽑을 숫자의 최대 수 = numbers 최대 크기 - 1
    //
    // 결과를 담는 리스트 
    // - destination
    public static void track(
            boolean[] visited,
            int[] numbers,
            int[] array,
            int index,
            int max,
            List<String> destination
    ) {
        // index가 max에 달했다면, (= 뽑아야 할 수의 양만큼 뽑았다면)
        // 재귀를 종료한다.
        if (index == max) {
            // destination에 결과를 담는다.
            //
            // 이 때, clone()을 통해 배열을 복사하거나,
            // 다음과 같이 mapping을 해야 한다.
            //
            // 그렇지 않으면 destination에는 모두 같은 값이 들어있다.
            // 왜냐하면 다른 분기로 진입한 재귀 함수가 같은 배열을 사용하기 때문이다.
            var builder = new StringBuilder();
            for (int number : array) {
                if (builder.length() > 0) {
                    builder.append(" ");
                }

                builder.append(number);
            }

            destination.add(builder.toString());
            return;
        }

        // 아직 수를 더 뽑야아 한다면, 수를 순회한다.
        for (int number : numbers) {
            // 이미 뽑은 수는 스킵한다.
            if (visited[number]) {
                continue;
            }

            // 뽑지 않은 수는 배열에 추가한다.
            array[index] = number;

            // 현재 상태를 토대로, 분기를 추가한다.
            // visited에는 현재 값을 true로 하여 다음 분기에서 현재 number를 뽑지 않도록 한다.
            // index는 1만큼 더하여 전달한다.
            visited[number] = true;
            track(visited, numbers, array, index + 1, max, destination);

            // visited에 number를 다시 false로 한다.
            // 다른 상태의 분기에서는 number를 아직 뽑지 않았기 때문이다.
            visited[number] = false;
        }
    }
}
