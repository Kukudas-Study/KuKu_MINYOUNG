package week_1;

public final class P_12977 {
    public static void main(String[] args) {
        System.out.println(new P_12977().solution(new int[]{1, 2, 3, 4}) == 1);
        System.out.println(new P_12977().solution(new int[]{1, 2, 7, 6, 4}) == 4);
    }

    public int solution(int[] nums) {
        var answer = 0;
        for (int i = 0; i < nums.length - 2; i++)
            for (int j = i + 1; j < nums.length - 1; j++)
                for (int k = j + 1; k < nums.length; k++) {
                    var num = nums[i] + nums[j] + nums[k];
                    var flag = false;
                    for (int l = 2; l <= Math.sqrt(num); l++)
                        if (num % l == 0) {
                            flag = true;
                            break;
                        }

                    if (!flag) answer++;
                }

        return answer;
    }
}
