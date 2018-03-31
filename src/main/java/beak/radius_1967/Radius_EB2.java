package beak.radius_1967;

import java.util.ArrayList;

/**
 * Created by eunbi on 2018-01-10.
 */
public class Radius_EB2 {



    public static void main(String[] args) throws Exception{
        solve();
    }
    static int v, biggest, weightSum;
    static ArrayList<Node>[] weightList;
    static int[] dist;

    private static void solve() throws Exception{
        weightList = (ArrayList<Node>[]) new ArrayList[10001];
        dist = new int[10001];

        for (int i = 1; i <= 10000; i++) {
            weightList[i] = new ArrayList<Node>();
        }
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n - 1; i++) {
            String line= br.readLine();
            String[] spl = line.split(" ");
            int parent = Integer.parseInt(spl[0]);
            int child = Integer.parseInt(spl[1]) ;
            int weight = Integer.parseInt(spl[2]);

            weightList[parent].add(new Node(child, weight));
            weightList[child].add(new Node(parent, weight));
        }

        //1번 == 루드 부터 탐색
        dfs(1, 0);
        weightSum = 0;

        //거리 초기화
        dist = new int[10001];

        // 기준점 == 가중치 최고점 해서 2차 dfs
        dfs(biggest, 0);
        System.out.println(weightSum);
    }

    public static void dfs(int v, int d) {
        dist[v] = d;

        //가장 큰 노드 갱신
        if (dist[v] > weightSum) {
            weightSum = dist[v];
            biggest = v;
        }

        //이웃노드 탐색
        for (Node node : weightList[v]) {
            int next = node.v;
            int weight = node.w;

            if (dist[next] == 0) {
                dfs(next, d + weight);
            }
        }

    }

    public static class Node {
        //노드, 가중치
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}
