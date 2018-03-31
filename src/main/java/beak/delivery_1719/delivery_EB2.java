package beak.delivery_1719;


/**
 * Created by eunbi on 2018-02-01.
 * <p>
 * - 2 3 3 2 2
 * 1 - 1 4 5 5
 * 1 1 - 4 5 6
 * 3 2 3 - 6 6
 * 2 2 3 6 - 6
 * 5 5 3 4 5 -
 */
public class delivery_EB2 {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    public static int N, M;

    public static int currentNode;

    public static int[] distance;
    public static int[][] weight;
    public static boolean[] check;
    public static int[] firstNode;


    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {

        N = 6;
        M = 10;


        String input = "1 2 2\n" +
                "1 3 1\n" +
                "2 4 5\n" +
                "2 5 3\n" +
                "2 6 7\n" +
                "3 4 4\n" +
                "3 5 6\n" +
                "3 6 7\n" +
                "4 6 4\n" +
                "5 6 2";
        // 가중치 저장

        weightInit();

        for (String line : input.split("\n")) {
            String[] spl = line.split(" ");
            weight[Integer.parseInt(spl[0]) - 1][Integer.parseInt(spl[1]) - 1] = Integer.parseInt(spl[2]);
            weight[Integer.parseInt(spl[1]) - 1][Integer.parseInt(spl[0]) - 1] = Integer.parseInt(spl[2]);
        }

        printWeight();
/*        for (int i = 0; i < N; i++) {
            nodeInit(i);
            dijkstra(i);
            sb.append(getsbList()).append("\n");
        }*/
        nodeInit(3);
        dijkstra(3);
// 2 1 2 - 5 5

        System.out.println(sb.toString());

    }


    public static String getsbList() {
        String result = "";
        for (int i = 0; i < N; i++) {
            if (firstNode[i] == 99) {
                result = result + " -";
            } else {
                result = result + " " + (firstNode[i] + 1);
            }
        }
        return result.substring(1, result.length());
    }

    public static void dijkstra(int node) {
        //가중치 업데이트
        for (int i = 0; i < N; i++) {
            if (!check[i] && weight[node][i] != 99) {
                if (distance[i] > distance[node] + weight[node][i]) {
                    distance[i] = distance[node] + weight[node][i];
                    firstNode[i] = node;
                    /*if (firstNode[node] == 99) {
                        firstNode[i] = i;
                    } else {
                        firstNode[i] = firstNode[node];
                    }*/
                }
                //distance[i] = Math.min(distance[i], distance[node] + weight[node][i]);

            }
        }
        check[node] = true;
        printDist();


        //다음 노드 선정
        int minNode = -1;
        float minVal = 9999;
        for (int i = 0; i < N; i++) {
            if (!check[i] && i != node && distance[i] != 99) {
                if (minVal > distance[i]) {
                    minNode = i;
                    minVal = distance[i];
                }

            }
        }

        //기저
        if (minNode == currentNode) return;
        if (minNode == -1) return;
        //System.out.println("Next Node : " + minNode + "\n");

        dijkstra(minNode);
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
            System.out.print(check[i] + "\t ");
        }
        System.out.println();

        System.out.print("dist\t");
        for (int i = 0; i < N; i++) {
            System.out.print(distance[i] + "\t\t ");
        }
        System.out.println();

        System.out.print("moveTo\t");
        for (int i = 0; i < N; i++) {
            System.out.print(firstNode[i] + "\t\t ");
        }
        System.out.println("\n==================================");

    }

    public static void nodeInit(int node) {
        distance = new int[N];          //최단 거리를 저장할 변수
        check = new boolean[N];     //해당 노드를 방문했는지 체크할 변수
        firstNode = new int[N];          //처음 방문노드 저장노드

        for (int i = 0; i < N; i++) {
            distance[i] = 99;
        }

        for (int i = 0; i < N; i++) {
            firstNode[i] = 99;
        }


        //시작 노드값 초기화
        distance[node] = 0;
        check[node] = true;
        weight[node][node] = 0;
        currentNode = node;
    }

    public static void weightInit() {
        weight = new int[N][N];         // 가중치 저장 변수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                weight[i][j] = 99;
            }
        }

    }

    public static void printWeight() {
        System.out.println("\n==================================");
        System.out.print("weight\t\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(weight[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n==================================");

    }

}
