package algoSpot.NQueen;

/**
 * Created by Rounting_eunbi on 2017-12-04.
 */
public class NQueenBackTracking3_eunbi {
    public static int sum = 0;

    /*
            public static void main(String[] args) throws Exception {

                int N = 8;
                int[] board = new int[N];
    //           initBoard(board, N);

                backTracking(board, N, 0);
                System.out.println("Total : " + sum);




            }
    */
    public static void main(String[] args) throws Exception {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        for (; num > 0; num--) {
            sum = 0;
            int N = Integer.parseInt(br.readLine());
            int[] board = new int[N];
//        initBoard(board, N);

            backTracking(board, N, 0);
            System.out.println(sum);
        }

    }

    public static void backTracking(int[] board, int n, int x) {
        if (x == n) {
            printBoard(board, n);
            sum++;
        } else {
            for (int y = 0; y < n; y++) {
                board[x] = y;
                if (check(board, x)) {
                    backTracking(board, n, x + 1);
                }
            }
        }
    }


    // 이전것이 없는지 확인
    public static boolean check(int[] board, int x) {

        // \ check, / check, | check
        for (int i = 0; i < x; i++) {
            if (board[i] == board[x]) return false;
            if ((board[i] - board[x]) == (x - i)) return false;
            if ((board[x] - board[i]) == (x - i)) return false;
        }
        return true;

    }

    public static String printBoard(int[] board, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("==========================\n");
        for (int x = 0; x < n; x++) {
            int y = board[x];
            for (int i = 0; i < y; i++) {
                sb.append("0 ");

            }
            sb.append("1 ");
            for (; y < n - 1; y++) sb.append("0 ");
            sb.append("\n");
        }
        sb.append("==========================\n");
        System.out.println(sb.toString());
        return sb.toString();
    }




}
