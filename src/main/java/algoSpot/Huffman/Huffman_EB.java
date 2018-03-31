package algoSpot.Huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by eunbi on 2018-01-12.
 * https://algospot.com/judge/problem/read/KHUFFMAN
 * <p>
 * 입력은 T개의 테스트 케이스로 구성된다. 입력의 첫 줄에는 T가 주어진다.
 * 각 테스트 케이스 첫 줄에는 두 정수 N (2 <= N <= 10000),
 * K (2 <= K <= 10000)가 공백으로 구분되어 주어진다.
 * <p>
 * N은 Huffman 나라의 문자의 수이고 K는 인코딩할 진법을 나타낸다.
 * 다음 줄에는 각 문자가 문자열에 몇 번이나 나타나는지를 의미하는 N 개의 정수 Ci (0 <= Ci <= 100000) 가 공백으로 구분되어 주어진다.
 */
public class Huffman_EB {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    public static int N;
    public static int K;
    public static int T;

    //   public static List<Integer> useList;
    public static List<Tree> useTree;
    public static Tree resultTree;
    public static String[] resultList;
    public static int count =0;

    public static void main(String[] args) throws Exception{
        N = 4;
        K = 2;
        String input = "0 1 2 3";
        String input2 = "0 1 2 2";

        T = Integer.parseInt( br.readLine());
        for( int i=0; i<T;i++) {
            solve();
        }
        System.out.println(sb.toString());

    }

    public static void solve() throws Exception {
        String s = br.readLine();
        String[] spl = s.split(" ");
        N = Integer.parseInt(spl[0]);
        K = Integer.parseInt(spl[1]);

        String input = br.readLine();

        initList(input);

//        System.out.println("===================");
//        System.out.println(useTree);
//        System.out.println("===================");

        setTree();
        getList(resultTree, "");

        sb.append(count).append("\n");
    }

    public static void getList(Tree currentTree, String val) {
        if (currentTree.treeList.size() ==0) {
            System.out.println(val+ " / sum : "+currentTree.sum);
            count = count+val.length()*currentTree.sum;
            return;
        }
        for(int i=0;i<currentTree.treeList.size();i++){
            if(currentTree.treeList.get(i) != null) {
                getList(currentTree.treeList.get(i), val + i);
            }
        }



    }
    public static void initList(String input) {
        resultTree = new Tree();
        useTree = new ArrayList<Tree>();
        count =0;

        for (String s : input.split(" ")) {
            int num = Integer.parseInt(s);
                useTree.add(new Tree(num));

        }

        resultList = new String[useTree.size()];

        sortList();
    }

    public static void sortList() {
        //      Collections.sort(useList, Collections.reverseOrder());
        Collections.sort(useTree, new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return o2.sum - o1.sum;
            }
        });

    }

    public static void setTree() {

        if (useTree.size() <= K) {
            for(int i=0;i<useTree.size();i++){
                resultTree.treeList.add(useTree.get(useTree.size() - i - 1));
                resultTree.sum+=  useTree.get(useTree.size() - i - 1).sum;
            }
            System.out.println(resultTree);

            return;

        } else {
            Tree newTree = new Tree();

            for(int i=0;i<K;i++){
                newTree.treeList.add(useTree.get(useTree.size() - i - 1));
                newTree.sum+=  useTree.get(useTree.size() - i - 1).sum;
            }

            useTree = useTree.subList(0, useTree.size() - K);
            useTree.add(newTree);

            sortList();
            setTree();
        }

    }

    public static class Tree {
        List<Tree> treeList  = new ArrayList<Tree>();
        Tree left;  //0
        Tree right; //1
        int sum;

        public Tree() {
        }
        public Tree(int sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "sum=" + sum +
                    ", treeList=" + treeList+
                    '}';
        }
    }

}
