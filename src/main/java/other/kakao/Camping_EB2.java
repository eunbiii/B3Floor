package other.kakao;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Camping_EB2 {
    public int solution(int n, int[][] data) {
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(i, j, data, n)) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public boolean check(int num1, int num2, int[][] data, int N) {
        //쐐기1 좌표
        int num1x = data[num1][0];
        int num1y = data[num1][1];

        //쐐기2 좌표
        int num2x = data[num2][0];
        int num2y = data[num2][1];

        //넓이가 0인 케이스
        if (num1x == num2x || num1y == num2y) {
            return false;
        }

        //안에 쐐기가 존재하는 케이스
        for (int i = 0; i < N; i++) {
            if (i != num1 && i != num2) {
                int checkx = data[i][0];
                int checky = data[i][1];

                if ((checkx - num1x) * (checkx - num2x) <= 0) {
                    if ((checky - num1y) * (checky - num2y) <= 0) {
                        //System.out.println("contain ( "+checkx+" , " +checky+" )");
                        return false;
                    }

                }

            }
        }
        return true;

    }

}

