package beak.maze_2178;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by eunbi on 2018-01-20.
 * 미로 탐색 풀이
 * 시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
 * 2 초	128 MB	29420	9076	5583	29.664%
 * 문제
 * N×M크기의 배열로 표현되는 미로가 있다.
 * <p>
 * 1	0	1	1	1	1
 * 1	0	1	0	1	0
 * 1	0	1	0	1	1
 * 1	1	1	0	1	1
 * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
 * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
 * <p>
 * 입력
 * 첫째 줄에 두 정수 N, M(2≤N, M≤100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.
 * <p>
 * 출력
 * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 * <p>
 * 예제 입력  복사
 * 4 6
 * 101111
 * 101010
 * 101011
 * 111011
 * 예제 출력  복사
 * 15
 * <p>
 * 예제 입력 2  복사
 * 4 6
 * 110110
 * 110110
 * 111111
 * 111101
 * 예제 출력 2  복사
 * 9
 */
public class maze_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    public static int N;
    public static int M;

    public static char[][] map;
    public static boolean[][] visited;

    public static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //up, down, left, right check.


    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {

        String line = br.readLine();
        String[] spl = line.split(" ");
        N = Integer.parseInt(spl[0]);
        M = Integer.parseInt(spl[1]);
        map = new char[N][M];
        visited = new boolean[N][M];

        initMap();
//            printMap();
        maze();

    }

    public static void maze() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(0);

        int qSize;
        boolean running = true;

        int polledData;
        int x;
        int y;

        int count = 0;
        while (running) {
            qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                polledData = q.poll();
                //ex 305 => x=3 y=5
                x = polledData / 100;
                y = polledData % 100;

                if (x == N - 1 && y == M - 1) {
                    count++;
                    System.out.println(count);
                    running = false;
                }

                for (int j = 0; j < 4; j++) {
                    int nextX = x + dir[j][0];
                    int nextY = y + dir[j][1];


                    if (check(nextX, nextY)) {
                        visited[nextX][nextY] = true;

                        //와웅 ex x=3 y=5 => 305
                        q.offer(nextX * 100 + nextY);
                    }
                }
            }
            count++;
        }
    }

    public static boolean check(int nextX, int nextY) {
        // 배열을 1로 감싸면 if 하나가 줄음!!!
        if (nextX >= N || nextX < 0 || nextY >= M || nextY < 0) return false;
        if (map[nextX][nextY] == '0') return false;
        if (visited[nextX][nextY]) return false;

        return true;

    }

    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void initMap() throws Exception {
        String input = "101111\n" +
                "101010\n" +
                "101011\n" +
                "111011\n";

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            char[] spl = line.toCharArray();
            map[i] = spl;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = false;
            }
        }


    }
}
