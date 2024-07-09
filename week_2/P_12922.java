package week_2;

public final class P_12922 {
    public static void main(String[] args) {
        System.out.println(new P_12922().solution(3).equals("수박수"));
        System.out.println(new P_12922().solution(4).equals("수박수박"));
    }

    public String solution(int n) {
        // builder 결과
        var builder = new StringBuilder();
        for (int index = 0; index < n; index++) {
            // 나머지로 수, 박 판별
            builder.append(index % 2 == 0 ? "수" : "박");
        }

        return builder.toString();
    }
}
