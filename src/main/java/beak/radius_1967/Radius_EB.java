package beak.radius_1967;

import java.util.ArrayList;

/**
 * Created by eunbi on 2018-01-10.
 */
public class Radius_EB {

    /**
     * Created by eunbi on 2018-01-09.
     */
    //    static List<Integer> resultList = new ArrayList<>();
    static ArrayList<Node>[] weightList;
    static int biggest, weightSum;
    static int[] dist;
    static int N;

    public static void main(String[] args) throws Exception{
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        /*
        String input = "1 2 3\n" +
                "1 3 2\n" +
                "2 4 5\n" +
                "3 5 11\n" +
                "3 6 9\n" +
                "4 7 1\n" +
                "4 8 7\n" +
                "5 9 15\n" +
                "5 10 4\n" +
                "6 11 6\n" +
                "6 12 10";
        N=12;
       // weightList = new int[N];
        for(String s : input.split("\n")){
            addList(s);
        }*/

        weightList = (ArrayList<Node>[]) new ArrayList[N+1];
        dist = new int[N+1];

        for (int i = 0; i < N+1; i++) {
 //           weightList[i] = new ArrayList<>();
        }

        N = 12; //sc.nextInt();

        for(int i=0;i<N-1;i++) {
            String line= br.readLine();
            String[] spl = line.split(" ");
            int parent = Integer.parseInt(spl[0]);
            int child = Integer.parseInt(spl[1]);
            int weight = Integer.parseInt(spl[2]);

            weightList[parent].add(new Node(child, weight));
            weightList[child].add(new Node(parent, weight));


        }

        //1차 dfs
        dfs(1, 0);

        weightSum = 0;
        dist = new int[N+1];

        // 기준점 == 가중치 최고점 해서 2차 dfs
        dfs(biggest, 0);
        System.out.println(weightSum);


    }


    public static void dfs(int v, int d) {
        dist[v] = d;

        if (dist[v] > weightSum) {
            weightSum = dist[v];
            biggest = v;
        }

        //연결 노드 확인
        for (Node node : weightList[v]) {
            int next = node.v;
            int weight = node.w;

            if (dist[next] == 0) {
                dfs(next, d + weight);
            }
        }

    }


    public static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}
