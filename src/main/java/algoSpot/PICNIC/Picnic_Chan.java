package algoSpot.PICNIC;

import java.util.Scanner;


public class Picnic_Chan {
	
	static boolean[][] map;
	static boolean[] people;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		while(n-- > 0) {
			int count = scan.nextInt();
			int pair = scan.nextInt();
			
			map = new boolean[count][count];
			
			people = new boolean[count];
			
			for (int i = 0; i < pair; i++) {
				
				int p1 = scan.nextInt();
				int p2 = scan.nextInt();
				
				map[p1][p2] = map[p2][p1] = true;
			}
			
			int res = pairing(count);
			System.out.println(res);
		}
		
		scan.close();
	}
	
	public static int pairing(int count) {
		
		int res = 0;
		int idx = 0;
		
		for (int i = 0; i < people.length; i++) {
			if(!people[i]) {
				idx = i;
				
				if(count == 2) {
					for (int j = i + 1; j < people.length; j++) {
						if(!people[j]) {
							return map[i][j] ? 1 : 0;
						}
					}
					return 0;
				}
				
				break;
			}
		}
		
		for (int i = idx + 1; i < people.length; i++) {
			if(!people[i] && map[idx][i]) {
				people[idx] = people[i] = true;
				
				res += pairing(count - 2);
				
				people[idx] = people[i] = false;
			}
		}
		
		return res;
	}
}
