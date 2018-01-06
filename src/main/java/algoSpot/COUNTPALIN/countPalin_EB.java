package algoSpot.COUNTPALIN;


/**
 * Created by eunbi on 2018-01-03.
 * <p>
 * 첫 번째 줄에는 입력의 종류 T(<=50)이 주어진다.
 * 그 뒤로 T개의 줄에 하나의 숫자 N(1<=N<=1,000,000)과 길이가 N인 문자열이 주어진다.
 * 문자열은 항상 알파벳 소문자로 구성되어있고, 공백은 없다.
 * <p>
 * 'HSPSH','SPS','HAAH','AA','AA'
 */
public class countPalin_EB {

    public static int count=0;
    public static char[] input = {};

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (; count > 0; count--) {
            String line = br.readLine();
            String[] spl = line.split(" ");

            sb.append(palin(spl[1])).append('\n');
        }
        System.out.println(sb.toString());
        //palin("EWHSPSHAAHDAA");
    }


    public static int palin(String val) {
        count = 0;
        input = val.toCharArray();

        int start = 0;
        int end = 1;

        for (; end <= input.length; start++, end++) {
            doPlin(start, end);
        }
        start = 0;
        end = 2;
        for (; end <= input.length; start++, end++) {
            doPlin(start, end);
        }

        return count;

    }

    public static void doPlin(int start, int end) {


        if(input[start] != input[end]) return;
        while(start >= 0 && end < input.length && input[start] == input[end]){
            count++;
            start--;
            end++;
        }
    }

}
