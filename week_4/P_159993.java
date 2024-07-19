package week_4;

import java.util.*;

public final class P_159993 {
    public static void main(String[] args) {
        System.out.println(new P_159993().solution(new String[]{
                "SOOOL",
                "XXXXO",
                "OOOOO",
                "OXXXX",
                "OOOOE"
        }) == 16);
        System.out.println(new P_159993().solution(new String[]{
                "LOOXS",
                "OOOOX",
                "OOOOO",
                "OOOOO",
                "EOOOO"
        }) == -1);
    }

    public int solution(String[] maps) {
        Node start = null;
        Node lever = null;
        Node exit = null;

        // 맵 생성
        var map = new HashMap<Node, Character>();

        // 행 순회
        for (int i = 0; i < maps.length; i++) {
            var chars = maps[i].toCharArray();

            // 열 순회
            for (int j = 0; j < chars.length; j++) {

                // 노드 생성
                var node = new Node(j, i, 0);

                // 노드 유형에 따라, 탐색을 위해 미리 저장
                switch (chars[j]) {
                    case 'S':
                        start = node;
                        break;
                    case 'L':
                        lever = node;
                        break;
                    case 'E':
                        exit = node;
                        break;
                }

                // 노드와 칸 유형을 채우기
                map.put(node, chars[j]);
            }
        }

        // 시작 - 레버 까지의 최단 거리 탐색
        var first = bfs(map, start, lever);

        // 도달할 수 없는 경우, -1을 반환
        if (first == -1) {
            return -1;
        }

        // 레버 - 출구 까지의 최단 거리 탐색
        var second = bfs(map, lever, exit);

        // 도달할 수 없는 경우, -1을 반환
        if (second == -1) {
            return -1;
        }

        // 정답은 두 최단 거리의 합이다.
        return first + second;
    }

    public int bfs(Map<Node, Character> map, Node start, Node end) {
        // 큐
        var queue = new LinkedList<Node>();
        queue.offer(start);

        // 방문 셋
        var visited = new HashSet<Node>();
        visited.add(start);

        // bfs 구현
        while (!queue.isEmpty()) {
            var node = queue.poll();

            // 인접 리스트는 간단하므로 미리 구현하지 않고 매 번 탐색
            // 상, 하, 좌, 우
            for (var other : List.of(
                    new Node(node.x + 1, node.y, node.depth + 1),
                    new Node(node.x - 1, node.y, node.depth + 1),
                    new Node(node.x, node.y + 1, node.depth + 1),
                    new Node(node.x, node.y - 1, node.depth + 1)
            )) {
                // 방문했거나, 벽이면 방문하지 않는다.
                if (visited.contains(other) ||
                        !map.containsKey(other) ||
                        map.get(other) == 'X') {
                    continue;
                }

                // 끝에 도달했다면, 해가 되므로 반환한다.
                if (other.equals(end)) {
                    return other.depth;
                }

                queue.offer(other);
                visited.add(other);
            }
        }

        // 끝에 도달하지 않았으면 -1을 반환한다.
        return -1;
    }

    // 노드 구현
    public static class Node {
        public final int x;
        public final int y;
        public final int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        // x, y에 대해서만 해쉬 코드를 생성한다.
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        // x, y에 대해서만 비교한다.
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Node)) {
                return false;
            }

            var node = (Node) obj;
            return x == node.x && y == node.y;
        }

        // 테스트 용도
        @Override
        public String toString() {
            return "{" + x + ", " + y + "} " + depth;
        }
    }
}
