package beak.gear_14891;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eunbi on 2018-02-17.
 * 서로 맞닿은 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전하게 된다.
 */

public class gear_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int K;
    public static int N;
    public static int M;

    public static List<Gear> gearList = new ArrayList<Gear>();

    public static void main(String[] args) throws Exception {
        solve();
    }

    public static void solve() throws Exception {
        int i = 0;
        for (; i < 4; i++) {
            Gear gear = new Gear(br.readLine());
            gearList.add(gear);
        }
        K = Integer.parseInt(br.readLine());


        printGear();


        for (int j = 1; j <= K; j++) {
            // n 번호, M은 방향
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[0]);
            M = Integer.parseInt(line[1]);
            gearing(N-1, M);
            printGear();

        }


        int sum=0;
        int k=0;
        for(int j=1; j<9; j*=2){
            if(gearList.get(k).magList[gearList.get(k).upIndex] == 1){
                sum += j;
            }
            k++;
        }

        System.out.println(sum);
    }

    public static void gearing(int gearNum, int clock) {
        System.out.println("GearNum : "+gearNum +" / clock : "+clock);
        Gear currentGear = gearList.get(gearNum);



        rolling(gearNum + 1, 1, -1*clock);
        rolling(gearNum - 1, -1, -1*clock);
        currentGear.upIndex = (8 + currentGear.upIndex - clock) % 8;


    }

    //3,1 서로 맞닿은 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전하게 된다.
    public static void rolling(int gearNum, int direction, int clock) {
        if (gearNum < 0 || gearNum >= 4) return;

        Gear currentGear = gearList.get(gearNum);
        Gear beforeGear = gearList.get(gearNum - direction);

        //오른쪽방향
        if (direction == 1) {
            // 톱니의 극이 다르면
            if (beforeGear.magList[beforeGear.getRightIndex()] != currentGear.magList[currentGear.getLeftIndex()]) {
                // 재귀함수 호출
                rolling(gearNum + 1, direction, -1*clock);

                //인덱스 변경(돌리기)
                currentGear.upIndex = (8 + currentGear.upIndex - clock) % 8;
            }
        } else {
            //왼쪽방향
            if (beforeGear.magList[beforeGear.getLeftIndex()] != currentGear.magList[currentGear.getRightIndex()]) {
                rolling(gearNum - 1, direction, -1*clock);
                currentGear.upIndex = (8 + currentGear.upIndex - clock) % 8;
            }
        }


        //   rolling(gearNum + direction, direction, clock);

    }

    public static class Gear {
        int[] magList = new int[8];
        int upIndex = 0;

        public Gear(String line) {
            int i = 0;
            for (char c : line.toCharArray()) {
                magList[i] = (int) c - 48;
                i++;
            }

        }

        public int getRightIndex() {
            return (upIndex + 2) % 8;
        }

        public int getLeftIndex() {
            return (upIndex + 6) % 8;
        }

        @Override
        public String toString() {
            return "Gear{" +
                    "magList=" + Arrays.toString(magList) +
                    '}';
        }
    }

    public static void printGear() {
        String[] output = new String[6];
        for (int i = 0; i < 6; i++) {
            output[i] = "";
        }

        for (int i = 0; i < 4; i++) {
            int[] magList = gearList.get(i).magList;
            int upIndex = gearList.get(i).upIndex;

            output[0] = output[0] + "*" + i + "번 / "+upIndex+" \t";

            output[1] = output[1] + "    " + magList[(0 + upIndex) % 8] + "    \t";
            output[2] = output[2] + "  " + magList[(7 + upIndex) % 8] + "   " + magList[(1 + upIndex) % 8] + "  \t";
            output[3] = output[3] + magList[(6 + upIndex) % 8] + "       " + magList[(2 + upIndex) % 8] + "\t";
            output[4] = output[4] + "  " + magList[(5 + upIndex) % 8] + "   " + magList[(3 + upIndex) % 8] + "  \t";
            output[5] = output[5] + "    " + magList[(4 + upIndex) % 8] + "    \t";

        }

        for (int i = 0; i < 6; i++) {
            System.out.println(output[i]);
        }
        System.out.println();
    }
}

