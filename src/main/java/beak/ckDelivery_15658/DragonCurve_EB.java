package beak.ckDelivery_15658;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by eunbi on 2018. 4. 28.
 *
 첫째 줄에 드래곤 커브의 개수 N(1 ≤ N ≤ 20)이 주어진다.
 둘째 줄부터 N개의 줄에는 드래곤 커브의 정보가 주어진다.
 드래곤 커브의 정보는 네 정수 x, y, d, g로 이루어져 있다.
 x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다. (0 ≤ x, y ≤ 100, 0 ≤ d ≤ 3, 0 ≤ g ≤ 10)

 입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다. 드래곤 커브는 서로 겹칠 수 있다.

 방향은 0, 1, 2, 3 중 하나이고, 다음을 의미한다.

 0: x좌표가 증가하는 방향 (→)
 1: y좌표가 감소하는 방향 (↑)
 2: x좌표가 감소하는 방향 (←)
 3: y좌표가 증가하는 방향 (↓)
 *
 */
public class DragonCurve_EB {


    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    public static Scanner sc = new Scanner(System.in);

    public static int N, M;
    public static int x, y, d, g;

    public static int[] curveX = new int[4];
    public static int[] curveY = new int[4];

    public static int[][] map;


    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {
        init();


        Point p = new Point(x, y, d);

        Point nP = p.nextPoint(p.d);
        map[nP.x][nP.y] = 2;

        printMap();

        List<Point> pointList = new ArrayList<>();
        pointList.add(p);
        pointList.add(nP);

        curve(0,pointList);
        printMap();
        curve(1,pointList);
        printMap();
        curve(3,pointList);
        printMap();

    }
    public static void curve(int depth, List<Point> pointList){
        Point lastPoint = pointList.get(pointList.size()-1);
        int num =1;

        for(int i=pointList.size()-1;i>0;i--){
            Point currentPoint = pointList.get(i);

            Point nextPoint = lastPoint.nextPoint((currentPoint.d+1)%4);
            pointList.add(nextPoint);
            map[nextPoint.x][nextPoint.y] = num;
            num++;
            lastPoint = nextPoint;
        }

    }


    public static void init(){
        String input = "3\n" +
                "3 3 0 1\n" +
                "4 2 1 3\n" +
                "4 2 2 1";

        curveY[0]=1;
        curveX[0]=0;

        curveY[1]=0;
        curveX[1]=-1;

        curveY[2]=-1;
        curveX[2]=0;

        curveY[3]=0;
        curveX[3]=1;

        N = 3;
        x = 3;
        y = 2;
        d = 0;
        g = 1;


        map = new int[6][6];
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                map[i][j] = 0;
            }
        }

        map[x][y] = 1;

//        N = Integer.parseInt(sc.nextLine());
//        for(int i=0;i<N;i++){
//            //x, y, d, g로 이루어져 있다.
//            // x와 y는 드래곤 커브의 시작 점, d는 시작 방향, g는 세대이다.
//            x = sc.nextInt();
//            y = sc.nextInt();
//            d = sc.nextInt();
//            g = sc.nextInt();
//        }

    }
    public static void printMap(){
        System.out.println("==================");
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }

    public static class Point{
        int x;
        int y;
        int d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
        public Point nextPoint(int d){
            int nextX = this.x+curveX[d];
            int nextY = this.y+curveY[d];
            int nextCurve = d;

            return new Point(nextX,nextY,nextCurve);
        }
    }



}
