package algoSpot.DiamondPath;


import java.util.Scanner;

public class DiamondPath_hoon {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-->0){
		int n = sc.nextInt();
		int[][] map = new int[2*n-1][2*n-1];
		int[][] memo = new int[2*n-1][2*n-1];
		int z = 1;
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map.length; j++){
				if(i<n && j<=i){
					map[i][j] = sc.nextInt();
				}
				
				if(i>=n && i-z>j){
					map[i][j] = sc.nextInt();
				}
				
			}
			if(i>=n){
				z+=2;
			}
		}
		z=1;
		for(int i=0; i<map.length; i++){
			for(int j=0; j<map.length; j++){
				if(i==0){
					memo[i][j] = map[i][j];
				}else{
					//System.out.println(i);
					if(i<n){
						if(j==0){
							memo[i][j] = map[i][j] + memo[i-1][j];
						}else{
							memo[i][j] = map[i][j] + memo[i-1][j]>map[i][j]+memo[i-1][j-1]?map[i][j] + memo[i-1][j]:map[i][j]+memo[i-1][j-1];
							
						}
					}else{
						if(i-z>j)
							memo[i][j] = map[i][j] + memo[i-1][j]>map[i][j]+memo[i-1][j+1]?map[i][j] + memo[i-1][j]:map[i][j]+memo[i-1][j+1];
					}
				}
				
			}
			if(i>=n){
				z+=2;
			}
		}
		System.out.print("");
		System.out.println(memo[map.length-1][0]);
		}
	}
}

