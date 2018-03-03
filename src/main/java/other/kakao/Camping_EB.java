package other.kakao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Camping_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;
    public static int[][] data;

    public static Node[] nodeList ;

    public static boolean flag = true;


    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {
        N = 7;
        data = new int[N][2];

//        String input ="[[0, 0], [1, 1], [0, 2], [2, 0]]";

        //{ 0, 0},{1,0},{2,0},{1,1} ,{0,3}, {1,2}, {2,3} };

        data[0] = new int[]{0, 0};
        data[1] = new int[]{1, 0};
        data[2] = new int[]{2, 30};
        data[3] = new int[]{1, 21};
        data[4] = new int[]{0, 3};
        data[5] = new int[]{10, 20};
        data[6] = new int[]{20, 3};

        printNode();


        // 좌표 압축
        ArrayList<Integer> xList = new ArrayList<Integer>();
        ArrayList<Integer> yList = new ArrayList<Integer>();


        for (int i = 0; i < N; i++) {

            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }

        ArrayList<Integer> uniqueXList = new ArrayList<Integer>(new HashSet<Integer>(xList));
        ArrayList<Integer> uniqueYList = new ArrayList<Integer>(new HashSet<Integer>(yList));

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);


        // 구간합 배열
        int[][] S = new int[N][N];

        for (int i = 0; i < N; i++) {

            int x = uniqueXList.indexOf(new Integer(data[i][0]));
            int y = uniqueYList.indexOf(new Integer(data[i][1]));

            // 좌표 압축 적용
            data[i][0] = x;
            data[i][1] = y;

            // 구간합 배열 초기값
            S[x][y] = 1;
        }

        printNode();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                System.out.print(S[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        // N^2 구간합 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                S[i][j] += (i - 1 >= 0 ? S[i - 1][j] : 0)
                        + (j - 1 >= 0 ? S[i][j - 1] : 0)
                        - (i - 1 >= 0 && j - 1 >= 0 ? S[i - 1][j - 1] : 0);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                System.out.print(S[i][j] + " ");
            }
            System.out.println();
        }

        int ans = 0;
        // N^2 모든 쐐기 조합에 대하여 검사
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                // 조건#1 검사 : 직사각형이 아닌 경우
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                // 조건#2 검사 : 내부에 쐐기가 존재하는 경우
                int startX, startY, endX, endY;

                startX = Math.min(data[i][0], data[j][0]);
                startY = Math.min(data[i][1], data[j][1]);
                endX = Math.max(data[i][0], data[j][0]);
                endY = Math.max(data[i][1], data[j][1]);

                int cnt;

                //각 좌표의 길이가 1일 때  => 무조건 O (ex [1,0] / [2,10] )
                if (startX + 1 > endX - 1 || startY + 1 > endY - 1) {

                    cnt = 0;
                } else {
                    // 큰거 - 좌 - 우 + 작은거
                    //http://meansoup.blogspot.kr/2017/09/blog-post.html 참고
                    cnt = (S[startX][startY] + S[endX - 1][endY - 1]) - (S[endX - 1][startY] + S[startX][endY - 1]) ;
                }

                if (cnt == 0) {
//                    System.out.println(data[i][0]+" , "+data[i][1]+" / "+data[j][0]+" , "+data[j][1]);
                    ans++;
                }
            }
        }


    }

    public static void printNode(){
        System.out.println("==========");
        for(int[] n  : data){
            System.out.println(n[0]+" , "+n[1]);
        }
        System.out.println("==========");
    }


    public static class Node  implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +
                    ", " + y +
                    ')';
        }

        public int compareTo(Node node2) {
            if(flag) {
                return x -node2.x;
            }else{
                return y -node2.y;
            }
        }
    }

    public static void check(int num1, int num2) {
        //쐐기1 좌표
        int num1x = data[num1][0];
        int num1y = data[num1][1];

        //쐐기2 좌표
        int num2x = data[num2][0];
        int num2y = data[num2][1];

        //넓이가 0인 케이스
        if (num1x == num2x || num1y == num2y) {
            return;
        }

        //안에 쐐기가 존재하는 케이스
        for (int i = 0; i < N; i++) {
            if (i != num1 && i != num2) {
                int checkx = data[i][0];
                int checky = data[i][1];

                if ((checkx - num1x) * (checkx - num2x) < 0) {
                    if ((checky - num1y) * (checky - num2y) < 0) {
                        System.out.println("contain ( " + checkx + " , " + checky + " )");
                        return;
                    }

                }

            }
        }

        System.out.println("(" + num1x + " , " + num1y + " ) / (" + num2x + " , " + num2y + " )");
    }


}

