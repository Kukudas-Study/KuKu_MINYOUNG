package week_4;

import java.io.*;
import java.util.LinkedList;

public final class B_7576 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var arrays = new int[M][N];
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ", N);

            var array = new int[N];
            for (int j = 0; j < N; j++) {
                array[j] = Integer.parseInt(input[j]);
            }

            arrays[i] = array;
        }

        writer.write(String.valueOf(solution(N, M, arrays)));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int N, int M, int[][] arrays) {
        // 번지는 거니까 직관적으로 BFS 선택
        var queue = new LinkedList<int[]>();

        // 최초 정점들 큐에 넣기
        for (int i = 0; i < arrays.length; i++) {
            var array = arrays[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == 1) {
                    queue.offer(new int[]{j, i, 0});
                }
            }
        }

        // 며칠이 걸렸는 지 = 최대 깊이
        // 복잡도를 위해 탐색하는 동시에 최대 깊이를 구한다.
        var max = 0;
        while (!queue.isEmpty()) {
            var node = queue.poll();

            // 최대 깊이를 구하는 구현
            var depth = node[2];
            if (max < depth) {
                max = depth;
            }

            for (var other : new int[][]{
                    new int[]{node[0] + 1, node[1], depth + 1},
                    new int[]{node[0] - 1, node[1], depth + 1},
                    new int[]{node[0], node[1] + 1, depth + 1},
                    new int[]{node[0], node[1] - 1, depth + 1}
            }) {
                if (0 > other[0] || other[0] >= N ||
                        0 > other[1] || other[1] >= M ||
                        arrays[other[1]][other[0]] != 0) {
                    continue;
                }

                queue.offer(other);
                arrays[other[1]][other[0]] = 1;
            }
        }

        // 탐색 후 익지 않은 토마토가 있는지 확인
        var flag = false;

        loop:
        for (var array : arrays) {
            for (int value : array) {
                if (value == 0) {
                    flag = true;
                    break loop;
                }
            }
        }

        // 익지 않은거 있으면 -1, 아니면 최대 깊이
        return flag ? -1 : max;
    }
}
