package other.kakao.clean;


/**
 * Created by eunbi on 2018-01-12.
 */

public class Solution_3 {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static int M;


    public static void main(String[] args)throws Exception {
        String input = "722/148+360/176\n" +
                "978/1212+183/183\n" +
                "358/472+301/417\n" +
                "780/309+684/988\n" +
                "258/840+854/686\n";
        String[] spl = input.split("\n");
        String[]  result = reducedFractionSums(spl);





    }
    static String[] reducedFractionSums(String[] expressions) {
        /*
         * Write your code here.
         */
        String[] resultList = new String[expressions.length];
        for(int i=0;i<expressions.length;i++) {
            String[] spl =expressions[i].split("\\+");
            Expression expr1 = new Expression(spl[0]);
            Expression expr2 = new Expression(spl[1]);

            Expression result = calcSum(expr1,expr2);

            calcReduce(result);

            resultList[i]=result.toString();
        }




        return resultList;
    }

    public static Expression calcSum(Expression expr1,Expression expr2){
        int son = expr1.son*expr2.parent + expr2.son*expr1.parent;
        int parent = expr1.parent * expr2.parent;

        return new Expression( son,parent);
    }

    public static Expression calcReduce(Expression expression){
        int gcd = gcd(expression.parent,expression.son);

        expression.parent /= gcd;
        expression.son /= gcd;

        return expression;
    }
    public static int gcd(int num1, int num2) {

        while (num2 != 0) {
            int temp = num1 % num2;
            num1 = num2;
            num2 = temp;
        }

        return Math.abs(num1);
    }

    public static class Expression{
        int parent;
        int son;
        public Expression( int son, int parent) {
            this.parent = parent;
            this.son = son;
        }

        public Expression(String line) {
            String[] val1 = line.split("/");

            this.parent = Integer.parseInt(val1[1]);
            this.son = Integer.parseInt(val1[0]);
        }

        @Override
        public String toString() {
            return son+"/"+parent;
        }
    }


}

