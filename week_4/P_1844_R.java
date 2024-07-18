package week_4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class P_1844_R {
    public static void main(String[] args) {
        System.out.println(new P_1844_R().solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        }) == 11);
        System.out.println(new P_1844_R().solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        }) == -1);
    }

    public int solution(int[][] maps) {
        var set = new HashSet<Node>();
        for (int i = 0; i < maps.length; i++) {
            var array = maps[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] == 0) {
                    continue;
                }

                var x = j + 1;
                var y = i + 1;
                set.add(new Node(x, y, -1));
            }
        }

        var start = new Node(1, 1, 1);
        var end = new Node(maps[0].length, maps.length, -1);

        var stack = new LinkedList<Node>();
        stack.offer(start);
        set.remove(start);

        while (!stack.isEmpty()) {
            var node = stack.pop();
            for (var other : List.of(
                    new Node(node.x, node.y - 1, node.depth + 1),
                    new Node(node.x - 1, node.y, node.depth + 1),
                    new Node(node.x, node.y + 1, node.depth + 1),
                    new Node(node.x + 1, node.y, node.depth + 1)
            )) {
                if (!set.contains(other)) {
                    continue;
                }

                // 왜 끝에 도착한 것들 중 최소는 틀리고
                // 가장 먼저 도착한 것이 답인지?
                if (other.equals(end)) {
                    return other.depth;
                }

                stack.offer(other);
                set.remove(other);
            }
        }

        return -1;
    }

    public static class Node {
        public final int x;
        public final int y;
        public final int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Node)) return false;
            var node = (Node) obj;
            return x == node.x && y == node.y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ") = " + depth;
        }
    }
}
