package week_4;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public final class B_7562 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var count = Integer.parseInt(reader.readLine());
        for (int index = 0; index < count; index++) {
            var size = Integer.parseInt(reader.readLine());

            var input = reader.readLine().split(" ", 2);
            var start = new int[]{
                    Integer.parseInt(input[0]),
                    Integer.parseInt(input[1])
            };

            input = reader.readLine().split(" ", 2);
            var end = new int[]{
                    Integer.parseInt(input[0]),
                    Integer.parseInt(input[1])
            };

            if (index > 0) {
                writer.write("\n");
            }

            writer.write(String.valueOf(solution(size, start, end)));
        }


        writer.flush();
        writer.close();
        reader.close();
    }

    // 최단 거리이므로 bfs
    static int solution(int size, int[] start, int[] end) {
        // 시작 끝이면 거리 0 반환
        if (start[0] == end[0] && start[1] == end[1]) {
            return 0;
        }

        // bfs 구현
        // 이하 같은 내용은 설명 X
        var visited = new boolean[size][size];
        visited[start[0]][start[1]] = true;

        var queue = new LinkedList<int[]>();
        queue.offer(new int[]{start[0], start[1], 0});

        while (!queue.isEmpty()) {
            var node = queue.poll();
            // 나이트이므로 상하좌우 대신
            // 하나의 축으로 2칸, 다른 축으로 1칸 움직이는 경우 순회
            for (int[] other : List.of(
                    new int[]{node[0] + 2, node[1] + 1, node[2] + 1},
                    new int[]{node[0] + 2, node[1] - 1, node[2] + 1},
                    new int[]{node[0] - 2, node[1] + 1, node[2] + 1},
                    new int[]{node[0] - 2, node[1] - 1, node[2] + 1},
                    new int[]{node[0] + 1, node[1] + 2, node[2] + 1},
                    new int[]{node[0] - 1, node[1] + 2, node[2] + 1},
                    new int[]{node[0] + 1, node[1] - 2, node[2] + 1},
                    new int[]{node[0] - 1, node[1] - 2, node[2] + 1}
            )) {
                if (other[0] < 0 || other[0] >= size ||
                        other[1] < 0 || other[1] >= size ||
                        visited[other[0]][other[1]]) {
                    continue;
                }

                if (other[0] == end[0] && other[1] == end[1]) {
                    return other[2];
                }

                queue.offer(other);
                visited[other[0]][other[1]] = true;
            }
        }

        // 도달하지 못하는 경우는 없다.
        throw new AssertionError();
    }
}
