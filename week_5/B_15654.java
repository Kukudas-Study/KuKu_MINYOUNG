package week_5;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public final class B_15654 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입출력 설명은 생략
        var input = reader.readLine().split(" ", 2);
        var N = Integer.parseInt(input[0]);
        var M = Integer.parseInt(input[1]);

        var numbers = new int[N];
        input = reader.readLine().split(" ", N);
        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(input[index]);
        }

        writer.write(solution(M, numbers));
        writer.flush();
        writer.close();
        reader.close();
    }

    static String solution(int M, int[] numbers) {
        var destination = new ArrayList<int[]>();
        track(
                M,
                0,
                new int[M],
                numbers,
                new HashSet<>(),
                destination
        );
        // 정렬하는데, 배열이 [9, 11]일 때, 문자열로 바꾸고 정렬하면 11 9가 되어버리므로,
        // 배열 상태로 정렬한 후 문자열로 바꿔야 한다.
        //
        // Arrays::compare는 같은 index의 원소마다 0부터 차례대로 비교하며,
        // 두 원소가 서로 다르면 그 비교 결과를 반환한다.
        destination.sort(Arrays::compare);
        return destination.stream()
                .map(array ->
                        Arrays.stream(array)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining(" "))
                ).collect(Collectors.joining("\n"));
    }

    // 평범한 백트래킹 설명은 생략
    // destination에서 int[]를 넣는 이유는 위에 씀
    static void track(
            int length,
            int arrayIndex,
            int[] array,
            int[] numbers,
            Set<Integer> visited,
            List<int[]> destination
    ) {
        if (arrayIndex == length) {
            destination.add(array.clone());
            return;
        }

        for (int number : numbers) {
            if (visited.contains(number)) {
                continue;
            }

            array[arrayIndex] = number;
            visited.add(number);
            track(
                    length,
                    arrayIndex + 1,
                    array,
                    numbers,
                    visited,
                    destination
            );
            visited.remove(number);
        }
    }
}
