package other.midas;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eunbi on 2018-01-12.
 */

public class Q2_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static java.util.Scanner sc = new java.util.Scanner(System.in);
    public static int C;
    public static int N;
    public static String company;

    public static String[][] map;

    public static List<String> inputList = new ArrayList<>();

    public static Map<String, Integer > emailMap = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        solve();

    }

    public static void solve() throws Exception {

        init();

        sb = new StringBuilder();
        for(String e : inputList){
            sb.append(e+", ");
        }
        String s = sb.substring(0,sb.length()-2);
        System.out.println(s);

    }

    public static void init() {
        String input = "fffirst middle last\n" +
                "first middle last\n" +
                "first la-st";
        company = "eunbiiii";

        String regex = "([a-z]+)\\s(([a-z]+)(\\s))?([a-z|-]+)";
        Pattern pat = Pattern.compile(regex);
        for (String s : input.split("\n")) {
                Matcher match = pat.matcher(s);
                match.matches();
                match.groupCount();
                String fName = match.group(1);
                String lName = match.group(5).replace("-","");
                String email = lName+fName.substring(0,1);

//                Emp emp = new Emp(company,match.group(1),match.group(2),match.group(5));
                if(!emailMap.containsKey(email)){
                    emailMap.put(email,1);
                    inputList.add(email+"@"+company+".com");
                }else {
                    int index = emailMap.get(email)+1;
                    emailMap.put(email, index);
                    inputList.add(email+index+"@"+company+".com");
                }

        }


    }


    public static class Emp{
        String firstName="";
        String middleName= "";
        String lastName="";
        String company="";
        int index = 1;

        @Override
        public String toString() {
            return "Emp{" +
                    "firstName='" + firstName + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", company='" + company + '\'' +
                    '}';
        }
        public String getfirst(){
            return lastName+firstName.substring(0,1);
        }
        public String getEmail(){
            String email = getfirst();
            if(index>1){
                email = email+index;
            }
            return email+"@"+company+".com";
        }

        public Emp(String company, String firstName, String middleName, String lastName) {
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.company = company;
        }
    }
}

