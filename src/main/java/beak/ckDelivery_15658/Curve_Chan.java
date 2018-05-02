import java.util.ArrayList;
import java.util.Scanner;

public class Curve_Chan {
	
	static int[][] dir = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int res = 0;
		boolean[][] graph = new boolean[101][101];
		int maxX = 0, maxY = 0;
		
		while(T-- > 0) {
			ArrayList<int[]> list = new ArrayList<int[]>();
			
			int X = sc.nextInt();
			int Y = sc.nextInt();
			
			int D = sc.nextInt();
			int level = sc.nextInt();
			
			list.add(new int[] { X, Y });
			list.add(new int[] { X + dir[D][0], Y + dir[D][1] } );
			
			while(level-- > 0) {
				
				int[] zeroP = list.get(list.size() - 1);
				int size = list.size();
				
				for (int i = size - 2; i >= 0; i--) {
					int[] p = list.get(i);
					
					int x = p[0] - zeroP[0];
					int y = p[1] - zeroP[1];
					
					int nx = -y + zeroP[0];
					int ny = x + zeroP[1];
					
					list.add(new int[] { nx, ny });
				}
				
			}
			
			for (int[] p : list) {
				graph[p[1]][p[0]] = true;
				maxX = Math.max(p[0], maxX);
				maxY = Math.max(p[1], maxY);
			}
			
		}
		
		sc.close();
		
		for (int i = 0; i < maxY; i++) {
			for (int j = 0; j < maxX; j++) {
				if(graph[i][j] && graph[i + 1][j] && 
						graph[i][j + 1] && graph[i + 1][j + 1]) {
					res++;
				}
			}
		}
		
		System.out.println(res);
	}

}
