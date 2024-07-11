package week_3;

import java.io.*;
import java.util.*;

public final class B_1260 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = reader.readLine().split(" ", 3);

        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);
        var V = Integer.parseInt(input[2]);

        var E = new int[M][];
        for (int index = 0; index < M; index++) {
            input = reader.readLine().split(" ", 2);
            E[index] = new int[]{
                    Integer.parseInt(input[0]),
                    Integer.parseInt(input[1])
            };
        }

        writer.write(dfs(N, V, E) + "\n" + bfs(N, V, E));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static String dfs(int N, int V, int[][] E) {
        var strings = new ArrayList<String>();

        // 간선들로 인접 리스트 생성
        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : E) {
            // 어떤 정점에 대하여, 리스트가 비어있다면 넣어준다.
            map.computeIfAbsent(edge[0], (key) -> new ArrayList<>());
            map.computeIfAbsent(edge[1], (key) -> new ArrayList<>());

            // 서로 다른 두 정점의 리스트에 서로를 추가한다.
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // 분기 시 작은 정점을 먼저 방문하는 것이 우선이고,
        // 스택을 사용하므로 내림차순으로 정렬해준다.
        for (var value : map.values()) {
            value.sort(Comparator.reverseOrder());
        }

        // 스택을 선언한다. 스택에 있는 정점은, 방문해야 할 정점을 의미한다.
        // 첫 정점을 넣어준다.
        var stack = new Stack<Integer>();
        stack.push(V);

        // 정점을 이미 방문했는 지 확인하는 배열을 생성한다.
        // 정점은 0부터 시작하므로 크기는 정점의 수 + 1이다.
        var visited = new boolean[N + 1];

        // 방문해야 할 정점이 없을 때 까지, 반복한다.
        while (!stack.isEmpty()) {

            // 방문해야 할 정점을 맨 위에서 꺼낸다.
            var pop = stack.pop();

            // 만약 방문한 정점일 경우, 스킵한다.
            if (visited[pop]) {
                continue;
            }

            // 방문한 정점으로 처리하고, 결과에 추가한다.
            visited[pop] = true;
            strings.add(String.valueOf(pop));

            // 인접한 정점의 리스트를 가져온다.
            var others = map.get(pop);

            // 없을 경우, 고립된 정점이므로, 방문해야 할 다른 정점도 없다.
            // 그러므로 반복을 종료한다.
            if (others == null) {
                break;
            }

            // 인접한 정점을 순회한다.
            for (int other : others) {
                // 만약 방문한 정점일 경우, 스킵한다.
                if (visited[other]) {
                    continue;
                }

                // 방문해야 할 정점으로 처리한다.
                stack.push(other);
            }
        }

        return String.join(" ", strings);
    }

    public static String bfs(int N, int V, int[][] E) {
        var strings = new ArrayList<String>();

        // 간선들로 인접 리스트 생성
        var map = new HashMap<Integer, ArrayList<Integer>>();
        for (int[] edge : E) {
            // 어떤 정점에 대하여, 리스트가 비어있다면 넣어준다.
            map.computeIfAbsent(edge[0], (key) -> new ArrayList<>());
            map.computeIfAbsent(edge[1], (key) -> new ArrayList<>());

            // 서로 다른 두 정점의 리스트에 서로를 추가한다.
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // 분기 시 작은 정점을 먼저 방문하는 것이 우선이고,
        // 큐를 사용하므로 오름차순으로 정렬해준다.
        for (var value : map.values()) {
            value.sort(Comparator.naturalOrder());
        }

        // 큐를 선언한다. 큐에 있는 정점은, 최근에 방문한 정점을 의미한다.
        // 첫 정점을 넣어준다.
        var queue = new LinkedList<Integer>();
        queue.offer(V);

        // 정점을 이미 방문했는 지 확인하는 배열을 생성한다.
        // 정점은 0부터 시작하므로 크기는 정점의 수 + 1이다.
        var visited = new boolean[N + 1];

        // 첫 정점을 방문 처리한다.
        visited[V] = true;

        // 최근에 방문한 정점이 없을 때 까지 순회한다.
        while (!queue.isEmpty()) {
            // 최근에 방문한 정점을 맨 앞에서 꺼내온다.
            var poll = queue.poll();
            strings.add(String.valueOf(poll));

            // 없을 경우, 고립된 정점이므로, 방문해야 할 다른 정점도 없다.
            // 그러므로 반복을 종료한다.
            var others = map.get(poll);
            if (others == null) {
                break;
            }

            // 인접한 정점을 순회한다.
            for (int other : others) {
                // 만약 방문한 정점일 경우, 스킵한다.
                if (visited[other]) {
                    continue;
                }

                // 방문한 정점으로 처리한다.
                queue.offer(other);
                visited[other] = true;
            }
        }

        return String.join(" ", strings);
    }
}
