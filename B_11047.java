import java.io.*;

public final class B_11047 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var strings = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(strings[0]);
        var K = Integer.parseInt(strings[1]);

        // 동전 종류 배열
        var coins = new int[N];
        for (int index = 0; index < N; index++) {
            coins[index] = Integer.parseInt(reader.readLine());
        }

        writer.write(String.valueOf(solution(coins, K)));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int[] coins, int K) {
        var answer = 0;

        // 동전을 큰 순서대로 순회
        for (int index = coins.length - 1; index >= 0; index--) {
            var coin = coins[index];

            // 남은 가치에서 동전의 크기만큼 뺄 수 있다면 빼고,
            // 뺀 횟수만큼 나머지에 저장한다.
            answer += K / coin;
            K %= coin;
        }

        return answer;
    }
}
