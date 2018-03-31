package beak.minesweeper_2140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eunbi on 2017-12-18.
 */
public class Minesweeper_eunbi_main {
    public static int bombNum = 0;
    public static int N ;
    public static String[][] board ;
    public static class Doll{
        int x;
        int y;
        String value;
        boolean bool;

        public Doll(int x, int y, String value) {
            this.x = x;
            this.y = y;
            this.value = value;
            bool = false;
        }
    }
    public static void main(String[] args) throws Exception{
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board  = new String[N+1][N+1];



        int y=0;

        List<Doll> dollList = new ArrayList<Doll>();
        for(int x=0;x<N;x++){
            String line = br.readLine();
            y = 0;
            for(char c : line.toCharArray()){
                board[x][y] = String.valueOf(c);
                //if(c!='#')
                    dollList.add(new Doll(x,y,String.valueOf(c)));
                y++;
            }
        }
        printBoard();

        while(dollList.size()>0){
            for(int i=0;i<dollList.size();i++){
                if(checkXY(dollList.get(i).x,dollList.get(i).y)){
                    dollList.remove(i);
                }
            }
        }


        printBoard();
        System.out.println(bombNum);

    }

    public static boolean checkXY(int x, int y){
        String value = board[x][y];
        System.out.println("x : " + x + " / y : " + y + " / value : " + value);
        Map<String, List<Doll>> readMap = new HashMap<String, List<Doll>>();

        try{


            readMap.put("*", new ArrayList<Doll>());
            readMap.put("#", new ArrayList<Doll>());
            readMap.put("x", new ArrayList<Doll>());

            putMap(x - 1, y - 1, readMap);
            putMap(x-1,y,readMap);
            putMap(x-1,y+1,readMap);
            putMap(x,y-1,readMap);
            putMap(x,y+1,readMap);
            putMap(x+1,y-1,readMap);
            putMap(x+1,y,readMap);
            putMap(x + 1, y + 1, readMap);

            int num = Integer.parseInt(value);

            if(num == 0) {
                clearBomb(readMap.get("#"));
                return true;
            }
            int bomb = readMap.get("*").size();
            int nullNum = readMap.get("#").size();

            if(num == bomb) {
                clearBomb(readMap.get("#"));
                return true;
            }
            if(num ==  bomb + nullNum){
                putBomb(readMap.get("#"));
                return true;
            }

        }catch (Exception e){
            if(readMap.keySet().size() ==3){
                board[x][y] = "*";
                bombNum++;
            }
            return true;
        }
        return false;
    }

    public static void putBomb(List<Doll> bombList){
        for(Doll doll : bombList){
            board[doll.x][doll.y] = "*";
            bombNum++;
        }

    }
    public static void clearBomb(List<Doll> bombList){
        for(Doll doll : bombList){
            board[doll.x][doll.y] = "x";
        }

    }


    public static void putMap(int x, int y, Map<String, List<Doll>> readMap){
        if(x<0 || y<0 || x>N ||y>N) return;
        if(readMap.containsKey(board[x][y])){
            readMap.get(board[x][y]).add(new Doll(x, y, board[x][y]));

        }else{
            List<Doll> dollList = new ArrayList<Doll>();
            dollList.add(new Doll(x, y, board[x][y]));
            readMap.put(board[x][y], dollList);
        }
    }

    public static void printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("==========================\n");
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sb.append(board[x][y] + " ");

            }

            sb.append("\n");
        }
        sb.append("==========================\n");
        System.out.println(sb.toString());
        //return sb.toString();
    }

}
