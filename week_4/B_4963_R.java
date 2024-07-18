package week_4;

import java.io.*;
import java.util.*;

// 클래스 생성 없이 다시 풀기
// 1012번과 같은 풀이
public final class B_4963_R {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var firstLine = true;
        while (true) {
            var split = reader.readLine().split(" ", 2);

            var W = Integer.parseInt(split[0]);
            var H = Integer.parseInt(split[1]);
            if (W == 0 & H == 0) {
                break;
            }

            var arrays = new int[H][W];
            for (int i = 0; i < H; i++) {
                split = reader.readLine().split(" ", W);

                var array = new int[W];
                for (int j = 0; j < split.length; j++) {
                    array[j] = Integer.parseInt(split[j]);
                }

                arrays[i] = array;
            }

            if (firstLine) {
                firstLine = false;
            } else {
                writer.write("\n");
            }

            writer.write(String.valueOf(solution(arrays)));
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int[][] arrays) {

        // 땅이 있는 칸을 저장한다.
        var set = new HashSet<Position>();
        for (int i = 0; i < arrays.length; i++) {
            var array = arrays[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == 1) {
                    set.add(new Position(i, j));
                }
            }
        }

        // 8방향의 인접 리스트를 생성한다.
        var map = new HashMap<Position, List<Position>>();
        for (var position : set) {
            map.computeIfAbsent(position, key -> new ArrayList<>());

            for (var other : List.of(
                    new Position(position.x, position.y + 1),
                    new Position(position.x, position.y - 1),
                    new Position(position.x - 1, position.y),
                    new Position(position.x + 1, position.y),
                    new Position(position.x + 1, position.y + 1),
                    new Position(position.x - 1, position.y + 1),
                    new Position(position.x + 1, position.y - 1),
                    new Position(position.x - 1, position.y - 1)
            )) {
                if (set.contains(other)) {
                    map.get(position).add(other);
                }
            }
        }

        // 방문 여부를 확인하는 set 생성, 정답 변수 생성
        var visited = new HashSet<Position>();
        var answer = 0;

        // 방문 가능한 점을 찾아서 탐색한다.
        // 탐색하고 나면 인접한 점들은 방문 불가능하므로,
        // 이 루프의 횟수가 정답이 된다.
        while (true) {
            // 어느 점이라도 방문하여 탐색했는 지의 여부이다.
            var dirty = false;

            // 모든 점을 순회한다.
            for (var start : set) {
                // 방문한 점은 스킵한다.
                if (visited.contains(start)) {
                    continue;
                }

                // 방문하지 않은 점으로 부터 탐색을 시작한다.
                // 탐색을 시작하였으므로, 탐색 여부를 참으로 한다.
                dirty = true;

                // 탐색할 곳이 없을 때 까지 탐색해야 하므로,
                // DFS로 탐색한다. (스택 이용)
                var stack = new Stack<Position>();
                stack.push(start);

                // 이하 DFS의 구현
                while (!stack.isEmpty()) {
                    var pop = stack.pop();
                    if (visited.contains(pop)) {
                        continue;
                    }

                    visited.add(pop);

                    var others = map.get(pop);
                    if (others == null) {
                        break;
                    }

                    for (var other : others) {
                        if (!visited.contains(other)) {
                            stack.push(other);
                        }
                    }
                }

                answer++;
            }

            // 어느 점이라도 방문하여 탐색하지 않았다면,
            // 반복을 종료한다.
            if (!dirty) {
                break;
            }
        }

        return answer;
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

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

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
