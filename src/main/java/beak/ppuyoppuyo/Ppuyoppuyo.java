package beak.ppuyoppuyo;


import java.util.*;

/**
 * Created by eunbi on 2018-03-23.
 1. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
 2. 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.

 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데,
 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.

 ......
 .Y....
 .YG...
 RRYG..
 RRYGG.
 */
public class Ppuyoppuyo {
    static  int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, -1, 0, 1 };
    public static  java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int result = 0;

    public static char[][] map;
    public static  String input;

    public static boolean checkNext = false;

    public static void main(String[] args)throws Exception {
        init();

        do {
                result++;
                checkNext = false;
                down();
                searching();
        }while (checkNext);

        System.out.println(result-1);
    }
    public static void searching(){

        //풀서칭
        for(int i=0;i<12;i++) {
            for (int j = 0; j < 6; j++) {
                if(map[i][j] != '.') {
                    Map<Integer, Integer> check = new HashMap<Integer, Integer>();
                    check.put(i * 10 + j, 0);
                    //해당 포인트 재귀
                    dfs(map[i][j], (HashMap<Integer, Integer>) check);
                }
            }
        }
    }

    public static void dfs(char color, HashMap<Integer, Integer> check){
        int currentSize = check.size();
        Map<Integer, Integer> tempCheck = (Map<Integer, Integer>) check.clone();

        for(int point : tempCheck.keySet()) {
            int x = point/10;
            int y = point%10;

            //4면 체크
            for (int i = 0; i < 4; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];
                if (tempX < 0 || tempX > 11 || tempY < 0 || tempY > 5) {
                } else {
                    int checkValue = tempX * 10 + tempY;
                    if (!tempCheck.containsKey(checkValue)) {
                        if (map[tempX][tempY] == color) {
                            check.put(checkValue, 0);
                        }
                    }
                }
            }
        }

        //
        if(check.size() == currentSize){
            if(check.size()>=4) {
                for (int k : check.keySet()) {
                    int resultX = k / 10;
                    int resultY = k % 10;
                    map[resultX][resultY] = '.';
                }
                checkNext = true;
            }
        }else{
            dfs(color,check);
        }

    }


    //map 다시 그리기
    public static void down(){
        for (int j = 0; j < 6; j++) {
            String s = "";
            for(int i=0;i<12;i++) {
                s = s+map[i][j];
            }

            s = s.replace(".", "");
            if(s.length()!=0) {
                while (s.length() != 12) {
                    s = "." + s;
                }

                char[] c = s.toCharArray();
                for (int i = 0; i < 12; i++) {
                    map[i][j] = c[i];
                }
            }

        }
//        printMap();
    }

    //초기화
    public static void init(){
        result = 0;
        map = new char[12][6];
        //int i=0;

        for(int i=0;i<12;i++){
            map[i] = sc.nextLine().toCharArray();
            //  i++;
        }
    }

    public static void printMap(){
        System.out.println("==========");
        for(int i=0;i<12;i++){
            for(int j=0;j<6;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("==========");
    }
}
