package other.kakao.clean;


import java.util.ArrayList;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Solution_2 {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;


    public static void main(String[] args)throws Exception {
        String input = "101111010110011011100100\n" +
                "110000010101011111101111\n" +
                "100110101100111111101101\n" +
                "010111011010010110000011\n" +
                "000000001111111111111111";
        String[] spl = input.split("\n");
        closestColor(spl);


    }

    public static String[] closestColor(String[] pixels){

        String[] resultColors = new String[pixels.length];
        String[] colors = {"Black","White","Red","Green","Blue"};
        int[] rList = {0,255,255,0,0};
        int[] gList = {0,255,0,255,0};
        int[] bList = {0,255,0,0,255};


        for(int j=0;j<pixels.length;j++){
            String pixel = pixels[j];
            String result = "Ambiguous";
            int r = Integer.parseInt(pixel.substring(0,8),2);
            int g = Integer.parseInt(pixel.substring(8,16),2);
            int b = Integer.parseInt(pixel.substring(16,24),2);


            double[] resultList = new double[5];

            for(int i=0;i<5;i++){
                resultList[i] = calcDist(r,g,b,rList[i],gList[i],bList[i]);
            }


            double minVal=Integer.MAX_VALUE;
            boolean amb = false;
            for(int i=0;i<resultList.length;i++){
                if(minVal>resultList[i]){
                    minVal = resultList[i];
                    result = colors[i];
                    amb=false;
                }else if(minVal == resultList[i]){
                    amb = true;
                }

            }
            if(amb) result = "Ambiguous";
            resultColors[j] = result;
        }

        return resultColors;

    }

    public static double calcDist(int r, int g, int b,int r2, int g2, int b2){
        double calc = Math.pow((r-r2),2) + Math.pow((g-g2),2)+ Math.pow((b-b2),2);
        return Math.sqrt(calc);
    }

}

