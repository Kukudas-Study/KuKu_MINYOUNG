package week_2;

import java.io.*;

public final class B_5585 {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        var writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(String.valueOf(solution(Integer.parseInt(reader.readLine()))));
        writer.flush();
        writer.close();
        reader.close();
    }

    public static int solution(int amount) {
        // 돈 저장
        var num = 1000 - amount;
        var count = 0;
        while (num > 0) {
            // 남아있는 만큼 내림차순으로 처리
            if (num >= 500) num -= 500;
            else if (num >= 100) num -= 100;
            else if (num >= 50) num -= 50;
            else if (num >= 10) num -= 10;
            else if (num >= 5) num -= 5;
            else num -= 1;
            count++;
        }

        return count;
    }
}
