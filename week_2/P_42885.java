package week_2;

import java.util.Arrays;

public final class P_42885 {
    public static void main(String[] args) {
        System.out.println(new P_42885().solution(new int[]{70, 50, 80, 50}, 100) == 3);
        System.out.println(new P_42885().solution(new int[]{70, 80, 50}, 100) == 3);
    }

    public int solution(int[] people, int limit) {
        // 무게 순으로 정렬한다.
        Arrays.sort(people);

        // answer 답
        // j 배열의 마지막 index
        var answer = 0;
        var j = people.length - 1;

        // 사람들을 순회한다.
        loop:
        for (int i = 0; i < people.length; i++) {
            // -1은 이미 탑승한 사람을 의미한다.
            // 왼쪽의 사람이 탑승한 사람인 경우, 스킵한다.
            var x = people[i];
            if (x == -1) {
                continue;
            }

            // 배열의 맨 뒷사람부터 현재 사람의 바로 뒷사람까지 순회한다.
            for (; j > i; j--) {
                var y = people[j];
                // 순회 중인 사람이 탑승한 사람이거나,
                // 현재 사람과 순회 중인 사람 무게의 합이 한도를 초과하면 스킵한다.
                if (y == -1 || x + y > limit) {
                    continue;
                }

                // 한도 이내라면, 둘 다 탑승한 사람으로 처리하고 카운팅한다.
                people[i] = -1;
                people[j] = -1;
                answer++;

                // 이 때, 바깥 순회에 대하여 스킵한다.
                continue loop;
            }

            // 같이 타고갈 수 있는 사람이 없으므로,
            // 탑승한 사람으로 처리하고 카운팅한다.
            people[i] = -1;
            answer++;
        }

        return answer;
    }
}
