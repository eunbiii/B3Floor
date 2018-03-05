package other.NHN;

/**
 * Created by Rounting_eunbi on 2017-12-02.
 */
public class PreTest_eunbi {
    public static void main(String[] args) {

        String[] unit = {"", "십", "백", "천", "만"};
        String[] digitList = {"", "", "이", "삼", "사", "오", "육", "칠", "팔", "구"};

        StringBuilder sb = new StringBuilder();

        int input = 15733;
        int digit = 0;
        /*
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        br.close();
        */


        // digit == 현재 자리수의 숫자
        // Append : 숫자 -> 한글 치환 + 자리수 단위
        for (int i = 4; i >= 0; i--) {
            int num = (int) Math.pow(10, i);

            digit = input / num;
            if(digit!=0) sb.append(digitList[digit] + unit[i]);
            input = input % num;
        }
        if (digit == 1) {
            sb.append("일");
        }
        System.out.println(sb.length());
        System.out.println(sb.toString());
    }

}
