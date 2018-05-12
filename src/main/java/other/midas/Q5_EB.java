package other.midas;

import java.util.*;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Q5_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static Scanner sc = new Scanner(System.in);
    public static String input;

    public static String[][] map;

    public static int result = Integer.MAX_VALUE;

    public static Map<String, Integer > emailMap = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        solve();

    }

    public static void solve() throws Exception {

        init();

        sb = new StringBuilder();


        System.out.println(result);

    }

    public static void init() {
        input = "1!=2?3>4?5>=6?7:8==9?10:11:12:13";
        char[] inputArray = input.toCharArray();
        List<String> sList = new ArrayList<>();
        String s="";
        for(int i=0;i<inputArray.length;i++){
            if(inputArray[i] == '?' || inputArray[i] == ':' ){
                sList.add(s);
                s="";
                sList.add(String.valueOf(inputArray[i]));
            }else {
                s = s+inputArray[i];
            }
        }
        System.out.println();
    }
}

