package week_3;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public final class B_2606 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var vertexCount = Integer.parseInt(reader.readLine());
        var edgeCount = Integer.parseInt(reader.readLine());
        var edges = new int[edgeCount][];
        for (int index = 0; index < edgeCount; index++) {
            var array = reader.readLine().split(" ", 2);
            edges[index] = new int[]{
                    Integer.parseInt(array[0]),
                    Integer.parseInt(array[1])
            };
        }

        writer.write(String.valueOf(solution(vertexCount, edges)));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int vertexCount, int[][] edges) {
        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], (key) -> new ArrayList<>());
            map.computeIfAbsent(edge[1], (key) -> new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        var queue = new LinkedList<Integer>();
        queue.offer(1);

        var visited = new boolean[vertexCount + 1];
        visited[1] = true;

        var answer = -1;
        while (!queue.isEmpty()) {
            var poll = queue.poll();
            answer++;

            var others = map.get(poll);
            if (others == null) {
                continue;
            }

            for (int other : others) {
                if (visited[other]) {
                    continue;
                }

                queue.push(other);
                visited[other] = true;
            }
        }

        return answer;
    }
}
