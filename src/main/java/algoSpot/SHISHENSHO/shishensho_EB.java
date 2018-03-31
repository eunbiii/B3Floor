package algoSpot.SHISHENSHO;

/**
 * Created by eunbi on 2018-02-03.
 */

import java.util.*;

public class shishensho_EB {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        Scanner stdin = new Scanner(System.in);

        int T = stdin.nextInt();
        int[] ctr = new int[T];

        for (int t = 0; t < T; t++) {
            int row = stdin.nextInt();
            int col = stdin.nextInt();
            stdin.nextLine();

            char[][] map = new char[row][];
            for (int i = 0; i < row; i++)
                map[i] = stdin.nextLine().toCharArray();

            Node node = new Node(map);

            for (int word = 0; word < 52; word++) {
                int numOfPoints = node.x[word].size();
                for (int m = 0; m < numOfPoints - 1; m++) {
                    for (int n = m + 1; n < numOfPoints; n++) {
                        int ai = node.x[word].get(m);
                        int aj = node.y[word].get(m);
                        int bi = node.x[word].get(n);
                        int bj = node.y[word].get(n);
                        char temp1 = map[ai][aj];
                        char temp2 = map[bi][bj];
                        map[ai][aj] = '.';
                        map[bi][bj] = '.';
                        if (checkPath(map, ai, aj, bi, bj))
                            ctr[t]++;
                        map[ai][aj] = temp1;
                        map[bi][bj] = temp2;
                    }
                }
            }
        }

        for (int t = 0; t < T; t++)
            System.out.println(ctr[t]);
    }

    static class Node {
        private List<Integer>[] x = new ArrayList[52];  // row
        private List<Integer>[] y = new ArrayList[52];  // column

        Node(char[][] map) {
            for (int i = 0; i < 52; i++) {
                x[i] = new ArrayList<Integer>();
                y[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] >= 'a') {
                        x[map[i][j] - 'a'].add(i);
                        y[map[i][j] - 'a'].add(j);
                    } else if (map[i][j] >= 'A') {
                        x[map[i][j] - 'A' + 26].add(i);
                        y[map[i][j] - 'A' + 26].add(j);
                    }
                }
            }
        }
    }

    static boolean checkPath(char[][] map, int ai, int aj, int bi, int bj) {
        for (int i = 0; i < map.length; i++) {
            if (isEmptyRow(map, i, aj, bj)
                    && isEmptyCol(map, i, ai, aj)
                    && isEmptyCol(map, i, bi, bj)) {
                return true;
            }
        }
        for (int j = 0; j < map[0].length; j++) {
            if (isEmptyCol(map, ai, bi, j)
                    && isEmptyRow(map, ai, aj, j)
                    && isEmptyRow(map, bi, bj, j)) {
                return true;
            }
        }
        return false;
    }

    static boolean isEmptyRow(char[][] map, int row, int col1, int col2) {
        int tempCol1 = (col1 < col2) ? col1 : col2;
        int tempCol2 = (col1 < col2) ? col2 : col1;
        for (int j = tempCol1; j <= tempCol2; j++)
            if (map[row][j] != '.')
                return false;
        return true;
    }

    static boolean isEmptyCol(char[][] map, int row1, int row2, int col) {
        int tempRow1 = (row1 < row2) ? row1 : row2;
        int tempRow2 = (row1 < row2) ? row2 : row1;
        for (int i = tempRow1; i <= tempRow2; i++)
            if (map[i][col] != '.')
                return false;
        return true;
    }
}
