package week_5;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public final class B_15655 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        String[] input;

        input = reader.readLine().split(" ", 2);
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
        // 사전 순 + start가 있으므로 정렬
        Arrays.sort(numbers);
        var builder = new StringBuilder();
        track(
                M,
                0,
                0,
                new int[M],
                numbers,
                new HashSet<>(),
                builder
        );
        return builder.toString();
    }

    // 중복 뽑기 안되므로 visited set
    // 중복 수열 안되므로 start
    // 그 외엔 걍 백트래킹이니 설명 안함
    static void track(
            int maxDepth,
            int depth,
            int start,
            int[] array,
            int[] numbers,
            HashSet<Integer> visited,
            StringBuilder builder
    ) {
        if (depth == maxDepth) {
            if (builder.length() > 0) {
                builder.append("\n");
            }

            for (int index = 0; index < array.length; index++) {
                if (index > 0) {
                    builder.append(" ");
                }

                builder.append(array[index]);
            }

            return;
        }

        for (int index = start; index < numbers.length; index++) {
            int number = numbers[index];
            if (visited.contains(number)) {
                continue;
            }

            array[depth] = number;

            visited.add(number);
            track(
                    maxDepth,
                    depth + 1,
                    index,
                    array,
                    numbers,
                    visited,
                    builder
            );
            visited.remove(number);
        }
    }
}
