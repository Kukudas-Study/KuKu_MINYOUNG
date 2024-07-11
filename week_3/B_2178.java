package week_3;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public final class B_2178 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 과정은 설명 생략
        var input = reader.readLine().split(" ");
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var arrays = new int[N][];
        for (int i = 0; i < N; i++) {
            input = reader.readLine().split("");

            var array = new int[M];
            for (int j = 0; j < M; j++) {
                array[j] = Integer.parseInt(input[j]);
            }

            arrays[i] = array;
        }

        // 출력 과정은 설명 생략
        writer.write(String.valueOf(solution(N, M, arrays)));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int N, int M, int[][] arrays) {
        // goal 골인 지점
        // goals 골인 지점에 닿은 Position 객체들, depth 확인용
        var goal = new Position(M, N, -1);
        var goals = new ArrayList<Position>();

        // 미로를 표현하는 map 선언, 키는 위치를 나타낸다.
        // 값은 해당 위치가 이동 가능한 칸인지 나타낸다.
        var map = new HashMap<Position, Boolean>();
        for (int i = 0; i < arrays.length; i++) {
            var array = arrays[i];
            for (int j = 0; j < array.length; j++) {
                map.put(new Position(j + 1, i + 1, -1), array[j] != 0);
            }
        }

        // BFS로 풀이, 큐 선언
        // 첫 위치를 집어넣는다.
        var queue = new LinkedList<Position>();
        var initPos = new Position(1, 1, 1);
        queue.push(initPos);

        // 방문 여부 set 생성
        // 첫 위치를 방문 처리한다.
        var visited = new HashSet<Position>();
        visited.add(initPos);

        while (!queue.isEmpty()) {
            // 최근에 방문한 정점을 맨 앞에서 꺼내온다.
            var poll = queue.poll();
            if (poll.equals(goal)) {
                goals.add(poll);
            }

            // 고차 함수 대신 사용
            Consumer<Position> consumer = (other) -> {
                // 이동 가능한지, 방문 가능한지 확인
                if (map.containsKey(other) &&
                        map.get(other) &&
                        !visited.contains(other)
                ) {
                    // 가능하면 큐에 추가, 방문 처리
                    queue.offer(other);
                    visited.add(other);
                }
            };

            // 고차 함수 대신 사용
            BiFunction<Integer, Integer, Position> biConsumer = (x, y) ->
                    new Position(poll.x + x, poll.y + y, poll.depth + 1);

            // 인접한 정점을 탐색한다.
            consumer.accept(biConsumer.apply(0, -1));
            consumer.accept(biConsumer.apply(1, 0));
            consumer.accept(biConsumer.apply(0, 1));
            consumer.accept(biConsumer.apply(-1, 0));
        }

        // 도달한 위치들 중 최단 거리 구하기
        // 해가 없는 입력은 X
        return goals.stream()
                .mapToInt(position -> position.depth)
                .min()
                .orElseThrow();
    }

    // HashMap, HashSet에 사용하기 위해 int[] 대신 클래스 생성
    public static class Position {
        public final int x;
        public final int y;
        public final int depth;

        public Position(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        // 테스트용
        @Override
        public String toString() {
            return depth > 0 ?
                    "(" + x + ", " + y + "): " + depth :
                    "(" + x + ", " + y + ")";
        }


        // depth 제외 구현
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        // depth 제외 구현
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Position)) {
                return false;
            }

            var other = (Position) obj;
            return x == other.x && y == other.y;
        }
    }
}
