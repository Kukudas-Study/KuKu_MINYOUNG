package week_5;

import java.io.*;

public final class B_2563 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var count = Integer.parseInt(reader.readLine());
        var array = new int[count][2];
        for (int index = 0; index < count; index++) {
            var input = reader.readLine().split(" ", 2);
            array[index] = new int[]{
                    Integer.parseInt(input[0]),
                    Integer.parseInt(input[1])
            };
        }

        writer.write(String.valueOf(solution(array)));
        writer.flush();
        writer.close();
        reader.close();
    }

    static int solution(int[][] papers) {
        // count 답
        // map 도화지를 의미하는 배열
        var count = 0;
        var map = new boolean[100][100];

        // 색종이를 순회
        for (int[] paper : papers) {

            // i는 색종이 좌측 상단 끝 x
            // j는 색종이 좌측 상단 끝 y
            //
            // i + 9는 색종이 우측 하단 끝 x
            // j + 9는 색종이 우측 하단 끝 y
            //
            // 이를 순회한다.
            var i = paper[0];
            var j = paper[1];
            for (int k = i; k < i + 10; k++) {
                for (int l = j; l < j + 10; l++) {
                    // 안칠해져 있으면
                    if (!map[l][k]) {
                        // 칠하고 카운트
                        count++;
                        map[l][k] = true;
                    }

                }
            }
        }

        return count;
    }
}
