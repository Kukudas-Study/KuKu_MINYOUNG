package week_3;

import java.io.*;
import java.util.ArrayList;

// 다시 풀기
public final class B_2798_R {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = reader.readLine().split(" ", 2);

        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var numbers = new int[N];
        var strings = reader.readLine().split(" ", N);
        for (int index = 0; index < strings.length; index++) {
            numbers[index] = Integer.parseInt(strings[index]);
        }

        writer.write(String.valueOf(solution(M, numbers)));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int M, int[] numbers) {
        var dest = new ArrayList<Integer>();

        var visited = new boolean[100_000];
        for (int number : numbers) {
            visited[number] = true;
            comb(number, 1, numbers, visited, M, dest);
            visited[number] = false;
        }

        return dest.stream().mapToInt(it -> it).max().orElseThrow();
    }

    public static void comb(
            int sum,
            int depth,
            int[] numbers,
            boolean[] visited,
            int M,
            ArrayList<Integer> dest
    ) {
        if (depth == 3) {
            if (sum <= M) {
                dest.add(sum);
            }

            return;
        }

        for (int number : numbers) {
            if (visited[number]) {
                continue;
            }

            visited[number] = true;
            comb(sum + number, depth + 1, numbers, visited, M, dest);
            visited[number] = false;
        }
    }
}
