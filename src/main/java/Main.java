import java.util.Properties;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;

    public static String[][] map;

    public static void main(String[] args)throws Exception {
        solve();

    }

    public static void solve()throws Exception {
        System.out.println("HI");
    }
    public static void printMap(){
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

