package week_5;

import java.io.*;

public final class B_1913_R {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var N = Integer.parseInt(reader.readLine());
        var M = Integer.parseInt(reader.readLine());

        int mX = 0;
        int mY = 0;

        var map = solution(N);
        for (int i = 0; i < map.length; i++) {
            if (i > 0) {
                writer.write("\n");
            }

            var array = map[i];
            for (int j = 0; j < array.length; j++) {
                if (j > 0) {
                    writer.write(" ");
                }

                var value = array[j];
                if (value == M) {
                    mX = j + 1;
                    mY = i + 1;
                }

                writer.write(String.valueOf(value));
            }
        }

        writer.write("\n" + mY + " " + mX);
        writer.flush();
        writer.close();
        reader.close();
    }

    static int[][] solution(int N) {
        var dirIdx = 0;
        var dirX = 0;
        var dirY = 1;

        var map = new int[N][N];
        var number = N * N;

        var curX = 0;
        var curY = -1;
        while (number > 0) {
            var nextX = curX + dirX;
            var nextY = curY + dirY;
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || map[nextY][nextX] > 0) {
                if (dirIdx == 3) {
                    dirIdx = 0;
                } else {
                    dirIdx++;
                }

                switch (dirIdx) {
                    case 0:
                        dirX = 0;
                        dirY = 1;
                        break;
                    case 1:
                        dirX = 1;
                        dirY = 0;
                        break;
                    case 2:
                        dirX = 0;
                        dirY = -1;
                        break;
                    case 3:
                        dirX = -1;
                        dirY = 0;
                        break;
                }

                continue;
            }

            curX = nextX;
            curY = nextY;

            map[curY][curX] = number--;
        }

        return map;
    }
}
