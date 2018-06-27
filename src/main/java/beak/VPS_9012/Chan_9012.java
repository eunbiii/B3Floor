import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Chan_9012 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		scan.nextLine();
		
		while(N-- > 0) {
			char[] arr = scan.nextLine().toCharArray();
			Queue<Character> q = new LinkedList<Character>();
			
			if(arr.length == 0 || arr[0] == ')') {
				System.out.println("NO");
				continue;
			}
			
			checkRoof : for(char c : arr) {
				switch(c) {
					case '(':
						q.add(c);
						break;
					case ')':
						if(q.isEmpty()) {
							q.add(c);
							break checkRoof;
						}
						
						q.poll();
						
						break;
				}
			}
			
			System.out.println(q.isEmpty() ? "YES" : "NO");
		}
		
		scan.close();
	}
	
}
