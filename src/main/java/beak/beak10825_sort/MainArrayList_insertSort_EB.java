package beak.beak10825_sort;

public class MainArrayList_insertSort_EB {

    public static void main(String[] args) throws Exception{
        java.util.List<Node> nodeVOList = new java.util.ArrayList<Node>();
        StringBuilder sb = new StringBuilder();
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (;count>0;count--) {
            Node node = new Node(br.readLine());
            insertList(nodeVOList,node);

        }
        for(Node vo : nodeVOList){
            sb.append(vo.getName()+"\n");
        }
        System.out.println(sb.toString());
    }
    public static void insertList(java.util.List<Node> nodeVOList, Node node){
        if(nodeVOList.size()==0){
            nodeVOList.add(node);
        }else{
            for(int i=0;i<nodeVOList.size();i++) {
                if(compareList(nodeVOList.get(i),node)){
                    nodeVOList.add(i,node);
                    return;
                }
            }
            nodeVOList.add(node);
        }

    }


    public static boolean compareList(Node nextNode, Node currentNode){
        if(nextNode.getKorea() < currentNode.getKorea()){
            return true;
        }else if(nextNode.getKorea() == currentNode.getKorea()) {
            if(nextNode.getEnglish() > currentNode.getEnglish()){
                return true;
            }else if(nextNode.getEnglish() == currentNode.getEnglish()) {
                if(nextNode.getMath() < currentNode.getMath()){
                    return true;
                }else if(nextNode.getMath() == currentNode.getMath()) {
                    if(nextNode.getName().compareTo(currentNode.getName())>0) return true;
                    else return false;
                }
            }
        }
        return false;
    }
    public static class Node {
        // data
        private String name;
        private int korea;
        private int english;
        private int math;

        public Node(String name, int korea, int english, int math) {
            this.name = name;
            this.korea = korea;
            this.english = english;
            this.math = math;
        }
        public Node(String line) {
            String[] spl = line.split(" ");
            this.name = spl[0];
            this.korea = Integer.parseInt(spl[1]);
            this.english = Integer.parseInt(spl[2]);
            this.math = Integer.parseInt(spl[3]);
        }


        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", korea=" + korea +
                    ", english=" + english +
                    ", math=" + math +
                    '}';
        }

        public int getKorea() {
            return korea;
        }

        public int getEnglish() {
            return english;
        }

        public int getMath() {
            return math;
        }

        public String getName() {
            return name;
        }
    }

}