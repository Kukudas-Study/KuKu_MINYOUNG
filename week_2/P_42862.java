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
        var reserves = new LinkedList<Integer>();
        for (int student : reserve) {
            reserves.add(student);
        }

        var losts = new LinkedList<Integer>();
        for (int student : lost) {
            if (!reserves.remove((Object) student)) {
                losts.add(student);
            }
        }

        reserves.sort(Comparator.reverseOrder());

        for (int student : reserves) {
            if (!losts.remove((Object) (student + 1))) {
                losts.remove((Object) (student - 1));
            }
        }

        return n - losts.size();
    }
}
