package beak.gear_14891;


import java.util.Scanner;


public class Gear_Chan {
	
	static boolean[][] gear = new boolean[4][8];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int score = 0;
		
		for (int i = 0; i < 4; i++) {
			int read = sc.nextInt();
			
			for (int j = 0; j < 8; j++) {
				gear[i][7 - j] = read % 10 == 1;
				read /= 10;
			}
			
		}
		
		int n = sc.nextInt();
		
		while(n-- > 0) {
			int cur = sc.nextInt() - 1;
			int dir = sc.nextInt();
			
			rotate(cur, dir);
			
			checkNear(cur, dir, -1);
			checkNear(cur, dir, 1);
		}
		
		sc.close();
		
		for (int i = 0; i < gear.length; i++) {
			if(gear[i][0]) score += Math.pow(2, i);
		}
		
		System.out.println(score);
	}
	
	public static void checkNear(int cur, int dir, int isLeft) {
	    
		int near = cur + isLeft;
		int curI = isLeft == -1 ? 6 : 2;
		int nearI = isLeft == -1 ? 2 : 6;
		
		while(near < 4 && near >= 0) {
			
			if(gear[near][nearI] ^ gear[cur][curI + dir]) {
				
				dir *= -1;
				cur = near;
				rotate(cur, dir);
				
				near += isLeft;
				
			} else break;
		}
	}
	
	public static void rotate(int now, int duration) {
		
		int index = 0;
		boolean temp = gear[now][index];
        
        for (int i = 0; i < 8; i++) {
        	int prev = (index - duration + 8) % 8;
        	gear[now][index] = gear[now][prev];
        	index = prev;
        }
        
        index = (index + duration + 8) % 8;
        gear[now][index] = temp;
	}
    
}
