package week_5;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class B_15657 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        input = reader.readLine().split(" ", N);
        var numbers = new int[N];
        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(input[index]);
        }

        writer.write(solution(M, numbers));
        writer.flush();
        writer.close();
        reader.close();
    }

    static String solution(int M, int[] numbers) {
        // 사전 정렬 및 탐색을 위해 정렬
        Arrays.sort(numbers);

        // 백트래킹 구현
        var destination = new StringBuilder();
        track(
                M,
                0,
                0,
                new int[M],
                numbers,
                destination
        );
        return destination.toString();
    }

    // start로 탐색을 끝낸 번호는 제외한다.
    // 그 외엔 걍 백트래킹 구현이므로 설명 X
    static void track(
            int maxDepth,
            int depth,
            int start,
            int[] array,
            int[] numbers,
            StringBuilder destination
    ) {
        if (depth == maxDepth) {
            if (destination.length() > 0) {
                destination.append("\n");
            }

            destination.append(
                    Arrays.stream(array)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" "))
            );
            return;
        }

        for (int index = start; index < numbers.length; index++) {
            int number = numbers[index];
            array[depth] = number;
            track(maxDepth, depth + 1, index, array, numbers, destination);
        }
    }
}
