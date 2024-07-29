package week_5;

import java.io.*;
import java.util.LinkedList;

public final class B_2573 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        String[] input;

        input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var map = new int[N][M];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ", M);

            var row = new int[M];
            for (int j = 0; j < M; j++) {
                row[j] = Integer.parseInt(input[j]);
            }

            map[i] = row;
        }

        writer.write(String.valueOf(solution(N, M, map)));
        writer.flush();
        writer.close();
        reader.close();
    }

    static int solution(int N, int M, int[][] map) {
        var time = 0;
        while (true) {
            // 현재 상태를 확인한다.
            switch (getStatus(N, M, map)) {
                    // 바닷물이 다 녹았으면 0을 출력한다.
                case -1:
                    return 0;
                    // 덩어리가 2개 이상이라면 지난 시간을 출력한다.
                case 1:
                    return time;
            }

            tick(N, M, map);
            ++time;
        }
    }

    // 빙산을 1회 녹이는 메소드
    static void tick(int N, int M, int[][] map) {
        // visited 순회하면서 배열이 변화하므로, 어떤 빙산이 녹아 0이 되었을 때,
        //         그 빙산과 인접한 다음 빙산이 영향을 받는 것을 방지하기 위해,
        //         이 방문 배열을 선언한다. 이 방문 배열에 존재하는 노드는 0이어도,
        //         다른 빙산을 녹일 수 없다.
        var visited = new boolean[N][M];

        // 모든 바닷물에 대하여 순회
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 바닷물이 아니거나, visited에 포함이라면 스킵
                if (map[i][j] > 0 || visited[i][j]) {
                    continue;
                }

                // 상하좌우의 인접 노드를 순회한다.
                for (var other : new int[][]{
                        {i + 1, j},
                        {i - 1, j},
                        {i, j + 1},
                        {i, j - 1}
                }) {
                    // 인접 노드가 맵을 벗어났거나, 바닷물이면 스킵한다.
                    var x = other[0];
                    var y = other[1];
                    if (x < 0 || x >= N ||
                            y < 0 || y >= M ||
                            map[x][y] == 0) {
                        continue;
                    }

                    // 빙산이라면 녹이고 방문 배열에 추가한다.
                    visited[x][y] = true;
                    map[x][y]--;
                }
            }
        }
    }

    // 상태를 확인하는 메소드
    //
    // 1은 빙산이 두 덩어리 이상으로 분리되었음을 의미한다.
    // 0은 빙산이 한 덩어리임을 의미한다.
    // -1은 빙산이 모두 녹아 없어졌음을 의미한다.
    //
    static int getStatus(int N, int M, int[][] map) {
        // count 탐색한 횟수
        // visited bfs를 위한 방문 배열
        var count = 0;
        var visited = new boolean[N][M];

        // 빙산을 모두 순회
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 바닷물이거나 탐색했으면 스킵
                if (map[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                // 이미 한 번 탐색했다면, 덩어리가 2개란 의미이므로,
                // 1을 리턴한다.
                if (++count > 1) {
                    return 1;
                }

                // 이하 bfs 구현
                // 모든 인접한 빙산을 탐색하여 방문 처리한다.
                var queue = new LinkedList<int[]>();
                queue.push(new int[]{j, i});

                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    var node = queue.poll();
                    for (var other : new int[][]{
                            {node[0] + 1, node[1]},
                            {node[0] - 1, node[1]},
                            {node[0], node[1] + 1},
                            {node[0], node[1] - 1}
                    }) {
                        if (other[0] < 0 || other[0] >= M ||
                                other[1] < 0 || other[1] >= N ||
                                map[other[1]][other[0]] == 0 ||
                                visited[other[1]][other[0]]) {
                            continue;
                        }

                        queue.offer(other);
                        visited[other[1]][other[0]] = true;
                    }
                }
            }
        }

        // 탐색한 횟수가 0이면 모두 바닷물이므로, -1을 반환한다.
        // 아니면 1을 반환한다.
        return count == 0 ? -1 : 0;
    }
}
