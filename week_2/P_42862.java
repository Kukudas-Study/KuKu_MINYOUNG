package week_2;

import java.util.Comparator;
import java.util.LinkedList;

public final class P_42862 {
    public static void main(String[] args) {
        System.out.println(new P_42862().solution(5, new int[]{2, 4}, new int[]{1, 3, 5}) == 5);
        System.out.println(new P_42862().solution(5, new int[]{2, 4}, new int[]{3}) == 4);
        System.out.println(new P_42862().solution(3, new int[]{3}, new int[]{1}) == 2);
    }

    public int solution(int n, int[] lost, int[] reserve) {
        // 체육복을 가져온 학생들을 저장
        var reserves = new LinkedList<Integer>();
        for (int student : reserve) {
            reserves.add(student);
        }

        // 체육복을 잃어버린 학생들을 저장
        var losts = new LinkedList<Integer>();
        for (int student : lost) {
            // "여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다"
            // 이 조건에 만족하는 학생은 reserves, losts 둘 다 제외시킨다.
            if (!reserves.remove((Object) student)) {
                losts.add(student);
            }
        }

        // 번호 순으로 정렬
        reserves.sort(Comparator.reverseOrder());

        // 체육복을 가져온 학생들을 순회
        for (int student : reserves) {
            // 앞 번호가 잃어버린 학생에 해당하는지 확인하기 위해 remove를 호출한다.
            //
            // 호출 결과가 참인 경우 앞 번호는 잃어버린 학생에 해당하므로,
            // losts의 크기는 1 줄어들게 된다.
            //
            // 호출 결과가 거짓인 경우, 뒷 번호에도 remove를 호출한다.
            if (!losts.remove((Object) (student + 1))) {
                losts.remove((Object) (student - 1));
            }
        }

        // 전체 학생 수 - 잃어버린 수 = 체육수업을 들을 수 있는 학생의 최댓값
        return n - losts.size();
    }
}
