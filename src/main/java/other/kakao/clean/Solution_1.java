package other.kakao.clean;



/**
 * Created by eunbi on 2018-01-12.
 */

public class Solution_1 {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;


    public static void main(String[] args)throws Exception {
        String[] votes = new String[5];
        System.out.println(electionWinner(votes));

    }

    public static String electionWinner(String[] votes)throws Exception {


        String result = "";
        try {

            java.util.Map<String, Integer> map = new java.util.HashMap<String, Integer>();
            for (String person : votes) {
                if (map.containsKey(person)) {
                    map.put(person, map.get(person) + 1);
                } else {
                    map.put(person, 1);
                }
            }


            java.util.List<String> candidateList = new java.util.ArrayList<String>();
            int maxVal = 0;
            for (String key : map.keySet()) {
                int current = map.get(key);
                if (maxVal < current) {
                    maxVal = current;
                    candidateList.clear();
                    candidateList.add(key);
                } else if (maxVal == current) {
                    candidateList.add(key);
                }

            }

            java.util.Collections.sort(candidateList, java.util.Collections.<String>reverseOrder());
            result = candidateList.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        return  result;

    }
}

