import java.io.*;

public final class B_22864 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));
        var array = reader.readLine().split(" ", 4);
        writer.write(String.valueOf(solution(
                Integer.parseInt(array[0]),
                Integer.parseInt(array[1]),
                Integer.parseInt(array[2]),
                Integer.parseInt(array[3])
        )));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int A, int B, int C, int M) {
        // i 처리한 일
        // j 피로도
        // k 시간
        int i = 0, j = 0, k = 0;

        // 24번(하루)만큼 반복
        while (k++ < 24) {
            // 일을 해도 피로도가 한계 미만일 거라면
            if (j + A <= M) {
                // 일을 처리하고, 피로도를 증가한다.
                i += B;
                j += A;
            } else {
                // 한계 이상일 거라면, 즉 일을 할 수 없을 거라면
                // 휴식하여 피로도가 감소된다.
                j -= C;

                // 피로도는 음수로 내려갈 수 없다.
                if (j < 0) {
                    j = 0;
                }
            }
        }

        return i;
    }
}
