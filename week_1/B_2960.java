package week_1;

import java.io.*;

public final class B_2960 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var strings = reader.readLine().split(" ", 2);
        writer.write(String.valueOf(solution(
                Integer.parseInt(strings[0]),
                Integer.parseInt(strings[1])
        )));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int N, int K) {
        // flags 지워졌는 지 여부를 담는 배열
        // 어떤 수에 대하여, 빈 값이 false 이므로 지워졌다면 true 이다.
        var flags = new boolean[N + 1];

        // 2 이하의 수는 판별할 필요가 없으므로 2부터 N까지 순회한다.
        loop:
        for (int i = 2; i <= N; i++) {
            // 이미 지워진 수라면 스킵한다.
            if (flags[i]) {
                continue;
            }

            // 2부터 i의 제곱근까지 순회한다. 순회하는 수가 j라고 할 때,
            // i % j == 0일 경우, 즉 나누어 떨어지는 수가 있을 경우,
            // 소수가 아니므로 바깥 순회를 스킵한다.
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    continue loop;
                }
            }

            // 소수일 경우,
            // 탐색 범위의 한계까지 자신의 배수를 순회한다.
            for (int j = i; j <= N; j += i) {
                // 이미 지워진 수라면 스킵한다.
                if (flags[j]) {
                    continue;
                }

                // 수를 지운다.
                flags[j] = true;

                // 지운 횟수를 카운팅하고,
                // 찾을 인덱스와 일치하면 답이므로 반환한다.
                if (--K == 0) {
                    return j;
                }
            }
        }

        return -1;
    }
}
