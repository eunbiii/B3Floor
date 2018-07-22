package beak.ckDelivery_15658;


import java.util.ArrayList;
import java.util.Scanner;

public class Delivery_Chan {
	
	static int N, M;
	static int result = Integer.MAX_VALUE;
	static ArrayList<Position> house = new ArrayList<Position>();
	static ArrayList<Position> chicken = new ArrayList<Position>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				switch(sc.nextInt()) {
					case 1:
						house.add(new Position(i, j));
						break;
					case 2:
						chicken.add(new Position(i, j));
						break;
					default:
						break;
				}
			}
		}
		
		sc.close();
		boolean[] select = new boolean[chicken.size()];
		
		for (int i = 0; i < chicken.size(); i++) {
			dfs(i, select, 0);
		}
		
		System.out.println(result);
	}
	
	static void dfs(int idx, boolean[] select, int cur) {
		select[idx] = true;
		cur++;
	    
		if(cur == M) {
			int res = 0;
			
			for (Position h : house) {
				int dist = Integer.MAX_VALUE;
				
				for (int i = 0; i < chicken.size(); i++) {
					if(!select[i]) continue;
					Position p = chicken.get(i);
					int d = Math.abs(p.r - h.r) + Math.abs(p.c - h.c);
					dist = Math.min(dist, d);
				}
				
				res += dist;
			}
			
			result = Math.min(res, result);
		} else {
		    for (int i = idx + 1; i < chicken.size(); i++) {
		    	dfs(i, select, cur);
		    }
		}
		
		select[idx] = false;
	}
	
	static class Position {
		int r, c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
