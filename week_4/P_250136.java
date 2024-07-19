package week_4;

import java.util.*;

public final class P_250136 {
    public static void main(String[] args) {
        System.out.println(new P_250136().solution(new int[][]{
                {0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1}
        }) == 9);
        System.out.println(new P_250136().solution(new int[][]{
                {1, 0, 1, 0, 1, 1},
                {1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        }) == 16);
    }

    // 효율성 빡빡한 문제
    //
    // 1. 노드 class 대신 int[] 사용
    // 2. 배열 값, 길이 등 미리 꺼내두기
    //
    public int solution(int[][] land) {
        // 열의 수, 행의 수
        var width = land[0].length;
        var height = land.length;

        // 배열을 map으로 변환,
        // true면 석유, 아니면 걍 땅
        var suckus = new boolean[height][width];

        // 행 순회
        for (int i = 0; i < height; i++) {

            // 열 순회
            var array = land[i];
            for (int j = 0; j < width; j++) {

                // 석유면 true로 변환
                if (array[j] == 1) {
                    suckus[i][j] = true;
                }
            }
        }

        // count란 한 열이 뽑을 수 있는 석유의 양
        // max는 count 중 최댓값

        // counts의 key는 한 열, value는 해당 열이 뽑아낸 석유의 양이다.
        var max = Integer.MIN_VALUE;
        var counts = new int[width];

        // 모든 석유를 순회하며 bfs 실행
        // 이 때, 효율성을 위해 map을 visited로 사용한다.
        // 행 순회
        for (int i = 0; i < height; i++) {
            // 열 순회
            for (int j = 0; j < width; j++) {
                // 석유 아니거나 방문했으면 스킵
                if (!suckus[i][j]) {
                    continue;
                }

                // 석윤데 방문 안했으면 탐색
                // 이하 bfs 구현
                var queue = new LinkedList<int[]>();
                queue.offer(new int[]{i, j});
                suckus[i][j] = false;

                // bfs를 하며 다음 값들을 구함.
                //
                // 1. keys는 방문한 모든 열을 저장함
                // 2. count는 이동한 횟수, 즉 석유 덩어리의 크기
                var keys = new HashSet<Integer>();
                var count = 0;
                while (!queue.isEmpty()) {
                    var node = queue.poll();

                    // 미리 꺼내두기
                    var x = node[0];
                    var y = node[1];

                    // keys, count에 대한 처리
                    count++;
                    keys.add(y);

                    // 상하좌우 돌기
                    // List.of 안쓰면 더 빠르긴 할듯
                    for (var other : List.of(
                            new int[]{x, y + 1},
                            new int[]{x, y - 1},
                            new int[]{x + 1, y},
                            new int[]{x - 1, y}
                    )) {
                        x = other[0];
                        y = other[1];

                        // Node 클래스가 없으므로,
                        // 직접 유효성을 확인해야 한다.
                        //
                        // 맵을 벗어나지 않으며, 방문하지 않았는지 확인
                        // 벗어나거나 방문하면 스킵
                        if (0 > x || x >= height ||
                                0 > y || y >= width ||
                                !suckus[x][y]) {
                            continue;
                        }

                        // 안 벗어났고 방문 안했으면 방문한 정점으로 처리한다.
                        queue.offer(other);
                        suckus[x][y] = false;
                    }
                }

                // 한 석유 덩어리에 대한 탐색을 마친후,
                // 방문했던 keys들에 대하어 counts를 누적한다.
                for (var key : keys) {
                    // 다음 값, 즉 누적한 값이 다른 최대 누적값 보다 크다면,
                    // 최대 누적 값을 다음 값으로 한다.
                    var next = counts[key] + count;
                    if (next > max) {
                        max = next;
                    }

                    // counts에 누적한다.
                    counts[key] = next;
                }
            }
        }

        // 최대 누적값을 반환한다.
        return max;
    }
}
