public final class B_22864 {
    public int solution(String string) {
        var array = string.split(" ", 4);

        var A = Integer.parseInt(array[0]);
        var B = Integer.parseInt(array[1]);
        var C = Integer.parseInt(array[2]);
        var M = Integer.parseInt(array[3]);

        var i = 0;
        var j = 0;
        var k = 0;

        while (k++ < 24) {
            if (j + A <= M) {
                i += B;
                j += A;
            } else {
                j -= C;
                if (j < 0) {
                    j = 0;
                }
            }
        }

        return i;
    }
}
