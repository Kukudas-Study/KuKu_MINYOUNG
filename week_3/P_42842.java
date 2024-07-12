package week_3;

import java.util.Arrays;

public final class P_42842 {
    public static void main(String[] args) {
        System.out.println(Arrays.equals(new P_42842().solution(10, 2), new int[]{4, 3}));
        System.out.println(Arrays.equals(new P_42842().solution(8, 1), new int[]{3, 3}));
        System.out.println(Arrays.equals(new P_42842().solution(24, 24), new int[]{8, 6}));
    }

    public int[] solution(int brown, int yellow) {
        // 카펫의 모양이 성립하는 경우 찾기.
        //
        // i + 2는 카펫의 높이를 의미한다. 즉, 갈색 + 노랑의 높이 (테두리는 한 줄 이므로)
        // i는 (카펫의 높이 - 2)를 의미한다. 즉, 노랑의 높이
        //
        // 노랑의 최소 높이(1)부터 최대 높이까지 탐색한다.
        for (int i = 1; i <= yellow; i++) {
            // 탐색하는 높이가 노랑에 나누어 떨어지지 않으면,
            // 노랑이 해당 높이일 수가 없으므로 스킵한다.
            if (yellow % i != 0) {
                continue;
            }

            // 노랑의 최대 높이에서 현재 탐색하는 노랑의 높이를 나눈다.
            // 즉, j는 노랑 한 줄의 가로 너비를 의미한다.
            var j = yellow / i;

            // 노랑의 높이 * 2 + 노랑의 너비 * 2 + 4는 갈색의 개수와 일치해야 한다.
            // 일치하지 않을 경우, 스킵한다.
            if (i * 2 + j * 2 + 4 != brown) {
                continue;
            }

            // 해가 구해졌으므로 답을 반환한다.
            //
            // 가로 길이가 세로 길이 이상이므로 해는 1개이고,
            // 세로 길이를 탐색하였으므로 다른 탐색은 필요없다.
            return new int[]{j + 2, i + 2};
        }

        return null;
    }
}
