package week_3;

import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public final class B_2667 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var count = Integer.parseInt(reader.readLine());
        var map = reader.lines()
                .limit(count)
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        for (int value : solution(map)) {
            writer.write(value + "\n");
        }

        writer.flush();
        writer.close();
        reader.close();
    }

    public static int[] solution(int[][] map) {
        var homes = new HashMap<Position, Boolean>();
        for (int y = 0; y < map.length; y++) {
            var array = map[y];
            for (int x = 0; x < array.length; x++) {
                homes.put(new Position(x, y), array[x] == 1);
            }
        }

        var adjList = new HashMap<Position, ArrayList<Position>>();
        for (var entry : homes.entrySet()) {
            if (!entry.getValue()) {
                continue;
            }

            var current = entry.getKey();
            for (var other : List.of(
                    new Position(current.x, current.y - 1),
                    new Position(current.x + 1, current.y),
                    new Position(current.x, current.y + 1),
                    new Position(current.x - 1, current.y)
            )) {
                if (homes.containsKey(other) && homes.get(other)) {
                    adjList.computeIfAbsent(current, key -> new ArrayList<>());
                    adjList.computeIfAbsent(other, key -> new ArrayList<>());
                    adjList.get(current).add(other);
                    adjList.get(other).add(current);
                }
            }
        }

        var visited = new HashSet<Position>();
        Supplier<Position> getAny = () -> {
            for (var entry : homes.entrySet()) {
                if (!entry.getValue() || visited.contains(entry.getKey())) {
                    continue;
                }

                return entry.getKey();
            }

            return null;
        };

        var counts = new ArrayList<Integer>();
        while (true) {
            var any = getAny.get();
            if (any == null) {
                break;
            }

            var queue = new LinkedList<Position>();
            queue.offer(any);
            visited.add(any);

            var count = 0;
            while (!queue.isEmpty()) {
                var poll = queue.poll();
                count++;

                var others = adjList.get(poll);
                if (others == null) continue;
                for (var other : others) {
                    if (visited.contains(other)) {
                        continue;
                    }

                    visited.add(other);
                    queue.offer(other);
                }
            }

            counts.add(count);
        }

        counts.sort(Comparator.naturalOrder());
        var result = new int[counts.size() + 1];
        result[0] = counts.size();
        var i = 1;
        for (var count : counts) {
            result[i++] = count;
        }

        return result;
    }

    public static class Position {
        public final int x;
        public final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
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
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
