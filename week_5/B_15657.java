package week_5;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class B_15657 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
        Arrays.sort(numbers);
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
