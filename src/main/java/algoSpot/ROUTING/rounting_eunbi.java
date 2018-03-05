package algoSpot.ROUTING;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eunbi on 2017-12-16.
 * <p>
 * 입력의 첫 줄에는 테스트 케이스의 수 C (<= 50) 이 주어집니다.
 * 각 테스트 케이스의 첫 줄에는 컴퓨터의 수 N (<= 10000) 과 회선의 수 M (<= 20000) 이 주어집니다.
 * 각 컴퓨터는 0 부터 N-1 까지의 번호로 표현됩니다.
 * 그 후 줄에 각 3개의 정수로 각 회선의 정보가 주어집니다.
 * 회선의 정보는 a b c 로 표현되며, 이 때 이 회선은 a 번과 b 번 컴퓨터 사이를 이으며 이 회선을 지날 때 노이즈는 c 배 증폭됩니다.
 * c 는 언제나 1 이상의 실수입니다. 모든 회선은 양방향으로 데이터를 전송할 수 있습니다.
 * <p>
 * 시작 컴퓨터는 항상 0 번, 끝 컴퓨터는 항상 N-1번이라고 가정하며, 이와 같은 경로는 언제나 존재한다고 가정합니다.
 */
public class rounting_eunbi {
    public static int N = 7;
    public static int M = 14;
    public static float[] distance;
    public static float[][] noise;
    public static boolean[] check;

    public static class Node {
        String beforeNode;
        float distance;
        boolean bool = false;

        public Node(String beforeNode, float distance) {
            this.beforeNode = beforeNode;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {

        String input = "0 1 1.3\n" +
                "0 2 1.1\n" +
                "0 3 1.24\n" +
                "3 4 1.17\n" +
                "3 5 1.24\n" +
                "3 1 2\n" +
                "1 2 1.31\n" +
                "1 2 1.26\n" +
                "1 4 1.11\n" +
                "1 5 1.37\n" +
                "5 4 1.24\n" +
                "4 6 1.77\n" +
                "5 6 1.11\n" +
                "2 6 1.2\n";
        init();


        // 가중치 저장
        for (String line : input.split("\n")) {
            String[] spl = line.split(" ");
            noise[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])] = Float.parseFloat(spl[2]);
            noise[Integer.parseInt(spl[1])][Integer.parseInt(spl[0])] = Float.parseFloat(spl[2]);
        }

        printDist();
        dijkstra(0);

        System.out.println("************* result *************");
        printDist();

//        System.out.println(distance[N-1]);
    }

    public static void dijkstra(int node) {
        if (node != N - 1) {


            //가중치 업데이트
            for (int i = 1; i < N; i++) {
                if (!check[i] && noise[node][i] != 9999) {
                    distance[i] = Math.min(distance[i], distance[node] + noise[node][i]);
                }
            }

            check[node] = true;
            printDist();
        }
        //다음 노드 선정
        int minNode = 0;
        float minVal = 9999;
        for (int i = 1; i < N; i++) {
            if (!check[i] && i != node && distance[i] != 9999) {
                if (minVal > distance[i]) {
                    minNode = i;
                    minVal = distance[i];
                }

            }
        }

        //기저
        if (minNode == 0) return;
        System.out.println("Next Node : " + minNode + "\n");

        dijkstra(minNode);
/*
        for(int a=0;a<N-1;a++){
            float min=Integer.MAX_VALUE;
            int min_index=-1;

            //최소값 찾기
            for(int i=1;i<N;i++){
                //자기가 가진 가장 최소의 값으로
                if(!check[i] && distance[i]!=9999){
                    if(distance[i]<min ){
                        min=distance[i];
                        min_index = i;
                    }
                }
            }

            check[min_index] = true;
            for(int i=1;i<N+1;i++){
                if(!check[i] && weight[min_index][i]!=0){
                    if(distance[i]>distance[min_index]+weight[min_index][i]){
                        distance[i] = distance[min_index]+weight[min_index][i];
                    }
                }
            }

        }
*/


    }

    public static void printDist() {
        System.out.println("\n==================================");
        System.out.print("node\t ");
        for (int i = 0; i < N; i++) {
            System.out.print(i + "\t\t ");
        }
        System.out.println();

        System.out.print("bool\t");
        for (int i = 0; i < N; i++) {
            System.out.print(check[i] + "\t\t ");
        }
        System.out.println();

        System.out.print("dist\t");
        for (int i = 0; i < N; i++) {
            System.out.print(distance[i] + "\t\t ");
        }
        System.out.println("\n==================================");

    }

    public static void init() {
        noise = new float[N][N];         // 가중치 저장 변수
        distance = new float[N];          //최단 거리를 저장할 변수
        check = new boolean[N];     //해당 노드를 방문했는지 체크할 변수

        //
        for (int i = 0; i < N; i++) {
            distance[i] = 9999;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                noise[i][j] = 9999;
            }
        }

        //시작 노드값 초기화
        distance[0] = 0;
        check[0] = true;
        noise[0][0] = 0;
    }

}
