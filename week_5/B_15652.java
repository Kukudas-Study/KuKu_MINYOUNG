package week_5;

import java.io.*;

public final class B_15652 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);
        var builder = new StringBuilder();
        track(1, N, M, 0, new int[M], builder);
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        reader.close();
    }

    // visited 없는 백트래킹 구현 +
    //
    // 뽑을 수들은 뽑은 수의 최대를 초과해야 한다.
    // 근데 자기도 뽑을 수 있으니까 초과 X 이상 O
    //
    // 백트래킹 구현 설명은 생략
    public static void track(
            int start,
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

        for (int number = start; number <= N; number++) {
            array[depth] = number;
            track(number, N, M, depth + 1, array, builder);
        }
    }
}
