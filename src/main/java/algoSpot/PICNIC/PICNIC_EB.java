package algoSpot.PICNIC;

import java.util.*;

/**
 * Created by eunbi on 2018-02-05.
 * <p>
 * 안드로메다 유치원 익스프레스반에서는 다음 주에 율동공원으로 소풍을 갑니다. 원석 선생님은 소풍 때 학생들을 두 명씩 짝을 지어 행동하게 하려고 합니다.
 * 그런데 서로 친구가 아닌 학생들끼리 짝을 지어 주면 서로 싸우거나 같이 돌아다니지 않기 때문에, 항상 서로 친구인 학생들끼리만 짝을 지어 줘야 합니다.
 * <p>
 * 각 학생들의 쌍에 대해 이들이 서로 친구인지 여부가 주어질 때, 학생들을 짝지어줄 수 있는 방법의 수를 계산하는 프로그램을 작성하세요.
 * 짝이 되는 학생들이 일부만 다르더라도 다른 방법이라고 봅니다. 예를 들어 다음 두 가지 방법은 서로 다른 방법입니다.
 * <p>
 * (태연,제시카) (써니,티파니) (효연,유리)
 * (태연,제시카) (써니,유리) (효연,티파니)
 * <p>
 * 입력
 * 입력의 첫 줄에는 테스트 케이스의 수 C (C <= 50) 가 주어집니다.
 * 각 테스트 케이스의 첫 줄에는 학생의 수 n (2 <= n <= 10) 과 친구 쌍의 수 m (0 <= m <= n*(n-1)/2) 이 주어집니다.
 * 그 다음 줄에 m 개의 정수 쌍으로 서로 친구인 두 학생의 번호가 주어집니다. 번호는 모두 0 부터 n-1 사이의 정수이고,
 * 같은 쌍은 입력에 두 번 주어지지 않습니다. 학생들의 수는 짝수입니다.
 * <p>
 * 출력
 * 각 테스트 케이스마다 한 줄에 모든 학생을 친구끼리만 짝지어줄 수 있는 방법의 수를 출력합니다.
 */
public class PICNIC_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    public static int count=0;
    public static int C;
    public static int N;
    public static int M;
    public static int[][] freindsMap;

    public static List<Node> nodeList = new ArrayList<Node>();

    public static void main(String[] args)  throws Exception{
        C=Integer.parseInt(br.readLine().trim());
        for(int i=0;i<C;i++) {
            solve();
        }
        System.out.println(sb.toString());
    }

    public static void solve() throws Exception {
        String nmLine =br.readLine();
        String[] sp = nmLine.split(" ");


        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);

        String input = br.readLine();

        count = 0;
        boolean[] checkList = new boolean[N];
        for (int i = 0; i < N; i++) {
            checkList[i] = false;
        }

        initMap();

        String[] spl = input.split(" ");
        for (int i = 0; i < spl.length; i = i + 2) {
            int a = Integer.parseInt(spl[i]);
            int b = Integer.parseInt(spl[i + 1]);

            freindsMap[a][b] = freindsMap[b][a] = 1;
            nodeList.add(new Node(a, b));
        }


        for (Node node : nodeList) {
            boolean[] tempck = checkList.clone();
            tempck[node.a] = true;
            tempck[node.b] = true;
            picnic(tempck, "("+node.a+","+node.b+")", 1);
        }

        int divid = (int)Math.pow(2,N/2);
       // System.out.println(count +" / "+ combination());
        sb.append(count / divid).append("\n");
    }

    public static void picnic(boolean[] checkList, String result, int num) {

        // 쌍을 모두 찾음
        if(num == N/2){
           // System.out.println(result);
            count++;
            return;
        }
        for(int i=0;num<N/2&&i<nodeList.size();i++){
            Node node = nodeList.get(i);
            int a = node.a;
            int b = node.b;

            if(!checkList[a] &&!checkList[b]){
                boolean[] tempck = checkList.clone();


                tempck[a] = true;
                tempck[b] = true;

                picnic(tempck, result + " (" + a + "," + b + ")", num+1);
            }

        }


    }
    public static int combination(){
        int result=1;
        for(int i=1;i<=N/2;i++){
            result *= i;
        }

        System.out.println(result);
        return result;
    }


    public static void initMap() {
        freindsMap = new int[N][N];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                freindsMap[i][j] = 0;

            }
        }
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(freindsMap[i][j] + " ");

            }
            System.out.println();
        }
    }


    public static class Node {
        int a;
        int b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
