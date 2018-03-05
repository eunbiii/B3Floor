package algoSpot.BOARDCOVER;

import java.util.Arrays;

/**
 * Created by eunbi on 2018-02-06.
 */
public class BOARDCOVER_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int H;
    public static int W;
    public static int count =0;

    public static String[][] board;
    public static void main(String[] args)throws Exception {
        C=Integer.parseInt(br.readLine().trim());

        /**
         * 테스트케이스 루프
         */
        for(int i=0;i<C;i++) {
            solve();
        }
        System.out.println(sb.toString());
    }

    public static void solve()throws Exception {
//        H=8;
//        W=10;

        /**
         * Setting var value
         */
        String lines = br.readLine();
        String[] spl  = lines.split(" ");
        H=Integer.parseInt(spl[0]);
        W=Integer.parseInt(spl[1]);


/*        String input = "########## \n" +
                "#........# \n" +
                "#........# \n" +
                "#........# \n" +
                "#........# \n" +
                "#........# \n" +
                "#........# \n" +
                "########## ";

        String[] lines = input.split("\n");*/

        initBoard();


        /**
         * Map Data Setting
         */
        for(int i=0;i<H;i++){
            String input = br.readLine();
            int j=1;
            for(char c : input.toCharArray()){
                board[i+1][j] = String.valueOf(c);
                j++;
            }

        }

        boardCover();
        sb.append(count).append("\n");
    }

    public static void boardCover(){
        /**
         * Find first point '.'
         */
        int x=1;
        int y=1;
        for(x=1;x<H+1;x++){
            for(y = 1; y < W+1; y++) {
                if (board[x][y].equals(".")) {
                    break;
                }
            }
            if (y<W+1&&board[x][y].equals(".")) {
                break;
            }
        }

        //기저 => x,y 둘다 모두 채워짐
        if(x == H+1 && y == W+1){
            count++;
            return ;
        }

        // *
        // @ @
        if(board[x+1][y].equals(".")&&board[x+1][y+1].equals(".")){
            board[x][y] = board[x+1][y] =board[x+1][y+1] = "*";
            boardCover();
            board[x][y] = board[x+1][y] =board[x+1][y+1] = ".";
        }
        // *
        //@@
        if(board[x+1][y-1].equals(".")&&board[x+1][y].equals(".")){
            board[x][y] = board[x+1][y-1] =board[x+1][y] = "*";
            boardCover();
            board[x][y] = board[x+1][y-1] =board[x+1][y] = ".";
        }
        // *@
        //  @
        if(board[x][y+1].equals(".")&&board[x+1][y+1].equals(".")){
            board[x][y] = board[x][y+1] =board[x+1][y+1] = "*";
            boardCover();
            board[x][y] = board[x][y+1] =board[x+1][y+1] = ".";
        }
        // *@
        // @
        if(board[x+1][y].equals(".")&&board[x][y+1].equals(".")){
            board[x][y] = board[x+1][y] =board[x][y+1] = "#";
            boardCover();
            board[x][y] = board[x+1][y] =board[x][y+1] = ".";
        }

    }

    public static String printBoard(){
        StringBuilder sb = new StringBuilder();
        sb.append("==========================\n");
        for(int x=0;x<H+2;x++){
            for(int y = 0; y < W+2; y++) {
                sb.append(board[x][y]+" ");

            }

            sb.append("\n");
        }
        sb.append("==========================\n");
        System.out.println(sb.toString());
        return sb.toString();
    }
    public static void initBoard(){
        //3 , 7
        board = new String[H+2][W+2];
        for(int i=1;i<=H;i++){
            board[i][0] = board[i][W+1] = "#";
        }
        for(int i=0;i<W+2;i++){
            board[0][i] = board[H+1][i] = "#";
        }
        count=0;
    }
}
