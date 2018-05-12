package other.midas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Q1_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;

    public static String[][] map;

    public static List<String> inputList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        solve();

    }

    public static void solve() throws Exception {

        init();
        String idRegex = "(?=[a-z0-9]*\\d)(?=[a-z0-9]*[a-z]).{0,12}";
        String passRegex = "(?=[a-z0-9]*\\d)(?=[a-z0-9]*[a-z]).{0,16}";
        String passCheck = "(\\w)\\1\\1\\1";

        for (String s : inputList) {
            if (s.matches(idRegex)) {
                System.out.println("id success : " + s);
            } else {
                System.out.println("id fail : " + s);
            }
            if (s.matches(passRegex)) {
                if (s.matches(passCheck))
                    System.out.println("pw success : " + s);
                else
                    System.out.println("pw fail concurrency : " + s);

            } else {
                System.out.println("pw fail : " + s);
            }
        }

    }

    public static void init() {
        String input = "asd4sdas\n" +
                "assa\n" +
                "a4444\n" +
                "sadas@d\n" +
                "sdsSsd43";
        for (String s : input.split("\n")) {
            inputList.add(s);
        }


    }

    public static void printMap() {
        System.out.println("\n==================================");
        System.out.print("weight\t\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n==================================");

    }
}

