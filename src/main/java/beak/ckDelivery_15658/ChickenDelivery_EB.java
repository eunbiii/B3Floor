package beak.ckDelivery_15658;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mobigen on 2018. 4. 29.
 */
public class ChickenDelivery_EB {


    public static StringBuilder sb = new StringBuilder();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int N, M;

    static int[][] map;
    static ArrayList<Point> buildingList;
    static ArrayList<Point> personList;
    static int[] output;
    static boolean[] visited;
    static int result;



    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {

        init();
        visited = new boolean[buildingList.size()];
        output = new int[buildingList.size()];

        //치킨 집 선택
        for (int i = 0; i < buildingList.size(); i++) {
            visited[i] = true;
            select(i, 0);
            visited[i] = false;
        }
        System.out.println(result);


    }

    public static void select(int start, int depth) {
        output[depth] = start + 1;

        for (int i = start; i < buildingList.size(); i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            select(i, depth + 1);
            visited[i] = false;
        }

        //기저 : 3개 모두 선택
        if (depth == M - 1) {
            int sum = 0;

            //사람별
            for (int i = 0; i < personList.size(); i++) {
                int distanceMin = Integer.MAX_VALUE;
                //최소거리 빌딩 선택
                for (int j = 0; j < M; j++) {
                    int distance = calcDistance(personList.get(i), buildingList.get(output[j] - 1));
                    distanceMin = Math.min(distanceMin, distance);
                }
                sum = sum + distanceMin;
            }

            //다른 조합과 비교
            result = Math.min(result, sum);
        }
    }



    public static void init() throws Exception{

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new int[N][N];
        result = Integer.MAX_VALUE;
        buildingList = new ArrayList<Point>();
        personList = new ArrayList<Point>();

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if (map[i][j] == 1) {
                    personList.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    buildingList.add(new Point(i, j));
                }
            }
        }
    }

    public static int calcDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
