package week_4;

import java.io.*;

public final class B_15651 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);
        var builder = new StringBuilder();
        track(N, M, 0, new int[M], builder);
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        reader.close();
    }

    // visited 없는 백트래킹 구현
    // 백트래킹 구현 설명은 생략
    public static void track(
            int N,
            int M,
            int depth,
            int[] array,
            StringBuilder builder
    ) {
        if (depth == M) {
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

        for (int number = 1; number <= N; number++) {
            array[depth] = number;
            track(N, M, depth + 1, array, builder);
        }
    }
}
