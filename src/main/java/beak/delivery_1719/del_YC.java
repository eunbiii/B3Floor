package beak.delivery_1719;

/**
 * Created by eunbi on 2018-02-04.
 */
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class del_YC {
    public static int n, m;
    public static int[] t;
    public static int[] d;

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        int[][] map, res;

        n = 6;
        m = 10;


        String input = "1 2 2\n" +
                "1 3 1\n" +
                "2 4 5\n" +
                "2 5 3\n" +
                "2 6 7\n" +
                "3 4 4\n" +
                "3 5 6\n" +
                "3 6 7\n" +
                "4 6 4\n" +
                "5 6 2";
        // 가중치 저장


        if(n < 0 || n > 200 || m < 0 || m > 10000) System.exit(0);

        map = new int[n][n];
        res = new int[n][n];

        //**채우기

        for (String line : input.split("\n")) {
            String[] spl = line.split(" ");
            map[Integer.parseInt(spl[0]) - 1][Integer.parseInt(spl[1]) - 1] = Integer.parseInt(spl[2]);
            map[Integer.parseInt(spl[1]) - 1][Integer.parseInt(spl[0]) - 1] = Integer.parseInt(spl[2]);
        }


        //**다익스트라
        int i=0;
        //for (int i = 0; i < n; i++) {
            pq.offer(i);

            d = new int[n];
            t = new int[n];

            Arrays.fill(d, Integer.MAX_VALUE);

            d[i] = 0;
            t[i] = i;

        printDist();
        //
            while(!pq.isEmpty()) {
                int cur = pq.poll();
                for (int j = 0; j < n; j++) {

                    if(map[cur][j] == 0) continue;
                    else if(d[j] > map[cur][j] + d[cur]) {
                        d[j] = map[cur][j] + d[cur];
                        t[j] = cur;
                        pq.offer(j);
                    }
                }
                System.out.println("\ncurrent : "+cur);
                printDist();

            }
            for (int j = 0; j < n; j++) {
                res[i][j] = t[j];
            }

        //}

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                if(j == k) System.out.print("- ");
                else System.out.print(res[j][k] + " ");
            }

            System.out.println();
        }
    }

    public static void printDist() {
        System.out.println("==================================");
        System.out.print("node\t ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t\t ");
        }
        System.out.println();

/*
        System.out.print("bool\t");
        for (int i = 0; i < n; i++) {
            System.out.print(check[i] + "\t ");
        }
        System.out.println();
*/

        System.out.print("dist\t");
        for (int i = 0; i < n; i++) {
            System.out.print(d[i] + "\t\t ");
        }
        System.out.println();

        System.out.print("moveTo\t");
        for (int i = 0; i < n; i++) {
            System.out.print(t[i] + "\t\t ");
        }
        System.out.println("\n==================================");

    }

}