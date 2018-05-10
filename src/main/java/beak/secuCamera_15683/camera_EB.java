package beak.secuCamera_15683;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by eunbi on 2018-01-12.
 */

public class camera_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;
    public static boolean[][] scopeList = {{false, true, false, false},
            {false, true, false, true},
            {true, true, false, false},
            {true, true, false, true},
            {true, true, true, true}};

    public static int[][] map;
    public static List<Camera> cameraList;
    public static int result = 999;

    public static void main(String[] args) throws Exception {
        solve();

    }

    public static void solve() throws Exception {
        init();
  //      printMap();

        reduece(0);
        System.out.println(result);
    }

    public static void reduece(int index) {
        if (index >= cameraList.size()) {
//            printMap();
//            System.out.println(count());
            result = Math.min(count(),result);
            return;
        }
        Camera camera = cameraList.get(index);
        boolean[] scope = scopeList[camera.num];

        //i는 회전
        for (int i = 0; i < 4; i++) {
            //j = 카메라 사각방향
            int[][] tmpMap = deepCopy(map,N,M);
            for (int j = 0; j < 4; j++) {
                if (scope[(i + j) % 4]) {
                    scoping(camera.x, camera.y, j);
                }

            }
            reduece(index+1);
            map = tmpMap.clone();
//            printMap();
        }

    }
    public static int count(){
        int result= 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0)
                    result++;
            }
        }
        return result;

    }

    public static void scoping(int x,int y, int direction) {
        switch (direction) {
            case 0:
                for(int i=x-1;i>=0;i--){
                    if(map[i][y]==6) break;
                    map[i][y] = 9;
                }
                break;
            case 1:
                for(int i=y+1;i<M;i++){
                    if(map[x][i]==6) break;
                    map[x][i] = 9;
                }
                break;
            case 2:
                for(int i=x+1;i<N;i++){
                    if(map[i][y]==6) break;
                    map[i][y] = 9;
                }
                break;
            case 3:
                for(int i=y-1;i>=0;i--){
                    if(map[x][i]==6) break;
                    map[x][i] = 9;
                }
                break;


        }
    }

    public static void init() {
        String input ="0 0 0 0 0 0\n" +
                "0 2 0 0 0 0\n" +
                "0 0 0 0 6 0\n" +
                "0 6 0 0 2 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 5";
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        cameraList = new ArrayList<>();

//        sc = new Scanner(input);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int val = sc.nextInt();
                if (val > 0 && val < 6) {
                    cameraList.add(new Camera(i, j, val-1));
                }
                map[i][j] = val;
            }
        }
        Collections.sort(cameraList, (o1, o2) -> o2.num - o1.num);
    }

    public static void printMap() {
        System.out.println("\n==================================");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n==================================");

    }
    public static int[][] deepCopy(int[][] original, int n,int m) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[n][m];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    public static class Camera {
        int x;
        int y;
        int num;


        public Camera(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "C-" + num +
                    "(" + x +
                    ", " + y +
                    ")";
        }

    }
}

