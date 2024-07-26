package week_5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class B_15650 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        writer.write(solution(
                Integer.parseInt(input[0]),
                Integer.parseInt(input[1])
        ));
        writer.flush();
        writer.close();
        reader.close();
    }

    // 결과를 정렬하는 함수
    public static String solution(int N, int M) {
        var destination = new ArrayList<String>();
        track(
                1,
                N,
                0,
                M,
                new int[M],
                new boolean[N + 1],
                destination
        );
        return String.join("\n", destination);
    }

    // 백트래킹을 사용한다.
    //
    // start부터 end까지의 수로 분기를 나눈다.
    // start는 매 분기마다 1씩 증가한다.
    //
    // 예를 들어,
    // 처음엔 1, 2, 3, 4
    // 그 다음엔 2, 3, 4
    // 그 다음엔 3, 4
    // 그 다음엔 4 이런 식이다.
    //
    // 조합을 구하는 것이기 때문에,
    // 뽑아야 할 수들은 이미 뽑은 수보다 커야하기 때문이다.
    //
    // length는 뽑아야 할 수의 양이다.
    // index는 현재 array의 index이다. List를 생성하는 대신 쓰는 것.
    // visited는 방문 배열.
    public static void track(
            int start,
            int end,
            int index,
            int length,
            int[] array,
            boolean[] visited,
            List<String> destination
    ) {
        // 다 뽑았으면 결과에 저장
        if (index == length) {
            destination.add(
                    Arrays.stream(array)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" "))
            );
            return;
        }

        // 이하 백트래킹 구현
        for (int number = start; number <= end; number++) {
            if (visited[number]) {
                continue;
            }

            array[index] = number;

            visited[number] = true;
            track(
                    number + 1,
                    end,
                    index + 1,
                    length,
                    array,
                    visited,
                    destination
            );
            visited[number] = false;
        }
    }
}
