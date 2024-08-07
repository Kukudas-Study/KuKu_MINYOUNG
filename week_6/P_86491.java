package week_6;

public final class P_86491 {
    public static void main(String[] args) {
        System.out.println(new P_86491().solution(new int[][]{
                {60, 50},
                {30, 70},
                {60, 30},
                {80, 40}
        }) == 4000);
        System.out.println(new P_86491().solution(new int[][]{
                {10, 7},
                {12, 3},
                {8, 15},
                {14, 7},
                {5, 15}
        }) == 120);
        System.out.println(new P_86491().solution(new int[][]{
                {14, 4},
                {19, 6},
                {6, 16},
                {18, 7},
                {7, 1}
        }) == 133);
    }

    public int solution(int[][] sizes) {
        var maxWidth = Integer.MIN_VALUE;
        var maxHeight = Integer.MIN_VALUE;
        for (var size : sizes) {
            var first = size[0];
            var second = size[1];

            var width = Math.min(first, second);
            var height = Math.max(first, second);

            if (maxWidth < width) {
                maxWidth = width;
            }

            if (maxHeight < height) {
                maxHeight = height;
            }
        }

        return maxWidth * maxHeight;
    }
}
