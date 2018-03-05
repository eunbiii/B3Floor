package algoSpot.COUNTPALIN;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by eunbi on 2018-01-03.
 * <p>
 * 첫 번째 줄에는 입력의 종류 T(<=50)이 주어진다.
 * 그 뒤로 T개의 줄에 하나의 숫자 N(1<=N<=1,000,000)과 길이가 N인 문자열이 주어진다.
 * 문자열은 항상 알파벳 소문자로 구성되어있고, 공백은 없다.
 * <p>
 * 'HSPSH','SPS','HAAH','AA','AA'
 *
 * Do MANACHER
 */
public class countPalin_EB {

    public static int count=0;
    public static char[] input = {};
    public static int[] palinList;
    public static Map<String, String> resultMap;
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (; count > 0; count--) {
            String line = br.readLine();
            String[] spl = line.split(" ");

            sb.append(manacher(spl[1])).append('\n');
        }
        System.out.println(sb.toString());
     //manacher("EWHSPSHAAHDAAA");
       // palin("bananac");
    }

    public static int manacher(String s) {

        input = new char[s.length()*2 + 3];

        //시작 : $, 끝 : @
        input[0] = '$';
        input[s.length()*2 + 2] = '@';

        //리스트의 끝까지 문자 사이사이에 #을 넣어줌 aa -> a#a = 회문 o
        for (int i = 0; i < s.length(); i++) {
            input[2*i + 1] = '#';
            input[2*i + 2] = s.charAt(i);
        }
        input[s.length()*2 + 1] = '#';


        //회문 length
        int[]  palin = new int[input.length];

        int center = 0, right = 0;

        // 1부터 시작 (0은 $)
        for (int i = 1; i < input.length-1; i++) {
            //center에서 i만큼 떨어진 곳 == center 반대편
            int mirr = 2*center - i;


            //i가 오른쪽 기준선보다 작으면
            if (i < right) {
                //
                palin[i] = Math.min(right - i, palin[mirr]);
            }else{
                //
                palin[i]=0;
            }


            while (input[i + (1 + palin[i])] == input[i - (1 + palin[i])])
                palin[i]++;

            if (i + palin[i] > right) {
                center = i;
                right = i + palin[i];
            }
        }

        int length = 0;   // length of longest palindromic substring
        center = 0;   // center of longest palindromic substring
        int count =0;
        for (int i = 1; i < palin.length-1; i++) {
            count += palin[i]/2;
//            if (palin[i] > length) {
//                length = palin[i];
//                center = i;
//            }
        }


        return count;
//        System.out.println(s.substring((center - 1 - length) / 2, (center - 1 + length) / 2));
    }


    public static int palin(String input, int N) {
        String str = "~";
        char[] s = input.toCharArray();

//        cin >> N >> s;
        for(int i = 1 ; i <= input.length(); i++){
            str += "#";
            str += s[i-1];
        }
    //    str += "#";
        str += "!";
        char[] resultStr = str.toCharArray();
        int R = -1, p=0;


        //회문 length
        int[]  A = new int[str.length()];
        int[]  min = new int[str.length()];


        for(int i = 1 ; i < str.length()-1; i++){
            if (i <= R) A[i] = Math.min(A[2*p -i], R - i);
            else A[i] = 0;
            while(resultStr[i - A[i] - 1] == resultStr[i + A[i] + 1]) A[i]++;
            if(R < A[i] + i) R = A[i] + i; p = i;
        }
        int ans = 0;
        for(int i = 1; i < str.length()-1; i++) ans += A[i]/2;

        return ans;


    }

}
