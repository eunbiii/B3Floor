package other.midas;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Q4_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static Scanner sc = new Scanner(System.in);
    public static String num;
    public static String token;
    public static String company;

    public static String[][] map;

    public static int result = Integer.MAX_VALUE;

    public static Map<String, Integer > emailMap = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        solve();

    }

    public static void solve() throws Exception {

        init();

        sb = new StringBuilder();

        aCode(new StringBuffer(num),token);


        System.out.println(result);

    }
    public static void aCode (StringBuffer num, String token){
        for(int i=0;i+token.length()<num.length();i++){
            if(token.equals(num.substring(i,i+token.length()))){
                StringBuffer temp = new StringBuffer(num);
                num.delete(i,i+token.length());
                aCode(num, token);
                num = new StringBuffer(temp);
            }
        }

        result = Math.min(num.length(),result);
        System.out.println(token+"/" +num);

    }

    public static void init() {
        num = Integer.toBinaryString(438);
        token = Integer.toBinaryString(13);
    }

}

